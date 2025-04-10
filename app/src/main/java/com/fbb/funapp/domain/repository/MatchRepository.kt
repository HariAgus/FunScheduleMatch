package com.fbb.funapp.domain.repository

import com.fbb.funapp.domain.model.CourtMatch
import com.fbb.funapp.domain.model.Match
import com.fbb.funapp.domain.model.Player
import com.fbb.funapp.domain.model.Session
import com.fbb.funapp.domain.model.Team

interface MatchRepository {
    suspend fun saveSession(session: Session)
    suspend fun savePlayer(sessionId: String, player: Player)
    suspend fun saveMatchRound(sessionId: String, roundNumber: Int, matches: List<CourtMatch>)
    suspend fun saveMatches(session: Session)
    suspend fun getHistorySession(): List<Session>
    suspend fun getSessionById(sessionId: String): Session
    suspend fun getTeams(sessionId: String): List<Team>
    suspend fun getMatches(sessionId: String): List<Match>
}