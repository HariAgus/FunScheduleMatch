package com.fbb.funschedulematch.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.fbb.funschedulematch.presentation.ui.theme.StrokeColor
import com.fbb.funschedulematch.presentation.ui.theme.TextColorPrimary

@Composable
fun MyTextFieldTitle(
    modifier: Modifier = Modifier,
    title: String,
    labelText: String = "",
    icon: Int,
    keyboardType: KeyboardType = KeyboardType.Number
) {
    Column {
        Text(
            text = title,
            style = MaterialTheme.typography.labelLarge.copy(color = TextColorPrimary)
        )

        OutlinedTextField(
            value = TextFieldValue(""),
            onValueChange = {},
            label = { Text(text = labelText) },
            trailingIcon = {
                Image(
                    modifier = Modifier.size(24.dp),
                    painter = painterResource(id = icon),
                    contentDescription = "Icon"
                )
            },
            modifier = modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = keyboardType),
            singleLine = true,
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = StrokeColor
            ),
            shape = RoundedCornerShape(8.dp)

        )
    }

}