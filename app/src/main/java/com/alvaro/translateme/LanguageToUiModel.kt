package com.alvaro.translateme

import languages.model.Language

fun Language.toUiModel(): LanguageUiModel {
    return LanguageUiModel(
        code = code,
        name = name
    )
}