package com.fbb.funapp.presentation.screen.history

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.fbb.funapp.R
import com.fbb.funapp.presentation.components.HistoryCard
import com.fbb.funapp.presentation.screen.match.HistoryScheduleState
import com.fbb.funapp.presentation.screen.match.MatchViewModel
import com.fbb.funapp.presentation.ui.theme.BackgroundColorBlue
import com.fbb.funapp.presentation.ui.theme.BackgroundColorWhite
import com.fbb.funapp.presentation.ui.theme.TextColorPrimary
import com.fbb.funapp.presentation.ui.theme.TypographyStyle
import com.fbb.funapp.utils.AnimatedPreloader
import com.fbb.funapp.utils.randomEmptyStateTitle
import com.fbb.funapp.utils.randomLoadingTitle

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HistoryScreen(
    modifier: Modifier = Modifier,
    viewModel: MatchViewModel = hiltViewModel(),
    onClickToDetail: (String) -> Unit
) {
    val mContext = LocalContext.current
    val state by viewModel.historyScheduleState.collectAsState()

    LaunchedEffect(key1 = true) {
        viewModel.getHistoryMatches()
    }

    when (state) {
        is HistoryScheduleState.Loading -> {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = BackgroundColorWhite),

                ) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center), color = BackgroundColorBlue)
            }
        }

        is HistoryScheduleState.Error -> {
            val message = (state as HistoryScheduleState.Error).message
            Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show()
        }

        is HistoryScheduleState.Success -> {
            val dataSession = viewModel.sessions.value
            val textEmpty = remember { randomEmptyStateTitle() }

            Scaffold(
                modifier = modifier
                    .fillMaxSize()
                    .padding(top = 32.dp),
                containerColor = BackgroundColorWhite
            ) { paddingValues ->
                Column(
                    modifier = Modifier.padding(horizontal = 14.dp)
                ) {
                    Text(
                        modifier = Modifier.padding(top = 24.dp),
                        text = "History Match",
                        style = TypographyStyle.titleLarge.copy(fontSize = 22.sp, color = TextColorPrimary)
                    )

                    if (dataSession.isNotEmpty()) {
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
                    } else {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(color = BackgroundColorWhite),
                            contentAlignment = Alignment.Center
                        ) {
                            Column(
                                modifier = Modifier.align(Alignment.Center)
                            ) {
                                AnimatedPreloader(
                                    modifier = Modifier
                                        .align(Alignment.CenterHorizontally)
                                        .size(200.dp),
                                    lottieRes = R.raw.lottie_cat_dance
                                )

                                Text(
                                    modifier = Modifier
                                        .align(Alignment.CenterHorizontally)
                                        .padding(horizontal = 32.dp)
                                        .padding(top = 6.dp),
                                    text = textEmpty,
                                    style = TypographyStyle.bodySmall.copy(
                                        fontSize = 16.sp,
                                        color = TextColorPrimary
                                    ),
                                    textAlign = TextAlign.Center
                                )
                            }
                        }
                    }
                }
            }
        }

        else -> {}
    }
}