package com.example.data.storage.interfaces

import com.example.domain.utils.Language

interface LanguageOfLearning {
    fun save(data: Language): Boolean
    fun get(): Language
}