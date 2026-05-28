import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import translation.TranslateUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewmodel @Inject constructor(
    private val translateUseCase: TranslateUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState>(UiState.Idle)
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    fun translate(text: String, source: String, target: String) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            val result = translateUseCase(text, source, target)
            result.onSuccess {
                _uiState.value = UiState.Success(it)
            }.onFailure {
                _uiState.value = UiState.Error(it.message ?: "Unknown error")
            }
        }
    }

    sealed class UiState {
        object Idle : UiState()
        object Loading : UiState()
        data class Success(val translatedText: String) : UiState()
        data class Error(val message: String) : UiState()
    }
}
