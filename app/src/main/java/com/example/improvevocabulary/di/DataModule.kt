package com.example.improvevocabulary.di


import android.content.Context
import com.example.data.storage.FilterByStorage
import com.example.data.storage.LanguageStorage
import com.example.data.storage.ThemeStorage
import com.example.data.storage.sharedPrefs.SharedPrefsFilterByStorage
import com.example.data.storage.sharedPrefs.SharedPrefsLanguageStorage
import com.example.data.storage.sharedPrefs.SharedPrefsThemeStorage
import com.example.domain.repositoriesI.FilterByRepository
import com.example.domain.repositoriesI.LanguageRepository
import com.example.domain.repositoriesI.ThemeRepository
import dagger.Module
import dagger.Provides

@Module
class DataModule {

    @Provides
    fun provideLanguageStorage(context: Context): LanguageStorage {
        return SharedPrefsLanguageStorage(context = context)
    }

    @Provides
    fun provideLanguageRepository(languageStorage: LanguageStorage): LanguageRepository {
        return com.example.data.repositoriesImpl.LanguageRepository(languageStorage = languageStorage)
    }

    @Provides
    fun provideThemeStorage(context: Context): ThemeStorage {
        return SharedPrefsThemeStorage(context = context)
    }

    @Provides
    fun provideThemeRepository(themeStorage: ThemeStorage): ThemeRepository {
        return com.example.data.repositoriesImpl.ThemeRepository(themeStorage = themeStorage)
    }

    @Provides
    fun provideFilterByStorage(context: Context): FilterByStorage {
        return SharedPrefsFilterByStorage(context = context)
    }

    @Provides
    fun provideFilterByRepository(filterByStorage: FilterByStorage): FilterByRepository {
        return com.example.data.repositoriesImpl.FilterByRepository(filterByStorage = filterByStorage)
    }
}