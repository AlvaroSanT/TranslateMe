package com.alvaro.data.translations.api

import com.alvaro.data.translations.api.response.DeepLResponse
import com.google.gson.annotations.SerializedName
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Header
import retrofit2.http.POST

interface DeepLApi {
    @FormUrlEncoded
    @POST("v2/translate")
    suspend fun translate(
        @Header("Authorization") authKey: String,
        @Field("text") text: String,
        @Field("target_lang") targetLang: String,
        @Field("source_lang") sourceLang: String? = null
    ): DeepLResponse
}
