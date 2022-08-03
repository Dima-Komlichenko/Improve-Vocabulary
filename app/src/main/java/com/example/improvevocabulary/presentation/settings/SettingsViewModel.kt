package com.example.improvevocabulary.presentation.settings

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.models.Language
import com.example.domain.models.Theme
import com.example.domain.usecase.GetLanguageUseCase
import com.example.domain.usecase.GetThemeUseCase
import com.example.domain.usecase.SaveLanguageUseCase
import com.example.domain.usecase.SaveThemeUseCase

class SettingsViewModel(
    private val getLanguageUseCase: GetLanguageUseCase,
    private val saveLanguageUseCase: SaveLanguageUseCase,
    private val getThemeUseCase: GetThemeUseCase,
    private val saveThemeUseCase: SaveThemeUseCase
) : ViewModel() {
    val language = MutableLiveData<Language>()
    val theme = MutableLiveData<Theme>()

    fun saveTheme(theme: Theme) {
        saveThemeUseCase.execute(theme)
    }

    fun saveLanguage(language: Language) {
        saveLanguageUseCase.execute(language)
    }

    fun load() {
        language.value = getLanguageUseCase.execute()
        theme.value = getThemeUseCase.execute()
    }

}