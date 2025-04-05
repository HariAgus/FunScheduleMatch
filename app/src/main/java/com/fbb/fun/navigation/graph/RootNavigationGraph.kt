package com.fbb.`fun`.navigation.graph

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.fbb.`fun`.navigation.screen.Screen
import com.fbb.`fun`.presentation.screen.match.CreateMatchScreen
import com.fbb.`fun`.presentation.screen.splash.SplashScreen

@Composable
fun RootNavigationGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        route = Graph.ROOT,
        startDestination = Screen.Splash.route
    ) {
        composable(Screen.Splash.route) {
            SplashScreen(navController = navController)
        }

        composable(route = Graph.MAIN) {
            CreateMatchScreen()
        }
    }
}