package com.example.domain.usecase.languages

import com.example.domain.model.Language
import com.example.domain.repositoriesI.AppLanguageRepository
import com.example.domain.repositoriesI.LanguageOfLearningRepository

class SaveLanguageOfLearningUseCase(private val languageRepository: LanguageOfLearningRepository) {
    fun execute(language: Language): Boolean {
        return languageRepository.saveLanguage(language)
    }
}