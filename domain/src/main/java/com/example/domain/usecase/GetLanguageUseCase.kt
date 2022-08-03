package com.example.domain.usecase

import com.example.domain.models.Language
import com.example.domain.repositoriesI.LanguageRepository

class GetLanguageUseCase(private val languageRepository: LanguageRepository) {
    fun execute(): Language {
        return languageRepository.get()
    }
}