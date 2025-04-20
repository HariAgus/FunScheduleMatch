package com.fbb.funapp.presentation.screen.match

import android.annotation.SuppressLint
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fbb.funapp.R
import com.fbb.funapp.presentation.components.MyTextFieldTitle
import com.fbb.funapp.presentation.ui.theme.BackgroundColorBlue
import com.fbb.funapp.presentation.ui.theme.BackgroundColorWhite
import com.fbb.funapp.presentation.ui.theme.TextColorPrimary
import com.fbb.funapp.presentation.ui.theme.TypographyStyle
import com.fbb.funapp.utils.AnimatedPreloader
import com.fbb.funapp.utils.randomLoadingText
import com.fbb.funapp.utils.randomLoadingTitle
import kotlinx.coroutines.delay
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.UUID

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@RequiresApi(35)
@Composable
fun CreateMatchScreen(
    modifier: Modifier = Modifier,
    onSuccessToDetailMatch: (String) -> Unit,
    onBottomBarVisibilityChanged: (Boolean) -> Unit,
    viewModel: MatchViewModel
) {
    val state by viewModel.matchState.collectAsState()
    val isFormValid by viewModel.isFormValid.collectAsState()
    var isButtonClicked by remember { mutableStateOf(false) }

    var sessionId by remember { mutableStateOf("") }
    var nameOfMabar by remember { mutableStateOf("") }
    var court by remember { mutableStateOf("") }
    var date by remember { mutableStateOf("") }
    var players by remember { mutableStateOf("") }
    var totalTime by remember { mutableStateOf("") }
    var durationPerMatch by remember { mutableStateOf("") }

    val datePickerState = rememberDatePickerState(initialSelectedDateMillis = System.currentTimeMillis())
    val showDate = remember { mutableStateOf(false) }

    LaunchedEffect(nameOfMabar, court, players, totalTime, durationPerMatch) {
        viewModel.validateForm(
            name = nameOfMabar,
            court = court,
            players = players,
            totalTime = totalTime,
            durationPerMatch = durationPerMatch,
            date = date
        )
    }

    LaunchedEffect(key1 = isButtonClicked) {
        if (isButtonClicked) {
            delay(1500)

            sessionId = UUID.randomUUID().toString()
            viewModel.createSchedule(
                sessionId = sessionId,
                nameOfMabar = nameOfMabar,
                playerCount = players.toInt(),
                courts = court.toInt(),
                totalTime = totalTime.toInt(),
                durationPerMatch = durationPerMatch.toInt(),
                date = date
            )

            isButtonClicked = false
        }
    }

    LaunchedEffect(state) {
        when (state) {
            is MatchScheduleState.Success -> {
                onSuccessToDetailMatch(sessionId)

                delay(300)
                viewModel.resetMatchScheduleState()
            }

            else -> {}
        }
    }

    Crossfade(targetState = state, label = "MatchScheduleStateTransition") { currentState ->
        when (currentState) {
            is MatchScheduleState.Loading -> {
                onBottomBarVisibilityChanged(false)

                val loadingTitle = remember { randomLoadingTitle() }
                val loadingDescription = remember { randomLoadingText() }

                Box(
                    modifier = modifier
                        .fillMaxSize()
                        .background(BackgroundColorWhite)
                ) {
                    Column(
                        modifier = Modifier.align(Alignment.Center)
                    ) {

                        AnimatedPreloader(
                            modifier = Modifier
                                .align(Alignment.CenterHorizontally)
                                .size(200.dp),
                            lottieRes = R.raw.lottie_cat_dance
                        )

                        Text(
                            modifier = Modifier.align(Alignment.CenterHorizontally),
                            text = loadingTitle,
                            style = TypographyStyle.titleLarge.copy(
                                fontSize = 22.sp,
                                color = TextColorPrimary
                            ),
                            textAlign = TextAlign.Center
                        )

                        Text(
                            modifier = Modifier
                                .align(Alignment.CenterHorizontally)
                                .padding(horizontal = 32.dp)
                                .padding(top = 6.dp),
                            text = loadingDescription,
                            style = TypographyStyle.bodySmall.copy(
                                fontSize = 16.sp,
                                color = TextColorPrimary
                            ),
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }

            is MatchScheduleState.Error -> {}

            is MatchScheduleState.Idle -> {
                onBottomBarVisibilityChanged(true)

                Scaffold(
                    modifier = modifier
                        .fillMaxSize()
                        .padding(top = 32.dp),
                    contentWindowInsets = WindowInsets(0, 0, 0, 0),
                    containerColor = BackgroundColorWhite
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = 14.dp)
                    ) {
                        Text(
                            modifier = Modifier.padding(top = 24.dp),
                            text = "Create Match",
                            style = TypographyStyle.titleLarge.copy(fontSize = 22.sp, color = TextColorPrimary)
                        )

                        Text(
                            text = "Please fill in the match details",
                            style = TypographyStyle.bodySmall.copy(color = TextColorPrimary)
                        )

                        Spacer(modifier = Modifier.height(24.dp))

                        Column(
                            modifier = Modifier
                                .verticalScroll(state = rememberScrollState())
                                .imePadding()
                        ) {
                            MyTextFieldTitle(
                                labelText = "",
                                title = "Name of MABAR",
                                icon = R.drawable.ic_title,
                                keyboardType = KeyboardType.Text,
                                text = nameOfMabar,
                                onValueChange = {
                                    if (it.length <= 32) {
                                        nameOfMabar = it
                                    }
                                }
                            )

                            MyTextFieldTitle(
                                title = "Number of Courts",
                                icon = R.drawable.ic_cards,
                                keyboardType = KeyboardType.Number,
                                text = court,
                                onValueChange = {
                                    if (it.all { char -> char.isDigit() }) {
                                        court = it
                                    }
                                }
                            )

                            MyTextFieldTitle(
                                title = "Play Date",
                                icon = R.drawable.ic_calendar,
                                keyboardType = KeyboardType.Text,
                                text = date,
                                onValueChange = {},
                                readOnly = true,
                                enabled = false,
                                onClickField = {
                                    Log.d("DATE_PICKER", "Clicked!")
                                    showDate.value = true
                                }
                            )

                            MyTextFieldTitle(
                                title = "Players",
                                icon = R.drawable.ic_group,
                                keyboardType = KeyboardType.Number,
                                text = players,
                                onValueChange = {
                                    players = it
                                }
                            )

                            MyTextFieldTitle(
                                title = "Total Time (Minutes)",
                                icon = R.drawable.ic_time,
                                keyboardType = KeyboardType.Number,
                                text = totalTime,
                                onValueChange = {
                                    totalTime = it
                                }
                            )

                            MyTextFieldTitle(
                                title = "Duration per Match (Minutes)",
                                icon = R.drawable.ic_timer,
                                keyboardType = KeyboardType.Number,
                                text = durationPerMatch,
                                onValueChange = {
                                    durationPerMatch = it
                                }
                            )

                            Spacer(modifier = Modifier.weight(1f))

                            Button(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 24.dp)
                                    .height(48.dp),
                                enabled = isFormValid,
                                shape = RoundedCornerShape(8.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = BackgroundColorBlue,
                                    disabledContainerColor = Color.LightGray
                                ),
                                onClick = {
                                    isButtonClicked = true
                                }
                            ) {
                                if (isButtonClicked) {
                                    CircularProgressIndicator(
                                        modifier = Modifier.size(24.dp),
                                        color = Color.White,
                                        strokeWidth = 2.dp
                                    )
                                } else {
                                    Text(text = "Create Match")
                                }

                            }

                            Spacer(modifier = Modifier.height(32.dp))
                        }
                    }

                    if (showDate.value) {
                        DatePickerDialog(
                            colors = DatePickerDefaults.colors(containerColor = BackgroundColorWhite),
                            onDismissRequest = {
                                showDate.value = false
                            }, confirmButton = {
                                Button(
                                    modifier = Modifier.padding(all = 8.dp),
                                    onClick = {
                                        val selectedMillis = datePickerState.selectedDateMillis
                                        val formatter = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault())
                                        date = selectedMillis?.let { formatter.format(Date(it)) } ?: ""
                                        showDate.value = false
                                    }
                                ) {
                                    Text("Confirm")
                                }
                            },
                            shape = RoundedCornerShape(12.dp)
                        ) {
                            DatePicker(
                                state = datePickerState,
                                modifier = Modifier.fillMaxWidth(),
                                title = {
                                    Text("Select a date", modifier = Modifier.padding(16.dp))
                                },
                                headline = {
                                    Text("Today's date", modifier = Modifier.padding(16.dp))
                                },
                                showModeToggle = true,
                                colors = DatePickerDefaults.colors(containerColor = BackgroundColorWhite)
                            )
                        }
                    }
                }
            }

            else -> {}
        }
    }
}