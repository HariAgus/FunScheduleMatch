package com.fbb.funapp.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
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
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fbb.funapp.R
import com.fbb.funapp.presentation.ui.theme.BackgroundColorBlue
import com.fbb.funapp.presentation.ui.theme.BorderTextFieldColor
import com.fbb.funapp.presentation.ui.theme.TextColorPrimary
import com.fbb.funapp.presentation.ui.theme.TextColorSubTitleGray
import com.fbb.funapp.presentation.ui.theme.TypographyStyle

@Composable
fun MyTextFieldTitle(
    modifier: Modifier = Modifier,
    title: String,
    labelText: String = "",
    placeholderText: String = "",
    icon: Int,
    text: String,
    onValueChange: (String) -> Unit,
    keyboardType: KeyboardType = KeyboardType.Number,
    maxLines: Int = 1,
    readOnly: Boolean = false,
    enabled: Boolean = true,
    onClickField: (() -> Unit)? = null
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
            placeholder = {
                Text(
                    text = placeholderText,
                    style = TypographyStyle.bodySmall.copy(
                        fontSize = 14.sp,
                        color = TextColorSubTitleGray
                    ),
                )
            },
            trailingIcon = {
                Icon(
                    modifier = Modifier.size(24.dp),
                    painter = painterResource(id = icon),
                    contentDescription = "Icon"
                )
            },
            readOnly = readOnly,
            modifier = modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
                .then(
                    if (onClickField != null) Modifier.clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null
                    ) { onClickField() }
                    else Modifier

                ),
            enabled = enabled,
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = keyboardType),
            singleLine = true,
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = BorderTextFieldColor,
                focusedBorderColor = BackgroundColorBlue,
                cursorColor = BackgroundColorBlue,
                focusedTrailingIconColor = BackgroundColorBlue,
                unfocusedTrailingIconColor = BorderTextFieldColor,
                disabledBorderColor = BorderTextFieldColor,
                disabledTextColor = TextColorPrimary,
            ),
            shape = RoundedCornerShape(8.dp),
            maxLines = maxLines
        )
    }

}

@Preview(showBackground = true)
@Composable
private fun MyTextFieldTitlePreview() {
    MyTextFieldTitle(
        labelText = "",
        title = "Username or Email",
        placeholderText = "Enter your username or email",
        icon = R.drawable.ic_title,
        keyboardType = KeyboardType.Text,
        text = "",
        onValueChange = {

        }
    )
}