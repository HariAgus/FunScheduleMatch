package com.fbb.funapp.presentation.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.fbb.funapp.navigation.graph.MainNavGraph
import com.fbb.funapp.presentation.components.BottomBar

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(modifier: Modifier = Modifier, navController: NavHostController = rememberNavController()) {
    val shouldShowBottomBar = remember { mutableStateOf(true) }

    Scaffold(
        modifier = modifier,
        bottomBar = {
            if (shouldShowBottomBar.value) {
                Surface(
                    color = Color.White,
                    shadowElevation = 24.dp,
                    tonalElevation = 24.dp,
                    shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
                ) {
                    BottomBar(navController = navController)
                }
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier.padding(bottom = innerPadding.calculateBottomPadding())
        ) {
            MainNavGraph(navController = navController, shouldShowBottomBar = shouldShowBottomBar)
        }
    }
}