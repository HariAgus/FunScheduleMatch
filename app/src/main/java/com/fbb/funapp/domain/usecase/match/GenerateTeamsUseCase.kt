package com.fbb.funapp.domain.usecase.match

import androidx.annotation.RequiresApi
import com.fbb.funapp.domain.model.Player
import com.fbb.funapp.domain.model.Team
import java.util.UUID
import javax.inject.Inject

class GenerateTeamsUseCase @Inject constructor() {

    operator fun invoke(players: List<Player>): List<Team> {
        val shuffled = players.shuffled()
        val teams = mutableListOf<Team>()
        val tempTeam = mutableListOf<Player>()

        shuffled.forEach { player ->
            tempTeam.add(player)
            if (tempTeam.size == 2) {
                teams.add(
                    Team(
                        id = UUID.randomUUID().toString(),
                        players = tempTeam.toList()
                    )
                )
                tempTeam.clear()
            }
        }

        if (tempTeam.isNotEmpty()) {
            teams.add(
                Team(
                    id = UUID.randomUUID().toString(),
                    players = tempTeam.toList()
                )
            )
        }

        return teams
    }
}
