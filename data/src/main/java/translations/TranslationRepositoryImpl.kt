package com.alvaro.data.translations

import android.util.Log
import com.alvaro.data.translations.api.DeepLApi
import translation.TranslationRepository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class TranslationRepositoryImpl @Inject constructor() : TranslationRepository {

    private val api: DeepLApi by lazy {
        val logging = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        val client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()

        Retrofit.Builder()
            .baseUrl("https://api-free.deepl.com/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(DeepLApi::class.java)
    }

    private val apiKey = "DeepL-Auth-Key 84cd4fab-c9b4-4edd-a5d5-06f9ad5afcb1:fx"

    override suspend fun translate(
        text: String,
        sourceLanguage: String,
        targetLanguage: String
    ): Result<String> {
        Log.d("TranslationRepositoryImpl", "translate: text=\"$text\", source=$sourceLanguage, target=$targetLanguage")
        return try {
            val response = api.translate(
                authKey = apiKey,
                text = text,
                targetLang = targetLanguage.uppercase(),
                sourceLang = sourceLanguage.uppercase()
            )

            val translatedText = response.translations.firstOrNull()?.text
            
            if (translatedText != null) {
                Log.d("TranslationRepositoryImpl", "Translation success: $translatedText")
                Result.success(translatedText)
            } else {
                Log.e("TranslationRepositoryImpl", "Translation error: No translation found in response")
                Result.failure(Exception("No translation found"))
            }
        } catch (e: Exception) {
            Log.e("TranslationRepositoryImpl", "Translation exception", e)
            Result.failure(e)
        }
    }
}
