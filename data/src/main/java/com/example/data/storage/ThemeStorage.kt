package com.example.data.storage

import com.example.data.storage.models.Theme

interface ThemeStorage {
    fun save(data: Theme): Boolean
    fun get(): Theme
}
