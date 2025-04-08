package com.fbb.funapp.domain.usecase.match

import androidx.annotation.RequiresApi
import com.fbb.funapp.domain.model.Match
import com.fbb.funapp.domain.model.Team
import java.util.Collections
import java.util.UUID
import javax.inject.Inject

class ScheduleMatchesUseCase @Inject constructor() {

    @RequiresApi(35)
    operator fun invoke(teams: List<Team>, courts: Int, totalMatches: Int): List<Match> {
        val schedule = mutableListOf<Match>()
        val queue = teams.toMutableList()

        repeat(totalMatches) { index ->
            if (queue.size < 2) return@repeat

            val team1 = queue.removeFirst()
            val team2 = queue.removeFirst()

            val courtNumber = (index % courts) + 1
            val round = (index / courts) + 1

            val match = Match(
                id = UUID.randomUUID().toString(),
                team1 = team1,
                team2 = team2,
                courtNumber = courtNumber,
                round = round,
                timestamp = System.currentTimeMillis() + index * 15 * 60 * 1000L
            )

            schedule.add(match)

            queue.add(team1)
            queue.add(team2)
        }

        return schedule
    }
}



