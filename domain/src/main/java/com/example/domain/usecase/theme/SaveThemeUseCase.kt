package com.example.domain.usecase.theme

import com.example.domain.model.Theme
import com.example.domain.repositoriesI.ThemeRepository

class SaveThemeUseCase(private val themeRepository: ThemeRepository) {
    fun execute(theme: Theme) {
        themeRepository.save(theme)
    }
}