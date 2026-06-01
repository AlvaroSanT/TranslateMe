package com.alvaro.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
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
import com.alvaro.ui.ColorPrimaryTextFieldText
import com.alvaro.ui.ColorPrimaryTopBarBorder
import com.alvaro.ui.ColorPrimaryTopBarHint
import com.alvaro.ui.FontSize
import com.alvaro.ui.Padding

@Composable
fun PrimaryTopBar(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    hint: String = ""
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = Padding.XL, vertical = Padding.M)
            .border(
                width = BorderWidth.XS,
                color = ColorPrimaryTopBarBorder,
                shape = RoundedCornerShape(BorderRadius.XXL)

            )
    ) {
        BasicTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = Padding.L, vertical = Padding.S),
            value = value,
            textStyle = TextStyle(
                color = ColorPrimaryTextFieldText,
                fontSize = FontSize.L
            ),
            onValueChange = { text ->
                onValueChange(text)
            },
            decorationBox = { innerTextField ->
                Box {
                    if (value.isEmpty() && hint.isNotEmpty()) {
                        PrimaryText(
                            text = hint,
                            fontSize = FontSize.L,
                            textColor = ColorPrimaryTopBarHint
                        )
                    }
                    innerTextField()
                }
            }
        )
    }
}

@Preview
@Composable
private fun PrimaryTopBarPreview() {
    PrimaryTopBar(
        value = "",
        hint = "Introduce tu idioma...",
        onValueChange = { }
    )
}