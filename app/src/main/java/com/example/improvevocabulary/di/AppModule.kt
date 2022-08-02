package com.example.improvevocabulary.di

import android.content.Context
import com.example.domain.usecase.GetLanguageUseCase
import com.example.domain.usecase.SaveLanguageUseCase
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