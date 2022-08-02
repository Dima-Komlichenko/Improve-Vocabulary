package com.example.improvevocabulary.di

import android.content.Context
import com.example.data.storage.LanguageStorage
import com.example.data.storage.SharedPrefsLanguageStorage
import com.example.domain.repositoriesI.LanguageRepository
import dagger.Module

@Module
class DataModule {
    fun provideDataStorage(context: Context): LanguageStorage {
        return SharedPrefsLanguageStorage(context = context)
    }

    fun provideDataRepository(languageStorage: LanguageStorage): LanguageRepository {
        return com.example.data.repositoriesImpl.LanguageRepository(languageStorage = languageStorage)
    }
}