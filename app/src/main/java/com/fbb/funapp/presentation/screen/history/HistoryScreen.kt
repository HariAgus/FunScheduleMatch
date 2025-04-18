package com.fbb.funapp.presentation.screen.history

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.fbb.funapp.presentation.components.HistoryCard
import com.fbb.funapp.presentation.screen.match.MatchViewModel
import com.fbb.funapp.presentation.ui.theme.BackgroundColorWhite
import com.fbb.funapp.presentation.ui.theme.TextColorPrimary
import com.fbb.funapp.presentation.ui.theme.TypographyStyle

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HistoryScreen(modifier: Modifier = Modifier, viewModel: MatchViewModel = hiltViewModel(), onClickToDetail: (String) -> Unit) {

    LaunchedEffect(key1 = true) {
        viewModel.getHistoryMatches()
    }

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

            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(10.dp),
                contentPadding = PaddingValues(vertical = 24.dp)
            ) {
                items(viewModel.sessions.value) { session ->
                    HistoryCard(
                        session = session,
                        onClickToDetail = onClickToDetail
                    )
                }
            }
        }
    }
}