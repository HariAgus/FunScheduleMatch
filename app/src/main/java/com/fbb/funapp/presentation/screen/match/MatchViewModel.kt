package com.fbb.funapp.presentation.screen.match

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fbb.funapp.domain.model.MatchRound
import com.fbb.funapp.domain.model.Session
import com.fbb.funapp.domain.model.Team
import com.fbb.funapp.domain.usecase.match.GenerateMatchUseCase
import com.fbb.funapp.domain.usecase.match.GetHistorySessionUseCase
import com.fbb.funapp.domain.usecase.match.GetMatchRoundUseCase
import com.fbb.funapp.domain.usecase.match.GetSessionByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MatchViewModel @Inject constructor(
    private val generateMatchUseCase: GenerateMatchUseCase,
    private val getHistorySessionUseCase: GetHistorySessionUseCase,
    private val getSessionByIdUseCase: GetSessionByIdUseCase,
    private val getMatchRoundUseCase: GetMatchRoundUseCase,
) : ViewModel() {

    private val sessionId = "a06f427c-27b1-4348-a6f7-90a7658bf7db"

    init {
        getSessionById(sessionId = sessionId)
        getMatchRounds(sessionId = sessionId)
    }

    private val _sessions = mutableStateOf<List<Session>>(emptyList())
    val sessions: State<List<Session>> = _sessions

    private val _session = mutableStateOf(Session())
    val session: State<Session> = _session

    private val _teams = mutableStateOf<List<Team>>(emptyList())
    val teams: State<List<Team>> = _teams

    private val _matchRounds = mutableStateOf<List<MatchRound>>(emptyList())
    val matchRounds: State<List<MatchRound>> = _matchRounds

    fun createSchedule(
        sessionId: String,
        nameOfMabar: String,
        courts: Int,
        playerCount: Int,
        totalTime: Int,
        durationPerMatch: Int
    ) {
        viewModelScope.launch {
            try {
                val session = Session(
                    id = sessionId,
                    nameOfMabar = nameOfMabar,
                    totalCourts = courts,
                    totalPlayers = playerCount,
                    totalTime = totalTime,
                    matchDuration = durationPerMatch,
                    createdAt = System.currentTimeMillis()
                )
                generateMatchUseCase.invoke(session = session)
            } catch (e: Exception) {
                Log.d("ViewModel", "createSchedule: ${e.message}")
            }
        }
    }

    fun getHistoryMatches() {
        viewModelScope.launch {
            val result = getHistorySessionUseCase.invoke()
            _sessions.value = result
        }
    }

    fun getSessionById(sessionId: String) {
        viewModelScope.launch {
            val result = getSessionByIdUseCase.invoke(sessionId = sessionId)
            _session.value = result
            Log.d("VIEWMODEL", "getSessionById: $result")
        }
    }

    fun getMatchRounds(sessionId: String) {
        viewModelScope.launch {
            val result = getMatchRoundUseCase.invoke(sessionId = sessionId)
            _matchRounds.value = result

            Log.d("VIEWMODEL", "getMatchRounds: $result")
        }
    }

}

sealed class MatchScheduleState {
    data object Idle : MatchScheduleState()
    data object Loading : MatchScheduleState()
    data object Success : MatchScheduleState()
    data class Error(val message: String) : MatchScheduleState()
}
