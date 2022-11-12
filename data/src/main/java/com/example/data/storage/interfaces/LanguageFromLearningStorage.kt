package com.example.data.storage.interfaces

import com.example.data.storage.models.Language

interface LanguageFromLearningStorage {
    fun save(data: Language): Boolean
    fun get(): Language
}