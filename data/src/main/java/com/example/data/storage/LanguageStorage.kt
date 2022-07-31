package com.example.data.storage


interface LanguageStorage {
    fun save(data: Language): Boolean
    fun get(): Language
}