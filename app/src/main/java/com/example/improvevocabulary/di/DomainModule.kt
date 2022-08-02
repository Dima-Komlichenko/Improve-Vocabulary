package com.example.improvevocabulary.di

import com.example.domain.repositoriesI.LanguageRepository
import com.example.domain.usecase.GetLanguageUseCase
import com.example.domain.usecase.SaveLanguageUseCase
import dagger.Module
import dagger.Provides

@Module
class DomainModule {

    @Provides
    fun provideGetDataUseCase(languageRepository: LanguageRepository): GetLanguageUseCase{
        return GetLanguageUseCase(languageRepository = languageRepository)
    }

    @Provides
    fun provideSaveDataUseCase(languageRepository: LanguageRepository): SaveLanguageUseCase{
        return SaveLanguageUseCase(languageRepository = languageRepository)
    }

}