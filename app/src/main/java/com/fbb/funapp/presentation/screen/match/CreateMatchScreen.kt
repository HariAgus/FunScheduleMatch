package com.fbb.funapp.presentation.screen.match

import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.fbb.funapp.R
import com.fbb.funapp.presentation.components.MyTextFieldTitle
import com.fbb.funapp.presentation.ui.theme.BackgroundColorBlue
import com.fbb.funapp.presentation.ui.theme.BackgroundColorWhite
import com.fbb.funapp.presentation.ui.theme.TextColorPrimary
import com.fbb.funapp.presentation.ui.theme.TypographyStyle
import java.util.UUID


@RequiresApi(35)
@Composable
fun CreateMatchScreen(
    modifier: Modifier = Modifier,
    viewModel: MatchViewModel = hiltViewModel()
) {
    var nameOfMabar by remember { mutableStateOf("Senin") }
    var court by remember { mutableStateOf("2") }
    var players by remember { mutableStateOf("16") }
    var totalTime by remember { mutableStateOf("240") }
    var durationPerMatch by remember { mutableStateOf("20") }

    Scaffold(
        modifier = modifier.fillMaxSize(),
        containerColor = BackgroundColorWhite
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues = paddingValues)
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

            MyTextFieldTitle(
                labelText = "",
                title = "Name of MABAR",
                icon = R.drawable.ic_title,
                keyboardType = KeyboardType.Text,
                text = nameOfMabar,
                onValueChange = {
                    nameOfMabar = it
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

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                // enabled = false,
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = BackgroundColorBlue,
                    disabledContainerColor = Color.LightGray
                ),
                onClick = {
                    val sessionId = UUID.randomUUID().toString()
                    viewModel.createSchedule(
                        sessionId = sessionId,
                        nameOfMabar = nameOfMabar,
                        playerCount = players.toInt(),
                        courts = court.toInt(),
                        totalTime = totalTime.toInt(),
                        durationPerMatch = durationPerMatch.toInt()
                    )
                }
            ) {
                Text(text = "Create Match")
            }
        }
    }
}