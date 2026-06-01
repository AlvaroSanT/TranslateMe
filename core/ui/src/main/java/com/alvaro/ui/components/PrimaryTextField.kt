package com.alvaro.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import com.alvaro.ui.BorderRadius
import com.alvaro.ui.BorderWidth
import com.alvaro.ui.ColorPrimaryTextFieldBackground
import com.alvaro.ui.ColorPrimaryTextFieldBorder
import com.alvaro.ui.ColorPrimaryTextFieldText
import com.alvaro.ui.Padding

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
            modifier = Modifier
                .fillMaxWidth()
                .padding(Padding.S),
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