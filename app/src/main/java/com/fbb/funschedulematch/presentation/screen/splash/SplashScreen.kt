package com.fbb.funschedulematch.presentation.screen.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fbb.funschedulematch.R
import com.fbb.funschedulematch.presentation.ui.theme.BackgroundColorBlue
import com.fbb.funschedulematch.presentation.ui.theme.TypographyStyle

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun SplashScreen(modifier: Modifier = Modifier) {
    val brush = Brush.verticalGradient(listOf(Color(0xFF3B82F6), BackgroundColorBlue))

    Box(
        modifier = modifier
            .background(brush = brush)
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .offset(x = 60.dp, y = -(60).dp)
                .size(180.dp)
                .graphicsLayer(alpha = 0.1f)
                .background(Color.White, CircleShape)
                .align(Alignment.TopEnd)
                .padding(16.dp)
        )

        Box(
            modifier = Modifier
                .offset(x = -(60).dp, y = 60.dp)
                .size(180.dp)
                .graphicsLayer(alpha = 0.1f)
                .background(Color.White, CircleShape)
                .align(Alignment.BottomStart)
                .padding(16.dp)
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Surface(
                modifier = Modifier.wrapContentSize(),
                shape = RoundedCornerShape(20.dp)
            ) {
                Image(
                    modifier = Modifier
                        .size(100.dp)
                        .padding(2.dp),
                    painter = painterResource(id = R.drawable.icon_schedule_blue),
                    contentDescription = ""
                )
            }

            Text(
                modifier = Modifier.padding(top = 16.dp),
                text = "Fun Badminton Schedule",
                color = Color.White,
                style = TypographyStyle.bodyLarge
            )

            Text(
                modifier = Modifier.padding(top = 2.dp),
                text = "Schedule your Badminton games easily",
                color = Color.White,
                style = TypographyStyle.labelMedium.copy(fontWeight = FontWeight.Light)
            )
        }

        Text(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 60.dp),
            text = "Powered by Fun Badminton Bekasi",
            color = Color.DarkGray,
            style = TypographyStyle.labelMedium
        )
    }
}


@Preview
@Composable
private fun SplashScreenPreview() {
    SplashScreen()
}