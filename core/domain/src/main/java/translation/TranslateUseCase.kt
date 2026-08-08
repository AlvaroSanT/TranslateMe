package translation

import android.util.Log
import javax.inject.Inject

class TranslateUseCase @Inject constructor(
    private val repository: TranslationRepository
) {
    suspend operator fun invoke(
        text: String,
        sourceLanguage: String,
        targetLanguage: String
    ): Result<String> {
        Log.d("TranslateUseCase", "Translating: \"$text\" from $sourceLanguage to $targetLanguage")
        val result = repository.translate(text, sourceLanguage, targetLanguage)
        result.onSuccess {
            Log.d("TranslateUseCase", "Translation successful: $it")
        }.onFailure {
            Log.e("TranslateUseCase", "Translation failed", it)
        }
        return result
    }
}
