package com.alvaro.data.di

import com.alvaro.data.languages.LanguageRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import languages.LanguagesRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class LanguagesModule {

    @Binds
    @Singleton
    abstract fun bindLanguagesRepository(
        languageRepositoryImpl: LanguageRepositoryImpl
    ): LanguagesRepository

}