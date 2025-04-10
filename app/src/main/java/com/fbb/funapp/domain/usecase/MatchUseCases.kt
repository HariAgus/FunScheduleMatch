package com.fbb.funapp.domain.usecase

import com.fbb.funapp.domain.usecase.match.GenerateMatchUseCase
import com.fbb.funapp.domain.usecase.match.GenerateTeamsUseCase
import com.fbb.funapp.domain.usecase.match.GetHistorySessionUseCase
import com.fbb.funapp.domain.usecase.match.GetMatchesUseCase
import com.fbb.funapp.domain.usecase.match.GetSessionByIdUseCase
import com.fbb.funapp.domain.usecase.match.GetTeamsUseCase
import com.fbb.funapp.domain.usecase.match.SaveMatchDataUseCase
import com.fbb.funapp.domain.usecase.match.ScheduleMatchesUseCase

data class MatchUseCases(
    val generateMatchUseCase: GenerateMatchUseCase,
    val generateTeamsUseCase: GenerateTeamsUseCase,
    val saveMatchDataUseCase: SaveMatchDataUseCase,
    val scheduleMatchesUseCase: ScheduleMatchesUseCase,
    val getHistorySessionUseCase: GetHistorySessionUseCase,
    val getSessionByIdUseCase: GetSessionByIdUseCase,
    val getTeamsUseCase: GetTeamsUseCase,
    val getMatchesUseCase: GetMatchesUseCase
)