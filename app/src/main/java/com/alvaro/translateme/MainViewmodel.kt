package com.alvaro.translateme

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted.Companion.WhileSubscribed
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import languages.GetAllLanguagesUseCase
import javax.inject.Inject

@HiltViewModel
class MainViewmodel @Inject constructor(
    private val getAllLanguagesUseCase: GetAllLanguagesUseCase
) : ViewModel() {

    private val _languageQuery: MutableStateFlow<String> = MutableStateFlow("")
    private val languagesList: StateFlow<List<LanguageUiModel>> = flow {
        getAllLanguagesUseCase().sortedBy { it.code }.map {
            Log.d("CODE", it.code)
        }
        emit(getAllLanguagesUseCase().map {
            it.toUiModel()
        })
    }.stateIn(
        scope = viewModelScope,
        started = WhileSubscribed(5_000),
        initialValue = emptyList()
    )

    val uiState: StateFlow<MainState> = combine(
        languagesList,
        _languageQuery
    ) { languages, query ->
        MainState(
            languageQuery = query,
            languages = languages
        )
    }.stateIn(
        scope = viewModelScope,
        started = WhileSubscribed(5_000),
        initialValue = MainState()
    )

    fun onLanguageQueryChange(newQuery: String) {
        _languageQuery.update {
            newQuery
        }
    }
}

data class MainState(
    val languageQuery: String = "",
    val languages: List<LanguageUiModel> = emptyList()
)