package com.fbb.funapp.domain.repository

import com.fbb.funapp.domain.model.Session

interface MatchRepository {
    suspend fun saveMatches(session: Session)
}