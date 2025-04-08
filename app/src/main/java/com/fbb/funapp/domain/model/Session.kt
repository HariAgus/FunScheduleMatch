package com.fbb.funapp.domain.model

data class Session(
    val id: String = "",
    val nameOfMabar: String = "",
    val totalPlayers: Int = 0,
    val totalCourts: Int = 0,
    val totalTime: Int = 0,
    val matchDuration: Int = 0,
    val teams: List<Team> = emptyList(),
    val matches: List<Match> = emptyList(),
    val createdAtFormatted: String = "-",
    val createdAt: Long = System.currentTimeMillis(),
)