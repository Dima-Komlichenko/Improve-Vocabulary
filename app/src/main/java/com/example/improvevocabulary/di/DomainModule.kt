package com.example.improvevocabulary.di

import com.example.domain.repositoriesI.FilterByRepository
import com.example.domain.repositoriesI.LanguageRepository
import com.example.domain.repositoriesI.ThemeRepository
import com.example.domain.usecase.*
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

    @Provides
    fun provideGetFilterByUseCase(filterByRepository: FilterByRepository): GetFilterByUseCase {
        return GetFilterByUseCase(filterByRepository = filterByRepository)
    }

    @Provides
    fun provideSaveFilterByUseCase(filterByRepository: FilterByRepository): SaveFilterByUseCase {
        return SaveFilterByUseCase(filterByRepository = filterByRepository)
    }
}