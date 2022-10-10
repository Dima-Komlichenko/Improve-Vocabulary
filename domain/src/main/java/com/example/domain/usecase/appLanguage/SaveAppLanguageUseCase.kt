package com.example.domain.usecase.appLanguage

import com.example.domain.model.Language
import com.example.domain.repositoriesI.LanguageRepository

class SaveAppLanguageUseCase(private val languageRepository: LanguageRepository) {
    fun execute(language: Language) {
        languageRepository.save(language)
    }
}