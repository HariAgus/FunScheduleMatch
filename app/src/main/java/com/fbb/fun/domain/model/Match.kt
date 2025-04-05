package com.fbb.`fun`.domain.model

data class Match(
    val id: String = "",
    val teamA: Team = Team(),
    val teamB: Team = Team(),
    val courtNumber: Int = 1,
    val round: Int = 1,
    val timestamp: Long = System.currentTimeMillis()
)