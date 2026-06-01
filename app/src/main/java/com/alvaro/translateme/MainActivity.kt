package com.alvaro.translateme

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.alvaro.ui.BorderWidth
import com.alvaro.ui.ColorBackground
import com.alvaro.ui.ColorSeparator
import com.alvaro.ui.Padding
import com.alvaro.ui.Spacing
import com.alvaro.ui.components.LanguageCard
import com.alvaro.ui.components.PrimaryTopBar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val viewModel: MainViewmodel = hiltViewModel()
            val uiState = viewModel.uiState.collectAsState()

            Scaffold(
                modifier = Modifier
                    .background(ColorBackground)
                    .statusBarsPadding()
                    .fillMaxSize(),
                topBar = {
                    PrimaryTopBar(
                        value = uiState.value.languageQuery,
                        onValueChange = {
                            viewModel.onLanguageQueryChange(it)
                        },
                        hint = stringResource(R.string.which_language_do_you_study)
                    )
                }
            ) { contentPadding ->
                val filteredLanguages = uiState.value.languages.filter {
                    it.name.lowercase().startsWith(
                        uiState.value.languageQuery.lowercase()
                    )
                }
                LazyColumn(
                    modifier = Modifier.padding(contentPadding),
                    verticalArrangement = Arrangement.spacedBy(Spacing.S)
                ) {
                    itemsIndexed(filteredLanguages) { index, language ->
                        LanguageCard(
                            languageName = language.name,
                            languageCode = language.code,
                            onClick = {
                                viewModel.onLanguageSelected(language)
                            }
                        )
                        if (index < filteredLanguages.lastIndex) {
                            HorizontalDivider(
                                modifier = Modifier.padding(horizontal = Padding.XS),
                                color = ColorSeparator,
                                thickness = BorderWidth.XS
                            )
                        }
                    }
                }
            }
        }
    }
}
