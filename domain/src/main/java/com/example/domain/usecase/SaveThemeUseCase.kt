package com.example.domain.usecase

import com.example.domain.models.Theme
import com.example.domain.repositoriesI.ThemeRepository

class SaveThemeUseCase(private val themeRepository: ThemeRepository) {
    fun execute(theme: Theme) {
        themeRepository.save(theme)
    }
}