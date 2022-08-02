package com.example.improvevocabulary.di

import android.content.Context
import com.example.domain.usecase.GetLanguageUseCase
import com.example.domain.usecase.SaveLanguageUseCase
import com.example.improvevocabulary.presentation.settings.SettingsViewModelFactory
import dagger.Module

@Module
class AppModule(val context: Context) {

    fun provideContext(): Context {
        return context
    }

    fun provideMainViewModelFactory(
        getLanguageUseCase: GetLanguageUseCase,
        saveLanguageUseCase: SaveLanguageUseCase
    ): SettingsViewModelFactory {
        return SettingsViewModelFactory(
            getLanguageUseCase = getLanguageUseCase,
            saveLanguageUseCase = saveLanguageUseCase
        )
    }
}