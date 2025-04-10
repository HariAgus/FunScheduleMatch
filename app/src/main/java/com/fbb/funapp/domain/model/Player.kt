package com.fbb.funapp.domain.model

data class Player(
    val id: String = "",
    val name: String = "",
    var gamesPlayed: Int = 0,
    var lastPlayedRound: Int = -2
)