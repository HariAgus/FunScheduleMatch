package com.fbb.funapp.domain.usecase

import com.fbb.funapp.domain.usecase.match.GenerateMatchUseCase
import com.fbb.funapp.domain.usecase.match.GetHistorySessionUseCase
import com.fbb.funapp.domain.usecase.match.GetMatchesUseCase
import com.fbb.funapp.domain.usecase.match.GetSessionByIdUseCase
import com.fbb.funapp.domain.usecase.match.GetTeamsUseCase

data class MatchUseCases(
    val generateMatchUseCase: GenerateMatchUseCase,
    val getHistorySessionUseCase: GetHistorySessionUseCase,
    val getSessionByIdUseCase: GetSessionByIdUseCase,
    val getTeamsUseCase: GetTeamsUseCase,
    val getMatchesUseCase: GetMatchesUseCase
)