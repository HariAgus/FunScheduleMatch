package com.fbb.funschedulematch.presentation.screen.match

import android.annotation.SuppressLint
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fbb.funschedulematch.R
import com.fbb.funschedulematch.presentation.components.MyTextFieldTitle
import com.fbb.funschedulematch.presentation.ui.theme.BackgroundColorBlue
import com.fbb.funschedulematch.presentation.ui.theme.BackgroundColorWhite
import com.fbb.funschedulematch.presentation.ui.theme.TextColorPrimary
import com.fbb.funschedulematch.presentation.ui.theme.TypographyStyle


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CreateMatchScreen(modifier: Modifier = Modifier) {
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
                keyboardType = KeyboardType.Text
            )

            MyTextFieldTitle(
                title = "Number of Courts",
                icon = R.drawable.ic_badminton_court
            )

            MyTextFieldTitle(
                title = "Players",
                icon = R.drawable.ic_team
            )

            MyTextFieldTitle(
                title = "Total Time (Minutes)",
                icon = R.drawable.ic_time
            )

            MyTextFieldTitle(
                title = "Duration per Match (Minutes)",
                icon = R.drawable.ic_timer
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                enabled = false,
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = BackgroundColorBlue,
                    disabledContainerColor = Color.LightGray
                ),
                onClick = { /*TODO*/ }
            ) {
                Text(text = "Create Match")
            }
        }
    }
}

@Preview
@Composable
private fun CreateMatchScreenPreview() {
    CreateMatchScreen()
}