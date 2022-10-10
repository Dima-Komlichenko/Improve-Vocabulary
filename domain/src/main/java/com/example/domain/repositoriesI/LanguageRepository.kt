package com.example.domain.repositoriesI

import com.example.domain.model.Language

interface LanguageRepository {
    fun save(language: Language): Boolean
    fun get(): Language
}
