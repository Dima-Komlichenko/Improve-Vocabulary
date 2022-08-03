package com.example.domain.repositoriesI

import com.example.domain.models.Theme

interface ThemeRepository {
    fun save(theme: Theme): Boolean
    fun get(): Theme
}