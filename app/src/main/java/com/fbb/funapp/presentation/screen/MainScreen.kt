package com.fbb.funapp.presentation.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
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
    Scaffold(modifier = modifier, bottomBar = {
        Surface(
            color = Color.White,
            shadowElevation = 24.dp,
            tonalElevation = 24.dp,
            shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
        ) {
            BottomBar(navController = navController)
        }
    }) {
        Column {
            MainNavGraph(navController = navController)
        }
    }
}