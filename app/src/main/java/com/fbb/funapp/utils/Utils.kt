package com.fbb.funapp.utils

import com.fbb.funapp.domain.model.MatchRound
import com.fbb.funapp.domain.model.Session
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun convertTimestampToDate(timestamp: Long): String {
    val date = Date(timestamp)
    val format = SimpleDateFormat("dd MMMM yyyy", Locale("id", "ID"))
    return format.format(date)
}

fun formatMatchRoundsForSharing(session: Session, rounds: List<MatchRound>): String {
    val header = """
        *${session.nameOfMabar.uppercase()}*
        
        📅 ${session.createdAtFormatted}
    """.trimIndent()

    val matchText = rounds.joinToString(separator = "\n\n") { round ->
        val roundHeader = "🔢 *Match ${round.roundNumber}*"
        val courtsText = round.courts.joinToString(separator = "\n") { court ->
            val team1Names = "Player ${court.team1.player1.id} & ${court.team1.player2.id}"
            val team2Names = "Player ${court.team2.player1.id} & ${court.team2.player2.id}"
            "Court ${court.courtNumber}: $team1Names *VS* $team2Names"
        }
        "$roundHeader\n$courtsText"
    }

    return "$header\n\n$matchText"
}

