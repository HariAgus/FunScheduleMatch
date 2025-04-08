package com.fbb.funapp.domain.model

data class Match(
    val id: String = "",
    val team1: Team = Team(),
    val team2: Team = Team(),
    val courtNumber: Int = 0,
    val round: Int = 0,
    val timestamp: Long = System.currentTimeMillis()
)