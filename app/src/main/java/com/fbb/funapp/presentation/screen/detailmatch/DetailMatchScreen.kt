package com.fbb.funapp.presentation.screen.detailmatch

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.fbb.funapp.presentation.components.CardFormResult
import com.fbb.funapp.presentation.components.CardMatchSchedule
import com.fbb.funapp.presentation.screen.match.DetailScheduleState
import com.fbb.funapp.presentation.screen.match.MatchRoundState
import com.fbb.funapp.presentation.screen.match.MatchViewModel
import com.fbb.funapp.presentation.ui.theme.BackgroundColorBlue
import com.fbb.funapp.presentation.ui.theme.BackgroundColorWhite
import com.fbb.funapp.presentation.ui.theme.BgColorWhiteSecond
import com.fbb.funapp.presentation.ui.theme.TextColorPrimary
import com.fbb.funapp.presentation.ui.theme.TypographyStyle

@Composable
fun DetailMatchScreen(
    modifier: Modifier = Modifier,
    viewModel: MatchViewModel = hiltViewModel()
) {

    val mContext = LocalContext.current

    LaunchedEffect(key1 = true) {
        val sessionId = viewModel.sessionId.value ?: "-"
        viewModel.getSessionById(sessionId = sessionId)
        viewModel.getMatchRounds(sessionId = sessionId)
    }

    val state by viewModel.detailScheduleState.collectAsState()
    val roundState by viewModel.matchRoundState.collectAsState()
    val session = viewModel.session.value

    when (state) {
        is DetailScheduleState.Loading -> {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(BackgroundColorWhite)
            ) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center), color = BackgroundColorBlue)
            }
        }

        is DetailScheduleState.Error -> {
            val message = (state as DetailScheduleState.Error).message
            Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show()
        }

        is DetailScheduleState.Success -> {
            Scaffold(
                modifier = modifier,
                containerColor = BgColorWhiteSecond
            ) { paddingValues ->
                Column(
                    modifier = Modifier.padding(paddingValues = paddingValues)
                ) {
                    Text(
                        modifier = Modifier.padding(start = 16.dp, top = 16.dp),
                        text = session.nameOfMabar,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        style = TypographyStyle.titleLarge.copy(fontSize = 18.sp, color = TextColorPrimary)
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
                        contentPadding = PaddingValues(top = 8.dp),
                    ) {
                        // Disable Team Distribution
                        /*item {
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
                        */

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

                        when (roundState) {
                            is MatchRoundState.Loading -> {
                                item {
                                    Box(
                                        modifier = Modifier.fillMaxSize()
                                    ) {
                                        CircularProgressIndicator(
                                            modifier = Modifier
                                                .align(Alignment.Center)
                                                .padding(top = 32.dp),
                                            color = BackgroundColorBlue
                                        )
                                    }
                                }
                            }

                            is MatchRoundState.Error -> {
                                val message = (roundState as MatchRoundState.Error).message
                                Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show()
                            }

                            is MatchRoundState.Success -> {
                                itemsIndexed(viewModel.matchRounds.value) { index, matchRound ->
                                    CardMatchSchedule(
                                        modifier = Modifier.padding(horizontal = 16.dp),
                                        matchRound = matchRound,
                                        matchNumber = (index.plus(1)).toString()
                                    )

                                    Spacer(modifier = Modifier.height(10.dp))
                                }
                            }

                            else -> {}

                        }
                    }
                }
            }
        }

        else -> {}
    }
}