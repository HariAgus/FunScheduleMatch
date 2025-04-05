package com.fbb.`fun`.navigation.screen

sealed class Screen(val route: String) {

    data object Splash : Screen("splash_screen")

}