package com.fbb.funapp.data.remote

import com.fbb.funapp.domain.model.CourtMatch
import com.fbb.funapp.domain.model.MatchRound
import com.fbb.funapp.domain.model.Player
import com.fbb.funapp.domain.model.Session
import com.fbb.funapp.domain.model.Team
import com.fbb.funapp.utils.convertTimestampToDate
import com.fbb.funapp.utils.toCourtMatch
import com.fbb.funapp.utils.toSession
import com.fbb.funapp.utils.toTeam
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class FirebaseDataSource {

    private val firestore = FirebaseFirestore.getInstance()

    suspend fun saveSession(session: Session) {
        val sessionRef = firestore.collection("sessions").document(session.id)

        val sessionData = mapOf(
            "id" to session.id,
            "nameOfMabar" to session.nameOfMabar,
            "totalPlayers" to session.totalPlayers,
            "totalCourts" to session.totalCourts,
            "totalTime" to session.totalTime,
            "matchDuration" to session.matchDuration,
            "date" to session.date,
            "createdAtFormatted" to session.createdAtFormatted,
            "createdAt" to session.createdAt
        )

        sessionRef.set(sessionData).await()
    }

    suspend fun saveAllPlayer(sessionId: String, players: List<Player>) = coroutineScope {
        val batch = firestore.batch()
        val playerRef = firestore.collection("sessions").document(sessionId).collection("players")

        players.forEach { player ->
            val docRef = playerRef.document(player.id)
            val playerMap = mapOf(
                "id" to player.id,
                "name" to player.name,
                "gamesPlayed" to player.gamesPlayed,
                "lastPlayedRound" to player.lastPlayedRound
            )
            batch.set(docRef, playerMap)
        }

        batch.commit().await()
    }

    suspend fun saveMatchRound(sessionId: String, roundNumber: Int, matches: List<CourtMatch>) {
        val roundRef = firestore.collection("sessions")
            .document(sessionId)
            .collection("rounds")
            .document(roundNumber.toString())

        val roundData = mapOf(
            "roundNumber" to roundNumber,
            "timestamp" to System.currentTimeMillis()
        )

        roundRef.set(roundData).await()

        val batch = firestore.batch()
        matches.forEachIndexed { index, court ->
            val match = mapOf(
                "courtNumber" to index + 1,
                "team1" to mapOf(
                    "player1Id" to court.team1.player1.id,
                    "player2Id" to court.team1.player2.id
                ),
                "team2" to mapOf(
                    "player1Id" to court.team2.player1.id,
                    "player2Id" to court.team2.player2.id
                )
            )
            val matchRef = roundRef.collection("matches").document()
            batch.set(matchRef, match)
        }

        batch.commit().await()
    }

    suspend fun getHistorySession(): List<Session> = withContext(Dispatchers.IO) {
        firestore.collection("sessions")
            .orderBy("createdAt", Query.Direction.DESCENDING)
            .get()
            .await()
            .documents
            .map { it.toSession() }
    }

    suspend fun getSessionById(sessionId: String): Session = withContext(Dispatchers.IO) {
        val snapshot = firestore.collection("sessions")
            .document(sessionId)
            .get()
            .await()

        Session(
            id = snapshot.id,
            nameOfMabar = snapshot.getString("nameOfMabar") ?: "-",
            totalPlayers = snapshot.getLong("totalPlayers")?.toInt() ?: 0,
            totalCourts = snapshot.getLong("totalCourts")?.toInt() ?: 0,
            totalTime = snapshot.getLong("totalTime")?.toInt() ?: 0,
            matchDuration = snapshot.getLong("matchDuration")?.toInt() ?: 0,
            createdAtFormatted = convertTimestampToDate(timestamp = snapshot.getLong("createdAt") ?: 0)
        )
    }

    suspend fun getTeams(sessionId: String): List<Team> = withContext(Dispatchers.IO) {
        val teamsSnapshot = firestore.collection("sessions")
            .document(sessionId)
            .collection("teams")
            .get()
            .await()

        teamsSnapshot.documents.mapNotNull { it.toTeam() }
    }

    suspend fun getMatchRounds(sessionId: String): List<MatchRound> = coroutineScope {
        val roundsSnapshot = firestore.collection("sessions")
            .document(sessionId)
            .collection("rounds")
            .orderBy("roundNumber")
            .get()
            .await()

        val roundTasks = roundsSnapshot.documents.map { roundDoc ->
            async {
                val roundNumber = (roundDoc.getLong("roundNumber") ?: return@async null).toInt()

                val matchesSnapshot = roundDoc.reference
                    .collection("matches")
                    .orderBy("courtNumber")
                    .get()
                    .await()

                val courtMatches = matchesSnapshot.documents.mapNotNull { it.toCourtMatch() }

                MatchRound(roundNumber = roundNumber, courts = courtMatches)
            }
        }

        roundTasks.awaitAll().filterNotNull()
    }


}