package com.example.improvevocabulary.di

import com.example.domain.repositoriesI.LanguageRepository
import com.example.domain.repositoriesI.ThemeRepository
import com.example.domain.usecase.GetLanguageUseCase
import com.example.domain.usecase.GetThemeUseCase
import com.example.domain.usecase.SaveLanguageUseCase
import com.example.domain.usecase.SaveThemeUseCase
import dagger.Module
import dagger.Provides

@Module
class DomainModule {

    @Provides
    fun provideGetLanguageUseCase(languageRepository: LanguageRepository): GetLanguageUseCase{
        return GetLanguageUseCase(languageRepository = languageRepository)
    }

    @Provides
    fun provideSaveLanguageUseCase(languageRepository: LanguageRepository): SaveLanguageUseCase{
        return SaveLanguageUseCase(languageRepository = languageRepository)
    }

    @Provides
    fun provideGetThemeUseCase(themeRepository: ThemeRepository): GetThemeUseCase{
        return GetThemeUseCase(themeRepository = themeRepository)
    }

    @Provides
    fun provideSaveThemeUseCase(themeRepository: ThemeRepository): SaveThemeUseCase {
        return SaveThemeUseCase(themeRepository = themeRepository)
    }
}