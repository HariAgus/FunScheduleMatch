package com.fbb.`fun`.presentation.screen.match

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fbb.`fun`.presentation.components.CardDistribution
import com.fbb.`fun`.presentation.components.CardFormResult
import com.fbb.`fun`.presentation.components.CardMatchSchedule
import com.fbb.`fun`.presentation.ui.theme.BgColorWhiteSecond
import com.fbb.`fun`.presentation.ui.theme.TextColorPrimary
import com.fbb.`fun`.presentation.ui.theme.TypographyStyle

@Composable
fun MatchScheduleScreen(modifier: Modifier = Modifier) {
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
                modifier = Modifier.fillMaxWidth(),
                color = Color.White
            ) {
                Column(
                    modifier = Modifier.padding(all = 16.dp)
                ) {
                    Row {
                        CardFormResult(
                            modifier = Modifier.weight(1f),
                            title = "Fields", value = "2"
                        )

                        Spacer(modifier = Modifier.width(16.dp))

                        CardFormResult(
                            modifier = Modifier.weight(1f),
                            title = "Players", value = "16"
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Row {
                        CardFormResult(
                            modifier = Modifier.weight(1f),
                            title = "Total Time", value = "240 min"
                        )

                        Spacer(modifier = Modifier.width(16.dp))

                        CardFormResult(
                            modifier = Modifier.weight(1f),
                            title = "Match Duration", value = "15 min"
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                modifier = Modifier.padding(start = 16.dp),
                text = "Team Distribution",
                style = TypographyStyle.titleSmall.copy(fontSize = 16.sp)
            )

            Spacer(modifier = Modifier.height(10.dp))

            CardDistribution(modifier = Modifier.padding(horizontal = 16.dp))

            Spacer(modifier = Modifier.height(18.dp))

            Text(
                modifier = Modifier.padding(start = 16.dp),
                text = "Schedule",
                style = TypographyStyle.titleSmall.copy(fontSize = 16.sp)
            )

            Spacer(modifier = Modifier.height(10.dp))

            CardMatchSchedule(
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }
    }
}