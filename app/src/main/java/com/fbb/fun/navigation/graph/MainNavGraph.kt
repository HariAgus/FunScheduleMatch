package com.fbb.`fun`.navigation.graph

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.fbb.`fun`.navigation.screen.BottomNavItemScreen
import com.fbb.`fun`.presentation.screen.match.MatchScheduleScreen

@Composable
fun MainNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        route = Graph.MAIN,
        startDestination = BottomNavItemScreen.Home.route
    ) {
        composable(BottomNavItemScreen.Home.route) {
            MatchScheduleScreen()
        }
    }
}