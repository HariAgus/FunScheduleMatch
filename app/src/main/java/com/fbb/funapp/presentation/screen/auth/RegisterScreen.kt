package com.fbb.funapp.presentation.screen.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fbb.funapp.R
import com.fbb.funapp.presentation.components.MyTextFieldTitle
import com.fbb.funapp.presentation.ui.theme.BackgroundColorBlue
import com.fbb.funapp.presentation.ui.theme.BackgroundColorWhite
import com.fbb.funapp.presentation.ui.theme.TextColorPrimary
import com.fbb.funapp.presentation.ui.theme.TextColorSubTitleGray
import com.fbb.funapp.presentation.ui.theme.TypographyStyle

@Composable
fun RegisterScreen(modifier: Modifier = Modifier) {

    var userName by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    val genderOptions = listOf("Male", "Female")
    val (selectedOption, onOptionSelected) = remember { mutableStateOf(genderOptions[0]) }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = BackgroundColorWhite)
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .align(Alignment.TopStart),
        ) {
            Text(
                modifier = Modifier.padding(top = 24.dp),
                text = "Register",
                style = TypographyStyle.titleLarge.copy(fontSize = 22.sp, color = TextColorPrimary)
            )

            Text(
                text = "Please fill in your details to register",
                style = TypographyStyle.bodySmall.copy(color = TextColorPrimary)
            )

            Spacer(modifier = Modifier.height(32.dp))

            MyTextFieldTitle(
                labelText = "",
                title = "Nama",
                placeholderText = "Enter your name",
                icon = R.drawable.ic_person,
                keyboardType = KeyboardType.Text,
                text = userName,
                onValueChange = {
                    userName = it
                }
            )

            MyTextFieldTitle(
                labelText = "",
                title = "Username or Email",
                placeholderText = "Enter your username or email",
                icon = R.drawable.ic_mail,
                keyboardType = KeyboardType.Text,
                text = userName,
                onValueChange = {
                    userName = it
                }
            )

            MyTextFieldTitle(
                title = "Password",
                placeholderText = "Enter your password",
                icon = R.drawable.ic_lock,
                keyboardType = KeyboardType.Password,
                text = password,
                onValueChange = {
                    password = it
                }
            )

            MyTextFieldTitle(
                title = "Confirm Password",
                placeholderText = "Enter your confirm password",
                icon = R.drawable.ic_lock,
                keyboardType = KeyboardType.Password,
                text = confirmPassword,
                onValueChange = {
                    confirmPassword = it
                }
            )

            Text(
                text = "Gender",
                style = MaterialTheme.typography.labelLarge.copy(color = TextColorPrimary)
            )

            Row(
                modifier = Modifier.padding(top = 6.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                genderOptions.forEach { text ->
                    Row {
                        RadioButton(
                            colors = RadioButtonColors(
                                selectedColor = BackgroundColorBlue,
                                disabledSelectedColor = BackgroundColorBlue,
                                disabledUnselectedColor = TextColorSubTitleGray,
                                unselectedColor = Color.Gray
                            ),
                            selected = (text == selectedOption),
                            onClick = null
                        )
                        Text(
                            text = text,
                            style = MaterialTheme.typography.bodyLarge,
                            modifier = Modifier.padding(start = 6.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            MyTextFieldTitle(
                title = "Level",
                placeholderText = "Select Your Level",
                icon = R.drawable.ic_arrow_drop_down,
                keyboardType = KeyboardType.Password,
                text = password,
                onValueChange = {

                },
                onClickField = {

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
                Text(text = "Register")
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Already have an account? ",
                    style = TypographyStyle.labelMedium
                )

                Text(
                    modifier = Modifier
                        .clickable {
                            // TODO: Navigation to Login
                        },
                    text = "Login",
                    style = TypographyStyle.labelMedium.copy(color = BackgroundColorBlue)
                )
            }

        }
    }
}

@Preview
@Composable
private fun RegisterScreenPreview() {
    RegisterScreen()
}