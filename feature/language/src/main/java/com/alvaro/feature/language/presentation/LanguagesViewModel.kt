package com.alvaro.feature.language.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alvaro.feature.language.mapper.toUiModel
import com.alvaro.feature.language.model.LanguageUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted.Companion.WhileSubscribed
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import languages.GetAllLanguagesUseCase
import user.SaveUserLanguageUseCase
import javax.inject.Inject

@HiltViewModel
class LanguagesViewModel @Inject constructor(
    private val getAllLanguagesUseCase: GetAllLanguagesUseCase,
    private val saveUserLanguageUseCase: SaveUserLanguageUseCase
) : ViewModel() {

    private val _languageQuery: MutableStateFlow<String> = MutableStateFlow("")
    private val languagesList: StateFlow<List<LanguageUiModel>> = flow {
        emit(getAllLanguagesUseCase().map {
            it.toUiModel()
        })
    }.stateIn(
        scope = viewModelScope,
        started = WhileSubscribed(5_000),
        initialValue = emptyList()
    )

    val uiState: StateFlow<LanguagesState> = combine(
        languagesList,
        _languageQuery
    ) { languages, query ->
        LanguagesState(
            languageQuery = query,
            languages = languages
        )
    }.stateIn(
        scope = viewModelScope,
        started = WhileSubscribed(5_000),
        initialValue = LanguagesState()
    )

    fun onLanguageQueryChange(newQuery: String) {
        _languageQuery.update {
            newQuery
        }
    }

    fun onLanguageSelected(language: LanguageUiModel) {
        viewModelScope.launch {
            saveUserLanguageUseCase(
                code = language.code,
                name = language.name
            ).onSuccess {
                Log.d("LanguagesViewModel", "Language ${language.name} saved successfully")
            }.onFailure {
                Log.e("LanguagesViewModel", "Error saving language ${language.name}", it)
            }
        }
    }
}

data class LanguagesState(
    val languageQuery: String = "",
    val languages: List<LanguageUiModel> = emptyList()
)
