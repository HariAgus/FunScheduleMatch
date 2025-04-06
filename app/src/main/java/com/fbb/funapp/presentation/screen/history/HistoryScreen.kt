package com.fbb.funapp.presentation.screen.history

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fbb.funapp.presentation.ui.theme.BackgroundColorWhite
import com.fbb.funapp.presentation.ui.theme.TextColorPrimary
import com.fbb.funapp.presentation.ui.theme.TypographyStyle

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HistoryScreen(modifier: Modifier = Modifier) {
    Scaffold(
        modifier = modifier,
        containerColor = BackgroundColorWhite
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues = paddingValues)
                .padding(horizontal = 14.dp)
        ) {
            Text(
                modifier = Modifier.padding(top = 24.dp),
                text = "History Match",
                style = TypographyStyle.titleLarge.copy(fontSize = 22.sp, color = TextColorPrimary)
            )
        }
    }
}