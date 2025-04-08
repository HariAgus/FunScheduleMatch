package com.fbb.funapp.utils

import com.fbb.funapp.domain.model.Match
import com.fbb.funapp.domain.model.Player
import com.fbb.funapp.domain.model.Team
import com.google.firebase.firestore.DocumentSnapshot

fun DocumentSnapshot.toTeam(): Team? {
    return try {
        val rawPlayers = this.get("players") as? List<*>

        val players = rawPlayers?.mapNotNull { player ->
            if (player is Map<*, *>) {
                Player(
                    id = player["id"] as? String ?: "",
                    name = player["name"] as? String ?: ""
                )
            } else null
        } ?: emptyList()

        Team(
            id = this.id,
            players = players
        )
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}


fun DocumentSnapshot.toMatch(): Match? {
    return try {
        // team1
        val team1Map = this.get("team1") as? Map<*, *>
        val team1Id = team1Map?.get("id") as? String ?: ""
        val team1PlayersRaw = team1Map?.get("players") as? List<*>

        val team1Players = team1PlayersRaw?.mapNotNull { player ->
            if (player is Map<*, *>) {
                Player(
                    id = player["id"] as? String ?: "",
                    name = player["name"] as? String ?: ""
                )
            } else null
        } ?: emptyList()

        // team2
        val team2Map = this.get("team2") as? Map<*, *>
        val team2Id = team2Map?.get("id") as? String ?: ""
        val team2PlayersRaw = team2Map?.get("players") as? List<*>

        val team2Players = team2PlayersRaw?.mapNotNull { player ->
            if (player is Map<*, *>) {
                Player(
                    id = player["id"] as? String ?: "",
                    name = player["name"] as? String ?: ""
                )
            } else null
        } ?: emptyList()

        Match(
            id = this.id,
            team1 = Team(id = team1Id, players = team1Players),
            team2 = Team(id = team2Id, players = team2Players),
            courtNumber = this.getLong("courtNumber")?.toInt() ?: 0,
            round = this.getLong("round")?.toInt() ?: 0,
            timestamp = this.getLong("timestamp") ?: 0L
        )
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}
