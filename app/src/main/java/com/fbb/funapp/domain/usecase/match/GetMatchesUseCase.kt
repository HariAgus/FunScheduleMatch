package com.fbb.funapp.domain.usecase.match

import com.fbb.funapp.domain.model.Match
import com.fbb.funapp.domain.repository.MatchRepository
import javax.inject.Inject

class GetMatchesUseCase @Inject constructor(private val repository: MatchRepository) {

    suspend operator fun invoke(sessionId: String): List<Match> = repository.getMatches(sessionId = sessionId)

}