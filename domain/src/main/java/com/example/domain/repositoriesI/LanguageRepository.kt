package com.example.domain.repositoriesI

import com.example.domain.model.Language

interface LanguageRepository {
    fun getAppLanguage(): Language
    fun getLanguageFrom(): Language
    fun getLanguageTo(): Language

    fun saveAppLanguage(language: Language): Boolean
    fun saveLanguageFrom(language: Language): Boolean
    fun saveLanguageTo(language: Language): Boolean
}
