package com.example.domain.usecase.languages

import com.example.domain.model.Language
import com.example.domain.repositoriesI.LanguageRepository

class GetLanguageFromUseCase(private val languageRepository: LanguageRepository) {
    fun execute(): Language {
        return languageRepository.getAppLanguage()
    }
}