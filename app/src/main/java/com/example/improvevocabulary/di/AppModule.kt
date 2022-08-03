package com.example.improvevocabulary.di

import android.content.Context
import com.example.domain.usecase.GetLanguageUseCase
import com.example.domain.usecase.GetThemeUseCase
import com.example.domain.usecase.SaveLanguageUseCase
import com.example.domain.usecase.SaveThemeUseCase
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
}