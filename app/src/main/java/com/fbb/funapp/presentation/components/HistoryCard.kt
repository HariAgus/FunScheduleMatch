package com.fbb.funapp.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fbb.funapp.R
import com.fbb.funapp.domain.model.Session
import com.fbb.funapp.presentation.ui.theme.BackgroundColorBlue
import com.fbb.funapp.presentation.ui.theme.ColorGreen
import com.fbb.funapp.presentation.ui.theme.ColorPurple
import com.fbb.funapp.presentation.ui.theme.TextColorPrimary
import com.fbb.funapp.presentation.ui.theme.TextColorSubTitleGray
import com.fbb.funapp.presentation.ui.theme.TypographyStyle

@Composable
fun HistoryCard(modifier: Modifier = Modifier, session: Session, onClickToDetail: (String) -> Unit) {
    Surface(
        modifier = modifier.clickable { onClickToDetail(session.id) },
        color = Color.White,
        shape = RoundedCornerShape(12.dp),
        shadowElevation = 2.dp,
        tonalElevation = 2.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(vertical = 18.dp, horizontal = 16.dp)
        ) {
            Row {
                Text(
                    text = session.nameOfMabar,
                    style = TypographyStyle.titleMedium.copy(fontSize = 18.sp, color = TextColorPrimary)
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    modifier = Modifier.size(18.dp),
                    imageVector = Icons.Default.DateRange,
                    tint = Color.Black.copy(alpha = 0.7f),
                    contentDescription = "Time"
                )

                Text(
                    modifier = Modifier.padding(start = 6.dp),
                    text = session.createdAtFormatted,
                    style = TypographyStyle.bodyMedium.copy(
                        fontSize = 14.sp,
                        color = Color.Black.copy(alpha = 0.7f)
                    )
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp)
            ) {
                InformationCard(
                    modifier = Modifier.weight(1f),
                    title = "Players",
                    value = session.totalPlayers.toString(),
                    icon = R.drawable.ic_group,
                    iconColor = BackgroundColorBlue,
                    bgColor = BackgroundColorBlue.copy(alpha = 0.1f),
                )

                Spacer(modifier = Modifier.width(16.dp))

                InformationCard(
                    modifier = Modifier.weight(1f),
                    title = "Courts",
                    value = session.totalCourts.toString(),
                    icon = R.drawable.ic_cards,
                    iconColor = ColorGreen,
                    bgColor = ColorGreen.copy(alpha = 0.1f),
                )

                Spacer(modifier = Modifier.width(16.dp))

                InformationCard(
                    modifier = Modifier.weight(1f),
                    title = "Total Time",
                    value = session.totalTime.toString(),
                    icon = R.drawable.ic_timer,
                    iconColor = ColorPurple,
                    bgColor = ColorPurple.copy(alpha = 0.1f),
                )
            }
        }
    }
}

@Composable
private fun InformationCard(
    modifier: Modifier = Modifier,
    title: String,
    value: String,
    icon: Int,
    iconColor: Color,
    bgColor: Color
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Surface(
            modifier = Modifier.fillMaxWidth(),
            color = bgColor,
            shape = RoundedCornerShape(8.dp)
        ) {
            Icon(
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                painter = painterResource(id = icon),
                tint = iconColor,
                contentDescription = title
            )
        }

        Text(
            modifier = Modifier.padding(top = 8.dp),
            text = title,
            style = TypographyStyle.bodyMedium.copy(color = TextColorSubTitleGray)
        )

        Text(
            modifier = Modifier.padding(top = 2.dp),
            text = value,
            style = TypographyStyle.bodyLarge.copy(fontSize = 14.sp, color = Color.Black.copy(alpha = 0.8f))
        )
    }

}

@Preview
@Composable
private fun HistoryCardPreview() {
    HistoryCard(session = Session(nameOfMabar = "Rabu Sehat"), onClickToDetail = {})
}