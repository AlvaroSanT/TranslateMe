package translation

interface TranslationRepository {
    suspend fun translate(
        text: String,
        sourceLanguage: String,
        targetLanguage: String
    ): Result<String>
}
