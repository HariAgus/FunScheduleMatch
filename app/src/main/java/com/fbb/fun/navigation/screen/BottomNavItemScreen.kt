package com.fbb.`fun`.navigation.screen

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItemScreen(val route: String, val icon: ImageVector, val title: String) {

    data object Home : BottomNavItemScreen("home_screen", Icons.Default.Home, "Home")

}