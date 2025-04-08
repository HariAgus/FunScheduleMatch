package com.fbb.funapp.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fbb.funapp.domain.model.Match
import com.fbb.funapp.presentation.ui.theme.BackgroundColorBlue
import com.fbb.funapp.presentation.ui.theme.StrokeColor
import com.fbb.funapp.presentation.ui.theme.TextColorSubTitleGray
import com.fbb.funapp.presentation.ui.theme.TypographyStyle

@Composable
fun CardMatchSchedule(modifier: Modifier = Modifier, match: Match, matchNumber: String) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        color = Color.White,
        shape = RoundedCornerShape(8.dp),
        shadowElevation = 2.dp,
        tonalElevation = 2.dp
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "Time Slot $matchNumber",
                style = TypographyStyle.bodyLarge.copy(color = BackgroundColorBlue)
            )

            Spacer(modifier = Modifier.height(8.dp))

            FieldMatchSchedule(match = match)
        }
    }
}

@Composable
private fun FieldMatchSchedule(modifier: Modifier = Modifier, match: Match) {
    Surface(
        modifier = modifier
            .fillMaxWidth(),
        color = StrokeColor,
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(
            modifier = Modifier.padding(12.dp)
        ) {
            Text(
                text = "Court ${match.courtNumber} - Match ${match.round}",
                style = TypographyStyle.bodySmall.copy(color = TextColorSubTitleGray)
            )

            Row(
                modifier = Modifier.padding(top = 4.dp)
            ) {
                Text(
                    text = "${match.team1.players[0].name} x ${match.team1.players[1].name}",
                    style = TypographyStyle.bodyLarge.copy(fontSize = 14.sp, color = Color.Black.copy(alpha = 0.8f))
                )

                Text(
                    modifier = Modifier.padding(horizontal = 4.dp),
                    text = "VS",
                    style = TypographyStyle.titleLarge.copy(fontSize = 14.sp)
                )

                Text(
                    text = "${match.team2.players[0].name} x ${match.team2.players[1].name}",
                    style = TypographyStyle.bodyLarge.copy(fontSize = 14.sp, color = Color.Black.copy(alpha = 0.8f))
                )
            }
        }
    }
}