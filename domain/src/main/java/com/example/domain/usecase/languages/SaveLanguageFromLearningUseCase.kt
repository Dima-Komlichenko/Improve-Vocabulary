package com.example.domain.usecase.languages

import com.example.domain.model.Language
import com.example.domain.repositoriesI.LanguageFromLearningRepository

class SaveLanguageFromLearningUseCase(private val languageRepository: LanguageFromLearningRepository) {
    fun execute(language: Language): Boolean {
        return languageRepository.saveLanguage(language)
    }
}