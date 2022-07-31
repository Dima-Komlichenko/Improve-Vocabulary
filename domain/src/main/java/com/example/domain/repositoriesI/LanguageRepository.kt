package com.example.domain.repositoriesI

import com.example.domain.models.Language

interface LanguageRepository {
    fun save(language: Language): Boolean
    fun get(): Language
}

//repository implementation has com.example.domain.models.Language