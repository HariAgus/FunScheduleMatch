package com.fbb.funapp.presentation.screen.match

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fbb.funapp.domain.model.MatchRound
import com.fbb.funapp.domain.model.Session
import com.fbb.funapp.domain.model.Team
import com.fbb.funapp.domain.usecase.match.GenerateMatchUseCase
import com.fbb.funapp.domain.usecase.match.GetHistorySessionUseCase
import com.fbb.funapp.domain.usecase.match.GetMatchRoundUseCase
import com.fbb.funapp.domain.usecase.match.GetSessionByIdUseCase
import com.fbb.funapp.utils.Constant
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MatchViewModel @Inject constructor(
    private val generateMatchUseCase: GenerateMatchUseCase,
    private val getHistorySessionUseCase: GetHistorySessionUseCase,
    private val getSessionByIdUseCase: GetSessionByIdUseCase,
    private val getMatchRoundUseCase: GetMatchRoundUseCase,
    saveStatedHandle: SavedStateHandle
) : ViewModel() {

    private val _matchScheduleState = MutableStateFlow<MatchScheduleState>(MatchScheduleState.Idle)
    val matchState: StateFlow<MatchScheduleState> = _matchScheduleState

    private val _historyScheduleState = MutableStateFlow<HistoryScheduleState>(HistoryScheduleState.Idle)
    val historyScheduleState: StateFlow<HistoryScheduleState> = _historyScheduleState

    private val _detailScheduleState = MutableStateFlow<DetailScheduleState>(DetailScheduleState.Idle)
    val detailScheduleState: StateFlow<DetailScheduleState> = _detailScheduleState

    private val _matchRoundState = MutableStateFlow<MatchRoundState>(MatchRoundState.Idle)
    val matchRoundState: StateFlow<MatchRoundState> = _matchRoundState

    private var _sessionId: MutableStateFlow<String?> = MutableStateFlow(null)
    val sessionId: StateFlow<String?> = _sessionId

    private val _isFormValid = MutableStateFlow(false)
    val isFormValid: StateFlow<Boolean> = _isFormValid

    init {
        viewModelScope.launch(Dispatchers.IO) {
            _sessionId.value = saveStatedHandle.get<String>(Constant.SESSION_ID) ?: ""
        }
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
        date: String,
        durationPerMatch: Int
    ) {
        viewModelScope.launch {
            _matchScheduleState.value = MatchScheduleState.Loading
            try {
                val session = Session(
                    id = sessionId,
                    nameOfMabar = nameOfMabar,
                    totalCourts = courts,
                    totalPlayers = playerCount,
                    totalTime = totalTime,
                    matchDuration = durationPerMatch,
                    date = date,
                    createdAt = System.currentTimeMillis()
                )
                generateMatchUseCase.invoke(session = session)

                _matchScheduleState.value = MatchScheduleState.Success
            } catch (e: Exception) {
                _matchScheduleState.value = MatchScheduleState.Error("Terjadi kesalahan, silahkan coba lagi")
            }
        }
    }

    fun getHistoryMatches() {
        viewModelScope.launch {
            _historyScheduleState.value = HistoryScheduleState.Loading
            try {
                val result = getHistorySessionUseCase.invoke()
                _sessions.value = result
                _historyScheduleState.value = HistoryScheduleState.Success
            } catch (e: Exception) {
                _historyScheduleState.value = HistoryScheduleState.Error("Terjadi kesalahan, silahkan coba lagi")
            }
        }
    }

    fun getSessionById(sessionId: String) {
        viewModelScope.launch {
            _detailScheduleState.value = DetailScheduleState.Loading
            try {
                val result = getSessionByIdUseCase.invoke(sessionId = sessionId)
                _session.value = result

                _detailScheduleState.value = DetailScheduleState.Success
            } catch (e: Exception) {
                _detailScheduleState.value = DetailScheduleState.Error("Terjadi kesalahan, silahkan coba lagi")
            }
        }
    }

    fun getMatchRounds(sessionId: String) {
        viewModelScope.launch {
            _matchRoundState.value = MatchRoundState.Loading
            try {
                val result = getMatchRoundUseCase.invoke(sessionId = sessionId)
                _matchRounds.value = result

                _matchRoundState.value = MatchRoundState.Success
            } catch (e: Exception) {
                _matchRoundState.value = MatchRoundState.Error("Terjadi kesalahan, silahkan coba lagi")
            }
        }
    }

    fun resetMatchScheduleState() {
        _matchScheduleState.value = MatchScheduleState.Idle
    }

    fun resetHistoryScheduleState() {
        _historyScheduleState.value = HistoryScheduleState.Idle
    }

    fun resetDetailScheduleState() {
        _detailScheduleState.value = DetailScheduleState.Idle
    }

    fun resetMatchRoundState() {
        _matchRoundState.value = MatchRoundState.Idle
    }

    fun validateForm(
        name: String,
        court: String,
        players: String,
        totalTime: String,
        durationPerMatch: String,
        date: String
    ) {
        _isFormValid.value = name.isNotBlank() && court.toIntOrNull() != null &&
                players.toIntOrNull() != null && totalTime.toIntOrNull() != null &&
                durationPerMatch.toIntOrNull() != null && date.isNotBlank()
    }

}

sealed class MatchScheduleState {
    data object Idle : MatchScheduleState()
    data object Loading : MatchScheduleState()
    data object Success : MatchScheduleState()
    data class Error(val message: String) : MatchScheduleState()
}

sealed class HistoryScheduleState {
    data object Idle : HistoryScheduleState()
    data object Loading : HistoryScheduleState()
    data object Success : HistoryScheduleState()
    data class Error(val message: String) : HistoryScheduleState()
}

sealed class DetailScheduleState {
    data object Idle : DetailScheduleState()
    data object Loading : DetailScheduleState()
    data object Success : DetailScheduleState()
    data class Error(val message: String) : DetailScheduleState()
}

sealed class MatchRoundState {
    data object Idle : MatchRoundState()
    data object Loading : MatchRoundState()
    data object Success : MatchRoundState()
    data class Error(val message: String) : MatchRoundState()
}
