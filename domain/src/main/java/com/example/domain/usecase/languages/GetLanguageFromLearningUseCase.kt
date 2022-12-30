package com.example.domain.usecase.languages

import com.example.domain.model.Language
import com.example.domain.repositoriesI.LanguageFromLearningRepository

class GetLanguageFromLearningUseCase(private val languageRepository: LanguageFromLearningRepository) {
    fun execute(): Language {
        return languageRepository.getLanguage()
    }
}