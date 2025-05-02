package com.fbb.funapp.domain.usecase.matchs

import com.fbb.funapp.domain.model.MatchRound
import com.fbb.funapp.domain.repository.MatchRepository
import javax.inject.Inject

class GetMatchRoundUseCase @Inject constructor(private val repository: MatchRepository) {

    suspend operator fun invoke(sessionId: String): List<MatchRound> = repository.getMatchRounds(sessionId = sessionId)

}