package com.fbb.funapp.domain.usecase.match

import com.fbb.funapp.domain.model.Team
import com.fbb.funapp.domain.repository.MatchRepository
import javax.inject.Inject

class GetTeamsUseCase @Inject constructor(private val repository: MatchRepository) {

    suspend operator fun invoke(sessionId: String): List<Team> = repository.getTeams(sessionId = sessionId)

}