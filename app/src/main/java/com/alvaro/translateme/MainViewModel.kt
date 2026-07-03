package com.alvaro.translateme

import androidx.compose.runtime.State
import androidx.lifecycle.ViewModel
import com.alvaro.translateme.navigation.NavigationManager
import com.alvaro.translateme.navigation.Route
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

sealed interface MainIntent {
    data class SwitchTab(val tab: Route) : MainIntent
    data object GoBack : MainIntent
}

@HiltViewModel
class MainViewModel @Inject constructor(
    private val navigationManager: NavigationManager
) : ViewModel() {

    val currentTab: State<Route> = navigationManager.currentTab
    val backStack: List<Route> = navigationManager.backStack

    fun handleIntent(intent: MainIntent) {
        when (intent) {
            is MainIntent.SwitchTab -> navigationManager.navigateTo(intent.tab)
            MainIntent.GoBack -> navigationManager.goBack()
        }
    }
}
