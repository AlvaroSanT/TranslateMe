package com.alvaro.feature.language.mapper

import com.alvaro.feature.language.model.LanguageUiModel
import languages.model.Language

fun Language.toUiModel(): LanguageUiModel {
    return LanguageUiModel(
        code = code,
        name = name
    )
}
