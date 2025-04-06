package com.fbb.funapp.data.remote

import com.fbb.funapp.domain.model.Session
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class FirebaseDataSource {

    suspend fun saveMatchesToFirestore(session: Session) {
        val firestore = FirebaseFirestore.getInstance()
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


}