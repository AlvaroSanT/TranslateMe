package com.alvaro.data.languages

import com.google.mlkit.nl.translate.TranslateLanguage
import languages.LanguagesRepository
import javax.inject.Inject

class LanguageRepositoryImpl @Inject constructor() : LanguagesRepository {

    override fun getAllLanguages(): List<String> {
        return TranslateLanguage.getAllLanguages().toList()
    }

}
