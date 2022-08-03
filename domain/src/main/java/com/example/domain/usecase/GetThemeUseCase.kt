package com.example.domain.usecase

import com.example.domain.models.Theme
import com.example.domain.repositoriesI.ThemeRepository

class GetThemeUseCase(private val themeRepository: ThemeRepository) {
    fun execute(): Theme {
        return themeRepository.get()
    }
}