package com.fbb.funapp.domain.model

data class Team(
    val id: String = "",
    val players: List<Player> = emptyList(),
    val player1: Player = Player(),
    val player2: Player = Player(),
)