package com.alvaro.translateme

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.alvaro.ui.PrimaryTextField

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Scaffold { contentPadding ->
                Column(
                    modifier = Modifier.padding(contentPadding),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    var textToTranslate = ""
                    var translatedText = ""

                    PrimaryTextField(
                        value = textToTranslate,
                        onValueChange = {
                            textToTranslate = it
                        }
                    )

                    PrimaryTextField(
                        value = translatedText,
                        onValueChange = {
                            translatedText = it
                        }
                    )
                }
            }
        }
    }
}