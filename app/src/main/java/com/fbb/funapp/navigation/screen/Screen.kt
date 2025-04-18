package com.fbb.funapp.navigation.screen

sealed class Screen(val route: String) {

    data object Splash : Screen("splash_screen")

    data object Detail : Screen("detail_screen/{session_id}") {
        fun passToDetail(sessionId: String) = "detail_screen/$sessionId"
    }

}