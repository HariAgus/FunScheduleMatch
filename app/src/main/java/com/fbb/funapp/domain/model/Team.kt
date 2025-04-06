package com.fbb.funapp.domain.model

data class Team(
    val id: String = "",
    val players: List<Player> = emptyList()
)