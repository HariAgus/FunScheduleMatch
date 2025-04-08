package com.fbb.funapp.domain.usecase.match

import com.fbb.funapp.domain.model.Session
import com.fbb.funapp.domain.repository.MatchRepository
import javax.inject.Inject

class SaveMatchDataUseCase @Inject constructor(private val repository: MatchRepository) {

    suspend operator fun invoke(session: Session) {
        repository.saveMatches(session = session)
    }

}