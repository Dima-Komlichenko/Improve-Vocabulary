package com.example.improvevocabulary.di

import com.example.domain.repositoriesI.LanguageRepository
import com.example.domain.usecase.GetLanguageUseCase
import com.example.domain.usecase.SaveLanguageUseCase
import dagger.Module

@Module
class DomainModule {

    fun provideGetDataUseCase(languageRepository: LanguageRepository): GetLanguageUseCase{
        return GetLanguageUseCase(languageRepository = languageRepository)
    }

    fun provideSaveDataUseCase(languageRepository: LanguageRepository): SaveLanguageUseCase{
        return SaveLanguageUseCase(languageRepository = languageRepository)
    }

}