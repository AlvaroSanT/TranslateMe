package languages.mapper

import languages.model.Language
import java.util.Locale

fun String.toDomainLanguage(): Language {
    val locale = Locale.forLanguageTag(this)
    return Language(
        code = this,
        name = locale.displayLanguage
    )
}