package com.example.domain.usecase

import com.example.domain.models.Language
import com.example.domain.repositoriesI.LanguageRepository

class SaveLanguageUseCase(private val languageRepository: LanguageRepository) {
    fun execute(language: Language) {
        languageRepository.save(language)
    }
}