package com.fbb.funschedulematch.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
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
import com.fbb.funschedulematch.presentation.ui.theme.TextColorSubTitleGray
import com.fbb.funschedulematch.presentation.ui.theme.TypographyStyle

@Composable
fun CardDistribution(modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        color = Color.White,
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(
            modifier = Modifier.padding(all = 12.dp)
        ) {
            Text(
                text = "Team 1",
                style = TypographyStyle.bodyMedium.copy(color = TextColorSubTitleGray)
            )

            Text(
                modifier = Modifier.padding(top = 2.dp),
                text = "Player 14 - Player 9",
                style = TypographyStyle.bodyLarge.copy(fontSize = 14.sp, color = Color.Black.copy(alpha = 0.8f))
            )
        }
    }
}