package com.example.improvevocabulary.di

import android.content.Context
import com.example.data.storage.LanguageStorage
import com.example.data.storage.SharedPrefsLanguageStorage
import com.example.domain.repositoriesI.LanguageRepository
import dagger.Module
import dagger.Provides

@Module
class DataModule {

    @Provides
    fun provideDataStorage(context: Context): LanguageStorage {
        return SharedPrefsLanguageStorage(context = context)
    }

    @Provides
    fun provideDataRepository(languageStorage: LanguageStorage): LanguageRepository {
        return com.example.data.repositoriesImpl.LanguageRepository(languageStorage = languageStorage)
    }
}