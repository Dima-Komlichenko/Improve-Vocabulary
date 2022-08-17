package com.example.improvevocabulary.di

import android.content.Context
import com.example.domain.usecase.*
import com.example.improvevocabulary.presentation.filter.FilterViewModelFactory
import com.example.improvevocabulary.presentation.settings.SettingsViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class AppModule(val context: Context) {

    @Provides
    fun provideContext(): Context {
        return context
    }

    @Provides
    fun provideSettingsViewModelFactory(
        getLanguageUseCase: GetLanguageUseCase,
        saveLanguageUseCase: SaveLanguageUseCase,
        getThemeUseCase: GetThemeUseCase,
        saveThemeUseCase: SaveThemeUseCase
    ): SettingsViewModelFactory {
        return SettingsViewModelFactory(
            getLanguageUseCase = getLanguageUseCase,
            saveLanguageUseCase = saveLanguageUseCase,
            getThemeUseCase = getThemeUseCase,
            saveThemeUseCase = saveThemeUseCase
        )
    }

    @Provides
    fun provideFilterByViewModelFactory(
        getFilterByUseCase: GetFilterByUseCase,
        saveFilterByUseCase: SaveFilterByUseCase
    ): FilterViewModelFactory {
        return FilterViewModelFactory(
            getFilterByUseCase = getFilterByUseCase,
            saveFilterByUseCase = saveFilterByUseCase
        )
    }
}