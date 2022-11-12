package com.example.improvevocabulary.di


import android.content.Context
import com.example.data.storage.interfaces.*
import com.example.data.storage.repositoriesImpl.LanguageFromLearningRepository
import com.example.domain.repositoriesI.FilterByRepository
import com.example.domain.repositoriesI.AppLanguageRepository
import com.example.domain.repositoriesI.ThemeRepository
import com.example.data.storage.repositoriesImpl.WordPairRepository
import com.example.data.storage.sharedPrefs.*
import dagger.Module
import dagger.Provides

@Module
class DataModule {

    @Provides
    fun provideAppLanguageStorage(context: Context): AppLanguageStorage {
        return SharedPrefsAppLanguageStorage(context = context)
    }

    @Provides
    fun provideLanguageFromLearningStorage(context: Context): LanguageFromLearningStorage {
        return SharedPrefsLanguageFromLearning(context = context)
    }

    @Provides
    fun provideLanguageOfLearningStorage(context: Context): LanguageOfLearningStorage {
        return SharedPrefsLanguageOfLearning(context = context)
    }

    @Provides
    fun provideAppLanguageRepository(appLanguageStorage: AppLanguageStorage): AppLanguageRepository {
        return com.example.data.storage.repositoriesImpl.AppLanguageRepository(
            appLanguageStorage = appLanguageStorage
        )
    }



    @Provides
    fun provideThemeStorage(context: Context): ThemeStorage {
        return SharedPrefsThemeStorage(context = context)
    }

    @Provides
    fun provideThemeRepository(themeStorage: ThemeStorage): ThemeRepository {
        return com.example.data.storage.repositoriesImpl.ThemeRepository(themeStorage = themeStorage)
    }

    @Provides
    fun provideFilterByStorage(context: Context): FilterByStorage {
        return SharedPrefsFilterByStorage(context = context)
    }

    @Provides
    fun provideFilterByRepository(filterByStorage: FilterByStorage): FilterByRepository {
        return com.example.data.storage.repositoriesImpl.FilterByRepository(
            filterByStorage = filterByStorage
        )
    }

    @Provides
    fun provideWordPairRepository(context: Context): WordPairRepository {
        return WordPairRepository(context = context)
    }
}