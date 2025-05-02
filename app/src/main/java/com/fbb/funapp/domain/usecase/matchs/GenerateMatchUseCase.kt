package com.fbb.funapp.domain.usecase.matchs

import com.fbb.funapp.domain.model.CourtMatch
import com.fbb.funapp.domain.model.MatchRound
import com.fbb.funapp.domain.model.Player
import com.fbb.funapp.domain.model.Session
import com.fbb.funapp.domain.model.Team
import com.fbb.funapp.domain.repository.MatchRepository
import javax.inject.Inject

class GenerateMatchUseCase @Inject constructor(private val repository: MatchRepository) {

    suspend operator fun invoke(session: Session) {
        val totalRounds = session.totalTime / session.matchDuration
        val players = (1..session.totalPlayers).map { Player(id = it.toString()) }.toMutableList()
        val matchRounds = mutableListOf<MatchRound>()
        val rotatedPlayers = players.toMutableList()

        repository.saveSession(session = session)

        for (round in 0 until totalRounds) {
            val roundMatches = mutableListOf<CourtMatch>()
            val isLastRound = round == totalRounds - 1

            rotatedPlayers.sortWith(compareBy({ it.gamesPlayed }, { it.lastPlayedRound }))
            val requiredPlayers = session.totalCourts * 4
            val activeCandidates = rotatedPlayers.take(requiredPlayers).filter {
                isLastRound || round - it.lastPlayedRound >= 1
            }.toMutableList()

            while (activeCandidates.size >= 4 && roundMatches.size < session.totalCourts) {
                val selected = activeCandidates.take(4).shuffled()

                val combinations = listOf(
                    Team(
                        player1 = selected[0], player2 = selected[1]
                    ) to Team(
                        player1 = selected[2],
                        player2 = selected[3]
                    ),
                    Team(player1 = selected[0], player2 = selected[2]) to Team(
                        player1 = selected[1],
                        player2 = selected[3]
                    ),
                    Team(player1 = selected[0], player2 = selected[3]) to Team(
                        player1 = selected[1],
                        player2 = selected[2]
                    )
                ).shuffled()

                val (team1, team2) = combinations.first()
                roundMatches.add(CourtMatch(team1 = team1, team2 = team2))

                val usedPlayers = listOf(team1.player1, team1.player2, team2.player1, team2.player2)
                usedPlayers.forEach { player ->
                    player.gamesPlayed++
                    player.lastPlayedRound = round
                    activeCandidates.remove(player)
                }
            }

            if (roundMatches.isNotEmpty()) {
                matchRounds.add(MatchRound(courts = roundMatches))
            }

            rotatedPlayers.add(rotatedPlayers.removeAt(0))

            repository.saveMatchRound(sessionId = session.id, roundNumber = round + 1, matches = roundMatches)
        }

        repository.savePlayers(sessionId = session.id, players = players)
    }

}