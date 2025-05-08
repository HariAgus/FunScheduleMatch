package com.fbb.funapp.utils

import com.fbb.funapp.domain.model.CourtMatch
import com.fbb.funapp.domain.model.Player
import com.fbb.funapp.domain.model.Session
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


fun DocumentSnapshot.toSession(): Session = Session(
    id = id,
    nameOfMabar = getString("nameOfMabar") ?: "-",
    totalPlayers = getLong("totalPlayers")?.toInt() ?: 0,
    totalCourts = getLong("totalCourts")?.toInt() ?: 0,
    totalTime = getLong("totalTime")?.toInt() ?: 0,
    matchDuration = getLong("matchDuration")?.toInt() ?: 0,
    date = getString("date") ?: "-",
    createdAtFormatted = convertTimestampToDate(getLong("createdAt") ?: 0)
)


fun DocumentSnapshot.toCourtMatch(): CourtMatch? {
    val courtNumber = this.getLong("courtNumber")?.toInt() ?: return null

    val team1Map = this.get("team1") as? Map<String, Any> ?: return null
    val team2Map = this.get("team2") as? Map<String, Any> ?: return null

    return CourtMatch(
        courtNumber = courtNumber,
        team1 = team1Map.toTeam(),
        team2 = team2Map.toTeam()
    )
}

fun Map<String, Any>.toTeam(): Team {
    val player1Id = this["player1Id"] as? String ?: ""
    val player2Id = this["player2Id"] as? String ?: ""

    return Team(
        players = emptyList(),
        player1 = Player(id = player1Id),
        player2 = Player(id = player2Id)
    )
}

fun Map<String, Any>.toPlayer(): Player {
    return Player(
        player1Id = this["player1Id"] as? String ?: "",
        player2Id = this["player2Id"] as? String ?: "",
        name = this["name"] as? String ?: "",
        gamesPlayed = (this["gamesPlayed"] as? Long)?.toInt() ?: 0,
        lastPlayedRound = (this["lastPlayedRound"] as? Long)?.toInt() ?: -2
    )
}


