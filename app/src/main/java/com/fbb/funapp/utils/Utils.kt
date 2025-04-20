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
        
        📅 ${session.date}
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

fun randomLoadingTitle(): String {
    val titles = listOf(
        "Sabar yaa....",
        "Tunggu sebentar...",
        "Lagi diolah nih...",
        "Match-nya lagi dimasak 🍳",
        "Siapkan raketmu 🏸",
        "Loading dulu ya...",
        "Cek strategi dulu...",
        "Almost there!",
        "Dikit lagi kelar...",
        "Nyusun tim dulu nih...",
        "Tahan dulu semangatmu 🔥"
    )
    return titles.random()
}


fun randomLoadingText(): String {
    val loadingMessages = listOf(
        "Aku lagi buatin match yang cocok dan adil buat kamu",
        "Lagi mikir formasi terbaik biar kamu makin puas mainnya",
        "Tunggu bentar ya, aku lagi ngacak-ngacak strategi match-nya 🔄",
        "Match-nya lagi aku racik biar semua kebagian main!",
        "Bentar yaa... aku pastikan semua tim seimbang ⚖️",
        "Match-nya lagi digodok biar seru abis 🍲",
        "Cek keseimbangan tim dulu nih, bentar ya...",
        "Formasi lagi di-shuffle... kamu bakal suka kok!",
        "Kamu santai aja, aku yang atur match-nya 😎",
        "Nyusun match-nya biar nggak ada yang jomblo di lapangan 🏸",
        "Lagi nyari lawan yang sepadan buat kamu nih 🔍"
    )
    return loadingMessages.random()
}


fun randomEmptyStateTitle(): String {
    val titles = listOf(
        "Kosong... seperti harapan aku ke dia 😔",
        "Belum ada data, kayak dia yang belum peka",
        "Ini kosong... tapi nggak se-kosong hati yang ditinggal pas sayang-sayangnya",
        "Data nggak ada, tapi kenangan sama dia masih penuh",
        "Belum ada jadwal, kayak hubungan kita yang nggak pernah jelas",
        "Ini kosong ya... tapi nggak seperih waktu dia bilang 'kita temenan aja'",
        "Nggak ada data. Sama kayak chat kamu yang cuma dibaca doang",
        "Belum ada yang masuk... mirip kamu di hatinya 😢",
        "Data belum ada, tapi luka lama masih ada",
        "Sepi banget... mirip inbox kamu waktu nunggu dia nge-chat"
    )
    return titles.random()
}



