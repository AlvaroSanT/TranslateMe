package com.alvaro.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun PrimaryTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .background(ColorPrimaryTextFieldBackground)
            .border(
                width = BorderWidth.XS,
                color = ColorPrimaryTextFieldBorder,
                shape = RoundedCornerShape(BorderRadius.XS))
    ) {
        BasicTextField(
            modifier = Modifier.padding(Padding.S),
            value = value,
            textStyle = TextStyle(color = ColorPrimaryTextFieldText),
            onValueChange = { text ->
                onValueChange(text)
            }
        )
    }
}

@Preview
@Composable
fun PrimaryTextFieldPreview() {
    PrimaryTextField(
        value = "Hola",
        onValueChange = { }
    )
}