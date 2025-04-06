package com.fbb.funapp.presentation.screen.match

import androidx.annotation.RequiresApi
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fbb.funapp.domain.model.Match
import com.fbb.funapp.domain.model.Player
import com.fbb.funapp.domain.model.Session
import com.fbb.funapp.domain.usecase.GenerateTeamsUseCase
import com.fbb.funapp.domain.usecase.SaveMatchDataUseCase
import com.fbb.funapp.domain.usecase.ScheduleMatchesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class MatchViewModel @Inject constructor(
    private val generateTeams: GenerateTeamsUseCase,
    private val scheduleMatchesUseCase: ScheduleMatchesUseCase,
    private val saveMatchDataUseCase: SaveMatchDataUseCase
) : ViewModel() {

    private val _matches = mutableStateOf<List<Match>>(emptyList())
    val matches: State<List<Match>> = _matches

    @RequiresApi(35)
    fun createSchedule(nameOfMabar: String, courts: Int, playerCount: Int, totalTime: Int, durationPerMatch: Int) {
        val players = (1..playerCount).map {
            Player(id = UUID.randomUUID().toString(), name = "Player $it")
        }

        val teams = generateTeams(players)
        val totalMatches = totalTime / durationPerMatch
        val matches = scheduleMatchesUseCase(
            teams = teams,
            courts = courts,
            totalMatches = totalMatches
        )

        _matches.value = matches

        val session = Session(
            id = UUID.randomUUID().toString(),
            nameOfMabar = nameOfMabar,
            totalCourts = courts,
            totalPlayers = playerCount,
            totalTime = totalTime,
            matchDuration = durationPerMatch,
            teams = teams,
            matches = matches,
            createdAt = System.currentTimeMillis()
        )

        viewModelScope.launch {
            saveMatchDataUseCase(session = session)
        }
    }


}