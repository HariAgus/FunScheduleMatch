package com.fbb.funapp.data.remote

import com.fbb.funapp.domain.model.CourtMatch
import com.fbb.funapp.domain.model.MatchRound
import com.fbb.funapp.domain.model.Player
import com.fbb.funapp.domain.model.Session
import com.fbb.funapp.domain.model.Team
import com.fbb.funapp.utils.convertTimestampToDate
import com.fbb.funapp.utils.toCourtMatch
import com.fbb.funapp.utils.toTeam
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.coroutines.Dispatchers
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
            "createdAtFormatted" to session.createdAtFormatted,
            "createdAt" to session.createdAt
        )

        sessionRef.set(sessionData).await()
    }

    suspend fun savePlayer(sessionId: String, player: Player) {
        val playerMap = mapOf(
            "id" to player.id,
            "gamesPlayed" to player.gamesPlayed,
            "lastPlayedRound" to player.lastPlayedRound
        )

        firestore.collection("sessions")
            .document(sessionId)
            .collection("players")
            .document(player.id)
            .set(playerMap)
            .await()
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

            roundRef.collection("matches").add(match).await()
        }
    }

    suspend fun saveMatchesToFirestore(session: Session) {
        val sessionRef = firestore.collection("sessions").document(session.id)

        val sessionData = mapOf(
            "id" to session.id,
            "nameOfMabar" to session.nameOfMabar,
            "totalPlayers" to session.totalPlayers,
            "totalCourts" to session.totalCourts,
            "totalTime" to session.totalTime,
            "matchDuration" to session.matchDuration,
            "createdAt" to session.createdAt
        )

        sessionRef.set(sessionData).await()

        session.teams.forEach { team ->
            val teamData = mapOf(
                "id" to team.id,
                "players" to team.players.map { player ->
                    mapOf("id" to player.id, "name" to player.name)
                }
            )
            sessionRef.collection("teams").document(team.id).set(teamData).await()
        }

        session.matches.forEach { match ->
            val matchData = mapOf(
                "id" to match.id,
                "courtNumber" to match.courtNumber,
                "round" to match.round,
                "timestamp" to match.timestamp,
                "team1" to mapOf(
                    "id" to match.team1.id,
                    "players" to match.team1.players.map { mapOf("id" to it.id, "name" to it.name) }
                ),
                "team2" to mapOf(
                    "id" to match.team2.id,
                    "players" to match.team2.players.map { mapOf("id" to it.id, "name" to it.name) }
                )
            )
            sessionRef.collection("matches").document(match.id).set(matchData).await()
        }
    }

    suspend fun getHistorySession(): List<Session> = withContext(Dispatchers.IO) {
        val snapshot = firestore.collection("sessions")
            .orderBy("createdAt", Query.Direction.DESCENDING)
            .get()
            .await()

        snapshot.documents.map { doc ->
            Session(
                id = doc.id,
                nameOfMabar = doc.getString("nameOfMabar") ?: "-",
                totalPlayers = doc.getLong("totalPlayers")?.toInt() ?: 0,
                totalCourts = doc.getLong("totalCourts")?.toInt() ?: 0,
                totalTime = doc.getLong("totalTime")?.toInt() ?: 0,
                matchDuration = doc.getLong("matchDuration")?.toInt() ?: 0,
                createdAtFormatted = convertTimestampToDate(timestamp = doc.getLong("createdAt") ?: 0)
            )
        }
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

    suspend fun getMatchRounds(sessionId: String): List<MatchRound> = withContext(Dispatchers.IO) {
        val roundsSnapshot = firestore.collection("sessions")
            .document(sessionId)
            .collection("rounds")
            .orderBy("roundNumber")
            .get()
            .await()

        roundsSnapshot.documents.mapNotNull { roundDoc ->
            val roundNumber = (roundDoc.getLong("roundNumber") ?: return@mapNotNull null).toInt()

            val matchesSnapshot = roundDoc.reference
                .collection("matches")
                .orderBy("courtNumber")
                .get()
                .await()

            val courtMatches = matchesSnapshot.documents.mapNotNull { it.toCourtMatch() }

            MatchRound(roundNumber = roundNumber, courts = courtMatches)
        }
    }

}