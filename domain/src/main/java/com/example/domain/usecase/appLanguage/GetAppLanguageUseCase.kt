package com.example.domain.usecase.appLanguage

import com.example.domain.model.Language
import com.example.domain.repositoriesI.AppLanguageRepository

class GetAppLanguageUseCase(private val languageRepository: AppLanguageRepository) {
    fun execute(): Language {
        return languageRepository.getLanguage()
    }
}