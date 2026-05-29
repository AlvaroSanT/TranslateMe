package com.alvaro.translateme

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import dagger.hilt.android.AndroidEntryPoint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.alvaro.ui.components.PrimaryTopBar

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
                Column(modifier = Modifier.padding(contentPadding)) { }
            }
        }
    }
}
