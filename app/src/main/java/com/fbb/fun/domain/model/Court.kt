package com.fbb.`fun`.domain.model

data class Court(
    val number: Int = 1,
    val matches: List<Match> = emptyList()
)