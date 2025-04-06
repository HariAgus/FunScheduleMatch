package com.fbb.funapp.navigation.graph

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.fbb.funapp.navigation.screen.BottomNavItemScreen
import com.fbb.funapp.presentation.screen.history.HistoryScreen
import com.fbb.funapp.presentation.screen.match.CreateMatchScreen

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
        }

        composable(BottomNavItemScreen.History.route) {
            HistoryScreen()
        }

//        composable(BottomNavItemScreen.Profile.route) {
//            Toast.makeText(mContext, "This Feature is still under development", Toast.LENGTH_LONG).show()
//        }
    }
}