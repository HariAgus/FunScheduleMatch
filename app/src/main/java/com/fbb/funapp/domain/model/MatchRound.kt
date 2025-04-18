package com.fbb.funapp.domain.model

data class MatchRound(
    val roundNumber: Int = 0,
    val courts: List<CourtMatch>
)