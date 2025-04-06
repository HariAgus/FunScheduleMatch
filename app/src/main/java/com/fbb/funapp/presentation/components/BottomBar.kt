package com.fbb.funapp.presentation.components

import android.widget.Toast
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.fbb.funapp.navigation.screen.BottomNavItemScreen
import com.fbb.funapp.presentation.ui.theme.BackgroundColorBlue

@Composable
fun BottomBar(modifier: Modifier = Modifier, navController: NavController) {

    val mContext = LocalContext.current

    val navigationItems = listOf(
        BottomNavItemScreen.Home,
        BottomNavItemScreen.History,
        BottomNavItemScreen.Profile
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val bottomBarDestination = navigationItems.any { it.route == currentRoute }

    if (bottomBarDestination) {
        NavigationBar(
            modifier = modifier,
            containerColor = Color.White,
            tonalElevation = 24.dp
        ) {
            navigationItems.forEach { item ->
                NavigationBarItem(
                    selected = currentRoute == item.route,
                    onClick = {
                        if (item.route == "profile_screen") {
                            Toast.makeText(mContext, "This Feature is still under development", Toast.LENGTH_LONG)
                                .show()
                        } else {
                            if (currentRoute != item.route) {
                                navController.navigate(item.route) {
                                    popUpTo(navController.graph.startDestinationId) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                        }
                    },
                    icon = {
                        Icon(
                            imageVector = item.icon,
                            contentDescription = item.title
                        )
                    },
                    label = {
                        Text(text = item.title)
                    },
                    alwaysShowLabel = true,
                    colors = NavigationBarItemColors(
                        selectedIconColor = BackgroundColorBlue,
                        selectedTextColor = BackgroundColorBlue,
                        selectedIndicatorColor = BackgroundColorBlue.copy(alpha = 0.2f),
                        unselectedIconColor = Color.Black.copy(alpha = 0.7f),
                        unselectedTextColor = Color.Black.copy(alpha = 0.7f),
                        disabledTextColor = Color.Black.copy(alpha = 0.7f),
                        disabledIconColor = Color.Black.copy(alpha = 0.7f)
                    )
                )
            }
        }
    }

}