package com.example.domain.usecase.languages

import com.example.domain.model.Language
import com.example.domain.repositoriesI.LanguageOfLearningRepository

class GetLanguageOfLearningUseCase(private val languageRepository: LanguageOfLearningRepository) {
    fun execute(): Language {
        return languageRepository.getLanguage()
    }
}