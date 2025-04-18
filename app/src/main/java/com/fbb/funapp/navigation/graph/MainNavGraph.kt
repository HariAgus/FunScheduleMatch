package com.fbb.funapp.navigation.graph

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.fbb.funapp.navigation.screen.BottomNavItemScreen
import com.fbb.funapp.navigation.screen.Screen
import com.fbb.funapp.presentation.screen.detailmatch.DetailMatchScreen
import com.fbb.funapp.presentation.screen.history.HistoryScreen
import com.fbb.funapp.presentation.screen.match.CreateMatchScreen
import com.fbb.funapp.utils.Constant

@Composable
fun MainNavGraph(navController: NavHostController) {
    val mContext = LocalContext.current

    NavHost(
        navController = navController,
        route = Graph.MAIN,
        startDestination = BottomNavItemScreen.Home.route
    ) {
        composable(BottomNavItemScreen.Home.route) {
            CreateMatchScreen()

            // DetailMatchScreen()
        }

        composable(BottomNavItemScreen.History.route) {
            HistoryScreen(
                onClickToDetail = {
                    navController.navigate(Screen.Detail.createRoute(sessionId = it))
                }
            )
        }

        // Todo : Create fitur Profile
        /*composable(BottomNavItemScreen.Profile.route) {
            Toast.makeText(mContext, "This Feature is still under development", Toast.LENGTH_LONG).show()
        }*/

        detailsNavGraph()
    }
}

fun NavGraphBuilder.detailsNavGraph() {
    navigation(
        route = Graph.DETAIL,
        startDestination = Screen.Detail.route
    ) {
        composable(
            route = Screen.Detail.route,
            arguments = listOf(navArgument(Constant.SESSION_ID) {
                type = NavType.StringType
            })
        ) {
            DetailMatchScreen()
        }
    }
}