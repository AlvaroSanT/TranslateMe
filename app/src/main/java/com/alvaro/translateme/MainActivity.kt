package com.alvaro.translateme

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.ui.Modifier
import com.alvaro.feature.language.presentation.LanguagesScreen
import com.alvaro.ui.components.BottomNavigationBar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Column {
                val shouldShowNavigationBar = true

                LanguagesScreen(modifier = Modifier.weight(1f))

                if (shouldShowNavigationBar) {
                    BottomNavigationBar()
                }
            }
        }
    }
}
