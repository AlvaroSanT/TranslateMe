package com.alvaro.feature.language.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import com.alvaro.ui.ColorArrowForward
import com.alvaro.ui.Padding
import com.alvaro.ui.R
import com.alvaro.ui.Size
import com.alvaro.ui.components.PrimaryText

@Composable
fun LanguageCard(
    languageName: String,
    languageCode: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .clickable { onClick() }
            .padding(horizontal = Padding.XL, vertical = Padding.S),
        verticalAlignment = Alignment.CenterVertically
    ) {

        AsyncImage(
            model = stringResource(R.string.language_flag_url, languageCode),
            contentDescription = "Flag of $languageName",
            modifier = Modifier.size(Size.XXL)
        )

        Spacer(modifier = Modifier.width(Padding.M))

        PrimaryText(
            text = languageName,
            modifier = Modifier.weight(1f)
        )

        Icon(
            painter = painterResource(id = R.drawable.arrow_right),
            contentDescription = "Language Icon",
            tint = ColorArrowForward,
            modifier = Modifier.size(Size.XXL)
        )

    }
}

@Preview
@Composable
private fun LanguageCardPreview() {
    LanguageCard(
        languageName = "English",
        languageCode = "en",
        onClick = {}
    )
}