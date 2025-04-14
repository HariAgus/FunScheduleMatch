package com.fbb.funapp.presentation.screen.match

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fbb.funapp.domain.model.Match
import com.fbb.funapp.domain.model.Session
import com.fbb.funapp.domain.model.Team
import com.fbb.funapp.domain.usecase.match.GenerateMatchUseCase
import com.fbb.funapp.domain.usecase.match.GetHistorySessionUseCase
import com.fbb.funapp.domain.usecase.match.GetMatchesUseCase
import com.fbb.funapp.domain.usecase.match.GetSessionByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MatchViewModel @Inject constructor(
    private val generateMatchUseCase: GenerateMatchUseCase,
    private val getHistorySessionUseCase: GetHistorySessionUseCase,
    private val getSessionByIdUseCase: GetSessionByIdUseCase,
    private val getMatchesUseCase: GetMatchesUseCase,
) : ViewModel() {

    private val sessionId = "713fe700-e6b8-4e8e-b40c-a152922e171a"

    init {
        getSessionById(sessionId = sessionId)
        getMatchesBySession(sessionId = sessionId)
    }

    private val _sessions = mutableStateOf<List<Session>>(emptyList())
    val sessions: State<List<Session>> = _sessions

    private val _session = mutableStateOf(Session())
    val session: State<Session> = _session

    private val _teams = mutableStateOf<List<Team>>(emptyList())
    val teams: State<List<Team>> = _teams

    private val _matches = mutableStateOf<List<Match>>(emptyList())
    val matches: State<List<Match>> = _matches

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

    fun getMatchesBySession(sessionId: String) {
        viewModelScope.launch {
            val result = getMatchesUseCase.invoke(sessionId = sessionId)
            _matches.value = result
        }
    }

}

sealed class MatchScheduleState {
    data object Idle : MatchScheduleState()
    data object Loading : MatchScheduleState()
    data object Success : MatchScheduleState()
    data class Error(val message: String) : MatchScheduleState()
}
