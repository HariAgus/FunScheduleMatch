package com.fbb.`fun`

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.fbb.`fun`.navigation.graph.RootNavigationGraph
import com.fbb.`fun`.presentation.ui.theme.FunScheduleMatchTheme

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FunScheduleMatchTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { _ ->
                    RootNavigationGraph(navController = rememberNavController())
                }
            }
        }
    }
}