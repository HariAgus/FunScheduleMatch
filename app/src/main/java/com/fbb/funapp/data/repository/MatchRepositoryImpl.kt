package com.fbb.funapp.data.repository

import com.fbb.funapp.data.remote.FirebaseDataSource
import com.fbb.funapp.domain.model.Session
import com.fbb.funapp.domain.repository.MatchRepository

class MatchRepositoryImpl(private val firebaseDataSource: FirebaseDataSource) : MatchRepository {

    override suspend fun saveMatches(session: Session) {
        firebaseDataSource.saveMatchesToFirestore(session)
    }
}