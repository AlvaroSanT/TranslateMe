package com.alvaro.translateme

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alvaro.translateme.navigation.NavigationManager
import com.alvaro.translateme.navigation.Route
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import user.GetUserLanguageStreamUseCase
import javax.inject.Inject

sealed interface MainIntent {
    data class SwitchTab(val tab: Route) : MainIntent
    data object GoBack : MainIntent
    data class UpdateNavigationState(val hasLanguage: Boolean) : MainIntent
}

@HiltViewModel
class MainViewModel @Inject constructor(
    private val navigationManager: NavigationManager,
    private val getUserLanguageStreamUseCase: GetUserLanguageStreamUseCase
) : ViewModel() {

    private val _isReady: MutableState<Boolean> = mutableStateOf(false)
    val isReady: State<Boolean> = _isReady

    val currentTab: State<Route?> = navigationManager.currentTab
    val backStack: List<Route> = navigationManager.backStack

    init {
        Log.d("MainViewModel", "Initializing MainViewModel")
        observeLanguageChanges()
    }

    private fun observeLanguageChanges() {
        Log.d("MainViewModel", "Observing language changes")
        viewModelScope.launch {
            getUserLanguageStreamUseCase().collectLatest { language ->
                Log.d("MainViewModel", "Language change detected: $language")
                handleIntent(MainIntent.UpdateNavigationState(language != null))
            }
        }
    }

    fun handleIntent(intent: MainIntent) {
        Log.d("MainViewModel", "handleIntent: $intent")
        when (intent) {
            is MainIntent.SwitchTab -> navigationManager.navigateTo(intent.tab)
            MainIntent.GoBack -> navigationManager.goBack()
            is MainIntent.UpdateNavigationState -> {
                processNavigationUpdate(intent.hasLanguage)
                _isReady.value = true
            }
        }
    }

    private fun processNavigationUpdate(hasLanguage: Boolean) {
        val backStack = navigationManager.backStack
        Log.d("MainViewModel", "processNavigationUpdate: hasLanguage=$hasLanguage, backStackSize=${backStack.size}")
        
        when {
            hasLanguage && backStack.contains(Route.LanguagesRoute) -> {
                Log.d("MainViewModel", "Navigating from Languages to Practice")
                navigationManager.navigateAndPopUpTo(
                    route = Route.PracticeRoute,
                    popUpTo = Route.LanguagesRoute,
                    inclusive = true
                )
            }
            hasLanguage && backStack.isEmpty() -> {
                Log.d("MainViewModel", "Setting initial route to Practice")
                navigationManager.setInitialRoute(Route.PracticeRoute)
            }
            !hasLanguage && backStack.isEmpty() -> {
                Log.d("MainViewModel", "Setting initial route to Languages")
                navigationManager.setInitialRoute(Route.LanguagesRoute)
            }
        }
    }
}
