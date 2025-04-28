package com.fbb.funapp.domain.usecase

import com.fbb.funapp.domain.usecase.match.GenerateMatchUseCase
import com.fbb.funapp.domain.usecase.match.GetHistorySessionUseCase
import com.fbb.funapp.domain.usecase.match.GetMatchRoundUseCase
import com.fbb.funapp.domain.usecase.match.GetSessionByIdUseCase

data class MatchUseCases(
    val generateMatchUseCase: GenerateMatchUseCase,
    val getHistorySessionUseCase: GetHistorySessionUseCase,
    val getSessionByIdUseCase: GetSessionByIdUseCase,
    val getMatchesUseCase: GetMatchRoundUseCase
)