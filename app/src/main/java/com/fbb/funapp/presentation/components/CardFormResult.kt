package com.fbb.funapp.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fbb.funapp.presentation.ui.theme.BgColorBlueYoung
import com.fbb.funapp.presentation.ui.theme.TextColorTitleGray
import com.fbb.funapp.presentation.ui.theme.TypographyStyle

@Composable
fun CardFormResult(
    modifier: Modifier = Modifier,
    title: String,
    value: String
) {
    Surface(
        modifier = modifier
            .wrapContentHeight()
            .fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        color = BgColorBlueYoung
    ) {
        Column(
            modifier = Modifier.padding(12.dp)
        ) {
            Text(
                text = title,
                style = TypographyStyle.bodySmall.copy(fontSize = 16.sp, color = TextColorTitleGray)
            )

            Text(
                text = value,
                style = TypographyStyle.titleMedium.copy(fontSize = 20.sp)
            )
        }
    }
}

@Preview
@Composable
private fun CardFormResultPreview() {
    CardFormResult(title = "Fields", value = "2")
}