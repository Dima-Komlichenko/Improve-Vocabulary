package com.example.domain.repositoriesI

import com.example.domain.model.Theme

interface ThemeRepository {
    fun save(theme: Theme): Boolean
    fun get(): Theme
}