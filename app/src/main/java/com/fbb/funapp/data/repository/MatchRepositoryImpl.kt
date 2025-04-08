package com.fbb.funapp.data.repository

import com.fbb.funapp.data.remote.FirebaseDataSource
import com.fbb.funapp.domain.model.Match
import com.fbb.funapp.domain.model.Session
import com.fbb.funapp.domain.model.Team
import com.fbb.funapp.domain.repository.MatchRepository

class MatchRepositoryImpl(private val firebaseDataSource: FirebaseDataSource) : MatchRepository {

    override suspend fun saveMatches(session: Session) {
        firebaseDataSource.saveMatchesToFirestore(session)
    }

    override suspend fun getHistorySession(): List<Session> {
        return firebaseDataSource.getHistorySession()
    }

    override suspend fun getSessionById(sessionId: String): Session {
        return firebaseDataSource.getSessionById(sessionId = sessionId)
    }

    override suspend fun getTeams(sessionId: String): List<Team> {
        return firebaseDataSource.getTeams(sessionId = sessionId)
    }

    override suspend fun getMatches(sessionId: String): List<Match> {
        return firebaseDataSource.getMatches(sessionId = sessionId)
    }
}