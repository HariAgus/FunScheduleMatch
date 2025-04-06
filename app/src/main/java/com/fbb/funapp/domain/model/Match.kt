package com.fbb.funapp.domain.model

data class Match(
    val id: String = "",
    val team1: Team = Team(),
    val team2: Team = Team(),
    val courtNumber: Int = 1,
    val round: Int = 1,
    val timestamp: Long = System.currentTimeMillis()
)