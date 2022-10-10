package com.example.domain.usecase.theme

import com.example.domain.model.Theme
import com.example.domain.repositoriesI.ThemeRepository

class GetThemeUseCase(private val themeRepository: ThemeRepository) {
    fun execute(): Theme {
        return themeRepository.get()
    }
}