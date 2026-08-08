package languages

import android.util.Log
import languages.mapper.toDomainLanguage
import languages.model.Language
import javax.inject.Inject

class GetAllLanguagesUseCase @Inject constructor(
    private val repository: LanguagesRepository
) {
    operator fun invoke(): List<Language> {
        Log.d("GetAllLanguagesUseCase", "Invoking GetAllLanguagesUseCase")
        val languages = repository.getAllLanguages().map {
            it.toDomainLanguage()
        }
        Log.d("GetAllLanguagesUseCase", "Fetched ${languages.size} languages")
        return languages
    }
}