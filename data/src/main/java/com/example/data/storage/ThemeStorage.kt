package com.example.data.storage

interface ThemeStorage {
    fun save(data: Theme): Boolean
    fun get(): Theme
}
