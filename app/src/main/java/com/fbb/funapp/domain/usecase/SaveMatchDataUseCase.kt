package com.fbb.funapp.domain.usecase

import com.fbb.funapp.domain.model.Session
import com.fbb.funapp.domain.repository.MatchRepository

class SaveMatchDataUseCase(private val repository: MatchRepository) {

    suspend operator fun invoke(session: Session) {
        repository.saveMatches(session = session)
    }

}