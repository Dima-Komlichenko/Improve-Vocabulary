package com.example.improvevocabulary.di

import com.example.domain.repositoriesI.FilterByRepository
import com.example.domain.repositoriesI.LanguageRepository
import com.example.domain.repositoriesI.ThemeRepository
import com.example.domain.usecase.filter.GetFilterByUseCase
import com.example.domain.usecase.filter.SaveFilterByUseCase
import com.example.domain.usecase.appLanguage.GetAppLanguageUseCase
import com.example.domain.usecase.appLanguage.SaveAppLanguageUseCase
import com.example.domain.usecase.theme.GetThemeUseCase
import com.example.domain.usecase.theme.SaveThemeUseCase
import dagger.Module
import dagger.Provides

@Module
class DomainModule {

    @Provides
    fun provideGetLanguageUseCase(languageRepository: LanguageRepository): GetAppLanguageUseCase {
        return GetAppLanguageUseCase(languageRepository = languageRepository)
    }

    @Provides
    fun provideSaveLanguageUseCase(languageRepository: LanguageRepository): SaveAppLanguageUseCase {
        return SaveAppLanguageUseCase(languageRepository = languageRepository)
    }

    @Provides
    fun provideGetThemeUseCase(themeRepository: ThemeRepository): GetThemeUseCase {
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