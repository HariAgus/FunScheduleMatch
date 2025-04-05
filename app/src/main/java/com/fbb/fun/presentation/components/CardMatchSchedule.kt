package com.fbb.`fun`.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fbb.`fun`.presentation.ui.theme.BackgroundColorBlue
import com.fbb.`fun`.presentation.ui.theme.StrokeColor
import com.fbb.`fun`.presentation.ui.theme.TextColorSubTitleGray
import com.fbb.`fun`.presentation.ui.theme.TypographyStyle

@Composable
fun CardMatchSchedule(modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        color = Color.White,
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "Time Slot 1",
                style = TypographyStyle.bodyLarge.copy(color = BackgroundColorBlue)
            )

            FieldMatchSchedule()
        }
    }
}

@Composable
private fun FieldMatchSchedule(modifier: Modifier = Modifier) {
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
                text = "Field 1 - Match 1",
                style = TypographyStyle.bodySmall.copy(color = TextColorSubTitleGray)
            )

            Row {
                Text(
                    text = "Player 1 x 9",
                    style = TypographyStyle.bodyLarge.copy(fontSize = 14.sp, color = Color.Black.copy(alpha = 0.8f))
                )

                Text(
                    modifier = Modifier.padding(horizontal = 4.dp),
                    text = "VS",
                    style = TypographyStyle.titleMedium.copy(fontSize = 14.sp)
                )

                Text(
                    text = "Player 1 x 9",
                    style = TypographyStyle.bodyLarge.copy(fontSize = 14.sp, color = Color.Black.copy(alpha = 0.8f))
                )
            }
        }
    }
}

@Preview
@Composable
private fun CardMatchSchedulePreview() {
    CardMatchSchedule()
}