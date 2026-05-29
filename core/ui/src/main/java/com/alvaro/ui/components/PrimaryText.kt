package com.alvaro.ui.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import com.alvaro.ui.FontSize

@Composable
fun PrimaryText(
    value: String,
    fontSize: TextUnit,
    textColor: Color,
    modifier: Modifier = Modifier
) {
    Text(
        text = value,
        color = textColor,
        fontSize = fontSize,
        modifier = modifier
    )
}

@Preview
@Composable
private fun PrimaryTextPreview() {
    PrimaryText(
        value = "Hola",
        fontSize = FontSize.L,
        textColor = Color.Black
    )
}