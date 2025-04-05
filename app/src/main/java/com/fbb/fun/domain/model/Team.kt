package com.fbb.`fun`.domain.model

data class Team(
    val id: String = "",
    val players: List<Player> = emptyList()
)