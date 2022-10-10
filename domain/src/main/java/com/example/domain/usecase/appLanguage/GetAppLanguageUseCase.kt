package com.example.domain.usecase.appLanguage

import com.example.domain.model.Language
import com.example.domain.repositoriesI.LanguageRepository

class GetAppLanguageUseCase(private val languageRepository: LanguageRepository) {
    fun execute(): Language {
        return languageRepository.get()
    }
}