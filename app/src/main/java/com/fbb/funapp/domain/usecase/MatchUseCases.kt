package com.fbb.funapp.domain.usecase

import com.fbb.funapp.domain.usecase.matchs.GenerateMatchUseCase
import com.fbb.funapp.domain.usecase.matchs.GetHistorySessionUseCase
import com.fbb.funapp.domain.usecase.matchs.GetMatchRoundUseCase
import com.fbb.funapp.domain.usecase.matchs.GetSessionByIdUseCase

data class MatchUseCases(
    val generateMatchUseCase: GenerateMatchUseCase,
    val getHistorySessionUseCase: GetHistorySessionUseCase,
    val getSessionByIdUseCase: GetSessionByIdUseCase,
    val getMatchesUseCase: GetMatchRoundUseCase
)