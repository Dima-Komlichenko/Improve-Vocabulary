package com.example.data.storage

import com.example.data.storage.models.Language


interface LanguageStorage {
    fun save(data: Language): Boolean
    fun get(): Language
}