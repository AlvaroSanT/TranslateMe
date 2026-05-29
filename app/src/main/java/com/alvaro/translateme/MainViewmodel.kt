package com.alvaro.translateme

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class MainViewmodel @Inject constructor() : ViewModel() {

    private val _uiState: MutableStateFlow<MainState> = MutableStateFlow(MainState())
    val uiState: StateFlow<MainState> = _uiState.asStateFlow()

    fun onLanguageQueryChange(newQuery: String) {
        _uiState.value = _uiState.value.copy(languageQuery = newQuery)
    }
}

data class MainState(
    val languageQuery: String = ""
)