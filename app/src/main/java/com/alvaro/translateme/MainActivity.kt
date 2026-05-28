package com.alvaro.translateme

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import dagger.hilt.android.AndroidEntryPoint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.alvaro.ui.Padding
import com.alvaro.ui.PrimaryButton
import com.alvaro.ui.PrimaryTextField
import com.alvaro.ui.Spacing

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val viewModel: MainViewmodel = hiltViewModel()
            val uiState by viewModel.uiState.collectAsState()

            Scaffold { contentPadding ->
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(contentPadding),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    var textToTranslate by remember { mutableStateOf("") }

                    PrimaryTextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = Padding.M),
                        value = textToTranslate,
                        onValueChange = {
                            textToTranslate = it
                        }
                    )

                    Spacer(modifier = Modifier.height(Spacing.M))

                    PrimaryButton(
                        text = "Traducir",
                        onClick = {
                            viewModel.translate(
                                textToTranslate,
                                "EN",
                                "ES"
                            )
                        }
                    )

                    Spacer(modifier = Modifier.height(Spacing.M))

                    when (val state = uiState) {
                        is MainViewmodel.UiState.Loading -> Text("Traduciendo...")
                        is MainViewmodel.UiState.Success -> Text("Resultado: ${state.translatedText}")
                        is MainViewmodel.UiState.Error -> Text("Error: ${state.message}")
                        else -> {}
                    }
                }
            }
        }
    }
}
