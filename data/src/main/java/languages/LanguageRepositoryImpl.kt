package com.alvaro.data.languages

import android.util.Log
import com.google.mlkit.nl.translate.TranslateLanguage
import languages.LanguagesRepository
import javax.inject.Inject

class LanguageRepositoryImpl @Inject constructor() : LanguagesRepository {

    override fun getAllLanguages(): List<String> {
        Log.d("LanguageRepositoryImpl", "getAllLanguages called")
        val languages = TranslateLanguage.getAllLanguages().toList()
        Log.d("LanguageRepositoryImpl", "Returning ${languages.size} languages from ML Kit")
        return languages
    }

}
