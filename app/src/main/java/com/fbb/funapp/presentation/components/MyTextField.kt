package com.fbb.funapp.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
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
import com.fbb.funapp.presentation.ui.theme.BackgroundColorBlue
import com.fbb.funapp.presentation.ui.theme.BorderTextFieldColor
import com.fbb.funapp.presentation.ui.theme.StrokeColor
import com.fbb.funapp.presentation.ui.theme.TextColorPrimary

@Composable
fun MyTextFieldTitle(
    modifier: Modifier = Modifier,
    title: String,
    labelText: String = "",
    icon: Int,
    text: String,
    onValueChange: (String) -> Unit,
    keyboardType: KeyboardType = KeyboardType.Number,
    maxLines: Int = 1
) {
    Column {
        Text(
            text = title,
            style = MaterialTheme.typography.labelLarge.copy(color = TextColorPrimary)
        )

        OutlinedTextField(
            value = text,
            onValueChange = {
                onValueChange(it)
            },
            label = { Text(text = labelText) },
            trailingIcon = {
                Icon(
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
                unfocusedBorderColor = BorderTextFieldColor,
                focusedBorderColor = BackgroundColorBlue,
                cursorColor = BackgroundColorBlue,
                focusedTrailingIconColor = BackgroundColorBlue,
                unfocusedTrailingIconColor = BorderTextFieldColor
            ),
            shape = RoundedCornerShape(8.dp),
            maxLines = maxLines
        )
    }

}