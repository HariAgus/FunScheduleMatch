package com.fbb.funapp.domain.repository

import com.fbb.funapp.domain.model.CourtMatch
import com.fbb.funapp.domain.model.MatchRound
import com.fbb.funapp.domain.model.Player
import com.fbb.funapp.domain.model.Session
import com.fbb.funapp.domain.model.Team

interface MatchRepository {
    suspend fun saveSession(session: Session)
    suspend fun savePlayers(sessionId: String, players: List<Player>)
    suspend fun saveMatchRound(sessionId: String, roundNumber: Int, matches: List<CourtMatch>)
    suspend fun getHistorySession(): List<Session>
    suspend fun getSessionById(sessionId: String): Session
    suspend fun getMatchRounds(sessionId: String): List<MatchRound>
}