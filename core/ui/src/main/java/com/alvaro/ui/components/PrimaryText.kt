package com.alvaro.ui.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import com.alvaro.ui.ColorPrimaryText
import com.alvaro.ui.FontSize

@Composable
fun PrimaryText(
    text: String,
    modifier: Modifier = Modifier,
    fontSize: TextUnit = FontSize.L,
    textColor: Color = ColorPrimaryText,
) {
    Text(
        text = text,
        color = textColor,
        fontSize = fontSize,
        modifier = modifier
    )
}

@Preview
@Composable
private fun PrimaryTextPreview() {
    PrimaryText(
        text = "Hola",
        fontSize = FontSize.L,
        textColor = Color.Black
    )
}