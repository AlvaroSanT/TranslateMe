package com.alvaro.data.translations.api.response

import com.google.gson.annotations.SerializedName

data class DeepLTranslation(
    @SerializedName("detected_source_language")
    val detectedSourceLanguage: String,
    val text: String
)