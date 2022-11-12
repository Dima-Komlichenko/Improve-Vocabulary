package com.example.domain.repositoriesI

import com.example.domain.model.Language

interface LanguageFromLearningRepository {
    fun getLanguage(): Language
    fun saveLanguage(language: Language): Boolean
}
