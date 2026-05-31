package languages

import languages.mapper.toDomainLanguage
import languages.model.Language
import javax.inject.Inject

class GetAllLanguagesUseCase @Inject constructor(
    private val repository: LanguagesRepository
) {
    operator fun invoke(): List<Language> {
        return repository.getAllLanguages().map {
            it.toDomainLanguage()
        }
    }
}