package com.fbb.funapp.domain.usecase.matchs

import com.fbb.funapp.domain.model.Session
import com.fbb.funapp.domain.repository.MatchRepository
import javax.inject.Inject

class GetSessionByIdUseCase @Inject constructor(private val repository: MatchRepository) {

    suspend operator fun invoke(sessionId: String): Session = repository.getSessionById(sessionId = sessionId)

}