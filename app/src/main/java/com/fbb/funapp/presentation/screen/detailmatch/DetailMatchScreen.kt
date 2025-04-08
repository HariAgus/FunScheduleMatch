package com.fbb.funapp.presentation.screen.detailmatch

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.fbb.funapp.presentation.components.CardDistribution
import com.fbb.funapp.presentation.components.CardFormResult
import com.fbb.funapp.presentation.components.CardMatchSchedule
import com.fbb.funapp.presentation.screen.match.MatchViewModel
import com.fbb.funapp.presentation.ui.theme.BgColorWhiteSecond
import com.fbb.funapp.presentation.ui.theme.TextColorPrimary
import com.fbb.funapp.presentation.ui.theme.TypographyStyle

@Composable
fun DetailMatchScreen(modifier: Modifier = Modifier, viewModel: MatchViewModel = hiltViewModel()) {

//    LaunchedEffect(key1 = viewModel.sessionId) {
//        viewModel.getSessionById(sessionId = viewModel.sessionId)
//        viewModel.getTeamsBySession(sessionId = viewModel.sessionId)
//        viewModel.getMatchesBySession(sessionId = viewModel.sessionId)
//    }
    val session = viewModel.session.value

    Scaffold(
        modifier = modifier,
        containerColor = BgColorWhiteSecond
    ) { paddingValues ->
        Column(
            modifier = Modifier.padding(paddingValues = paddingValues)
        ) {
            Text(
                modifier = Modifier.padding(start = 16.dp, top = 16.dp),
                text = "Match Schedule",
                style = TypographyStyle.titleLarge.copy(fontSize = 22.sp, color = TextColorPrimary)
            )

            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp),
                color = Color.White,
                shadowElevation = 1.dp,
                tonalElevation = 1.dp
            ) {
                Column(
                    modifier = Modifier.padding(all = 16.dp)
                ) {
                    Row {
                        CardFormResult(
                            modifier = Modifier.weight(1f),
                            title = "Courts",
                            value = "${session.totalCourts}"
                        )

                        Spacer(modifier = Modifier.width(16.dp))

                        CardFormResult(
                            modifier = Modifier.weight(1f),
                            title = "Players",
                            value = "${session.totalPlayers}"
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Row {
                        CardFormResult(
                            modifier = Modifier.weight(1f),
                            title = "Total Time",
                            value = "${session.totalTime}"
                        )

                        Spacer(modifier = Modifier.width(16.dp))

                        CardFormResult(
                            modifier = Modifier.weight(1f),
                            title = "Match Duration",
                            value = "${session.matchDuration}"
                        )
                    }
                }
            }

            LazyColumn(
                contentPadding = PaddingValues(top = 24.dp),
            ) {
                item {
                    Text(
                        modifier = Modifier.padding(start = 16.dp),
                        text = "Team Distribution",
                        style = TypographyStyle.titleSmall.copy(fontSize = 16.sp)
                    )
                }

                item {
                    Spacer(modifier = Modifier.height(10.dp))
                }

                itemsIndexed(viewModel.teams.value) { index, team ->
                    CardDistribution(
                        modifier = Modifier
                            .padding(horizontal = 16.dp),
                        team = team,
                        numberTeam = (index.plus(1)).toString()
                    )

                    Spacer(modifier = Modifier.height(10.dp))
                }

                item {
                    Spacer(modifier = Modifier.height(18.dp))
                }

                item {
                    Text(
                        modifier = Modifier.padding(start = 16.dp),
                        text = "Schedule",
                        style = TypographyStyle.titleSmall.copy(fontSize = 16.sp)
                    )
                }

                item {
                    Spacer(modifier = Modifier.height(10.dp))
                }

                itemsIndexed(viewModel.matches.value) { index, match ->
                    CardMatchSchedule(
                        modifier = Modifier.padding(horizontal = 16.dp),
                        match = match,
                        matchNumber = (index.plus(1)).toString()
                    )

                    Spacer(modifier = Modifier.height(10.dp))
                }
            }
        }
    }
}