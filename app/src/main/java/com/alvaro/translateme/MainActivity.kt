package com.alvaro.translateme

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.runtime.getValue
import com.alvaro.translateme.navigation.MainNavDisplay
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val isReady by viewModel.isReady
            
            if (isReady) {
                val currentTab by viewModel.currentTab
                val isBottomBarVisible = currentTab != null
                
                Scaffold(
                    bottomBar = {
                        if (isBottomBarVisible) {
                            MainBottomBar(
                                currentTab = currentTab!!,
                                onTabSelected = { route ->
                                    viewModel.handleIntent(MainIntent.SwitchTab(route))
                                }
                            )
                        }
                    }
                ) { innerPadding ->
                MainNavDisplay(
                    backStack = viewModel.backStack,
                    onBack = {
                        viewModel.handleIntent(MainIntent.GoBack)
                    },
                    modifier = Modifier.padding(innerPadding)
                )
                }
            }
        }
    }
}
