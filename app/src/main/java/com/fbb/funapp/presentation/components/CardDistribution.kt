package com.fbb.funapp.presentation.components

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
import com.fbb.funapp.domain.model.Team
import com.fbb.funapp.presentation.ui.theme.TextColorSubTitleGray
import com.fbb.funapp.presentation.ui.theme.TypographyStyle

@Composable
fun CardDistribution(modifier: Modifier = Modifier, team: Team, numberTeam: String) {
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
            modifier = Modifier.padding(all = 12.dp)
        ) {
            Text(
                text = "Team $numberTeam",
                style = TypographyStyle.bodyMedium.copy(color = TextColorSubTitleGray)
            )

            Text(
                modifier = Modifier.padding(top = 4.dp),
                text = "${team.players[0].name} - ${team.players[1].name}",
                style = TypographyStyle.bodyLarge.copy(fontSize = 14.sp, color = Color.Black.copy(alpha = 0.8f))
            )
        }
    }
}