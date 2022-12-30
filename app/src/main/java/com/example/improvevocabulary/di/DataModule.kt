package com.example.improvevocabulary.di


import android.content.Context
import com.example.data.storage.interfaces.*
import com.example.data.repository.repositoriesImpl.LanguageFromLearningRepository
import com.example.data.repository.repositoriesImpl.WordPairRepository
import com.example.data.storage.sharedPrefs.*
import com.example.domain.repositoriesI.*
import dagger.Module
import dagger.Provides

@Module
class DataModule {

    @Provides
    fun provideWasTestDescriptionShownOnceStorage(context: Context): WasTestDescriptionShownOnceStorage {
        return SharedPrefsWasTestDescriptionShownOnce(context = context)
    }

    @Provides
    fun provideWasPracticeDescriptionShownOnceStorage(context: Context): WasPracticeDescriptionShownOnceStorage {
        return SharedPrefsWasPracticeDescriptionShownOnce(context = context)
    }

    @Provides
    fun provideAppLanguageStorage(context: Context): AppLanguageStorage {
        return SharedPrefsAppLanguageStorage(context = context)
    }

    @Provides
    fun provideLanguageFromLearningStorage(context: Context): LanguageFromLearningStorage {
        return SharedPrefsLanguageFromLearning(context = context)
    }

    @Provides
    fun provideRepetitionWasOfferedStorage(context: Context): RepetitionWasOfferedStorage {
        return SharedPrefsRepetitionWasOfferedToday(context = context)
    }

    @Provides
    fun provideIsFirstTimeLaunchStorage(context: Context): IsFirstTimeLaunchStorage {
        return SharedPrefsIsFirstTimeLaunch(context = context)
    }

    @Provides
    fun provideLanguageOfLearningStorage(context: Context): LanguageOfLearningStorage {
        return SharedPrefsLanguageOfLearning(context = context)
    }

    @Provides
    fun provideAppLanguageRepository(appLanguageStorage: AppLanguageStorage): AppLanguageRepository {
        return com.example.data.repository.repositoriesImpl.AppLanguageRepository(
            appLanguageStorage = appLanguageStorage
        )
    }

    @Provides
    fun provideIsFirstTimeLaunchRepository(isFirstTimeLaunchStorage: IsFirstTimeLaunchStorage): IsFirstTimeLaunchRepository {
        return com.example.data.repository.repositoriesImpl.IsFirstTimeLaunchRepository(
            isFirstTimeLaunchStorage = isFirstTimeLaunchStorage
        )
    }

    @Provides
    fun provideWasTestDescriptionShownOnceRepository(wasTestDescriptionShownOnceStorage: WasTestDescriptionShownOnceStorage): WasTestDescriptionShownOnceRepository {
        return com.example.data.repository.repositoriesImpl.WasTestDescriptionShownOnceRepository(
            wasTestDescriptionShownOnceStorage = wasTestDescriptionShownOnceStorage
        )
    }

    @Provides
    fun provideWasPracticeDescriptionShownOnceRepository(wasPracticeDescriptionShownOnceStorage: WasPracticeDescriptionShownOnceStorage): WasPracticeDescriptionShownOnceRepository {
        return com.example.data.repository.repositoriesImpl.WasPracticeDescriptionShownOnceRepository(
            wasPracticeDescriptionShownOnceStorage = wasPracticeDescriptionShownOnceStorage
        )
    }

    @Provides
    fun provideRepetitionWasOfferedRepository(repetitionWasOfferedStorage: RepetitionWasOfferedStorage): RepetitionWasOfferedRepository {
        return com.example.data.repository.repositoriesImpl.RepetitionWasOfferedRepository(
            repetitionWasOfferedStorage = repetitionWasOfferedStorage
        )
    }

    @Provides
    fun provideThemeStorage(context: Context): ThemeStorage {
        return SharedPrefsThemeStorage(context = context)
    }

    @Provides
    fun provideThemeRepository(themeStorage: ThemeStorage): ThemeRepository {
        return com.example.data.repository.repositoriesImpl.ThemeRepository(themeStorage = themeStorage)
    }

    @Provides
    fun provideFilterByStorage(context: Context): FilterByStorage {
        return SharedPrefsFilterByStorage(context = context)
    }

    @Provides
    fun provideFilterByRepository(filterByStorage: FilterByStorage): FilterByRepository {
        return com.example.data.repository.repositoriesImpl.FilterByRepository(
            filterByStorage = filterByStorage
        )
    }

    @Provides
    fun provideWordPairRepository(context: Context): WordPairRepository {
        return WordPairRepository(context = context)
    }
}