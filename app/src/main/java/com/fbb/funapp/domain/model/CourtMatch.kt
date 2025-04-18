package com.fbb.funapp.domain.model

data class CourtMatch(
    val courtNumber: Int = 0,
    val team1: Team,
    val team2: Team
)