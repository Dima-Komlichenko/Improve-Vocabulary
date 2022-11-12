package com.example.domain.usecase.appLanguage

import com.example.domain.model.Language
import com.example.domain.repositoriesI.AppLanguageRepository

class SaveAppLanguageUseCase(private val languageRepository: AppLanguageRepository) {
    fun execute(language: Language) {
        languageRepository.saveLanguage(language)
    }
}