package com.fbb.funschedulematch

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.fbb.funschedulematch.presentation.screen.match.CreateMatchScreen
import com.fbb.funschedulematch.presentation.screen.match.MatchScheduleScreen
import com.fbb.funschedulematch.presentation.screen.splash.SplashScreen
import com.fbb.funschedulematch.presentation.ui.theme.FunScheduleMatchTheme

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FunScheduleMatchTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { _ ->
                    // SplashScreen()

                    // CreateMatchScreen()

                    MatchScheduleScreen()
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    FunScheduleMatchTheme {
        Greeting("Android")
    }
}