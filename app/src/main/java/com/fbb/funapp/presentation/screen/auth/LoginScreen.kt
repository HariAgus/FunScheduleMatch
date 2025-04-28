package com.fbb.funapp.presentation.screen.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fbb.funapp.R
import com.fbb.funapp.presentation.components.MyTextFieldTitle
import com.fbb.funapp.presentation.ui.theme.BackgroundColorBlue
import com.fbb.funapp.presentation.ui.theme.BackgroundColorWhite

@Composable
fun LoginScreen(modifier: Modifier = Modifier) {

    var userName by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = BackgroundColorWhite)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                modifier = Modifier
                    .size(110.dp)
                    .padding(2.dp),
                painter = painterResource(id = R.drawable.icon_schedule_blue),
                contentDescription = ""
            )

            MyTextFieldTitle(
                labelText = "",
                title = "Username or Email",
                placeholderText = "Enter your username or email",
                icon = R.drawable.ic_title,
                keyboardType = KeyboardType.Text,
                text = userName,
                onValueChange = {
                    userName = it
                }
            )

            MyTextFieldTitle(
                labelText = "",
                title = "Password",
                placeholderText = "Enter your password",
                icon = R.drawable.ic_lock,
                keyboardType = KeyboardType.Password,
                text = password,
                onValueChange = {
                    password = it
                }
            )

            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp)
                    .height(48.dp),
                enabled = true,
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = BackgroundColorBlue,
                    disabledContainerColor = Color.LightGray
                ),
                onClick = {

                }
            ) {
                Text(text = "Login")

            }
        }
    }
}

@Preview
@Composable
private fun LoginScreenPreview() {
    LoginScreen()
}