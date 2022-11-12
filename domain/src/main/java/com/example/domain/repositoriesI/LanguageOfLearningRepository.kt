package com.example.domain.repositoriesI

import com.example.domain.model.Language

interface LanguageOfLearningRepository {
    fun getLanguage(): Language
    fun saveLanguage(language: Language): Boolean
}
