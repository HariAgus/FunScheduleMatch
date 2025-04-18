package com.fbb.funapp.data.repository

import com.fbb.funapp.data.remote.FirebaseDataSource
import com.fbb.funapp.domain.model.CourtMatch
import com.fbb.funapp.domain.model.MatchRound
import com.fbb.funapp.domain.model.Player
import com.fbb.funapp.domain.model.Session
import com.fbb.funapp.domain.model.Team
import com.fbb.funapp.domain.repository.MatchRepository

class MatchRepositoryImpl(private val firebaseDataSource: FirebaseDataSource) : MatchRepository {

    override suspend fun saveSession(session: Session) {
        firebaseDataSource.saveSession(session = session)
    }

    override suspend fun savePlayer(sessionId: String, player: Player) {
        firebaseDataSource.savePlayer(sessionId = sessionId, player = player)
    }

    override suspend fun saveMatchRound(sessionId: String, roundNumber: Int, matches: List<CourtMatch>) {
        firebaseDataSource.saveMatchRound(sessionId = sessionId, roundNumber = roundNumber, matches = matches)
    }

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

    override suspend fun getMatchRounds(sessionId: String): List<MatchRound> {
        return firebaseDataSource.getMatchRounds(sessionId = sessionId)
    }
}