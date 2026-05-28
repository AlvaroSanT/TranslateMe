package translation

import javax.inject.Inject

class TranslateUseCase @Inject constructor(
    private val repository: TranslationRepository
) {
    suspend operator fun invoke(
        text: String,
        sourceLanguage: String,
        targetLanguage: String
    ): Result<String> {
        return repository.translate(text, sourceLanguage, targetLanguage)
    }
}
