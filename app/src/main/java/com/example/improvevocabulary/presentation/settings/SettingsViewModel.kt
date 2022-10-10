package com.example.improvevocabulary.presentation.settings

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.model.Language
import com.example.domain.model.Theme
import com.example.domain.usecase.appLanguage.GetAppLanguageUseCase
import com.example.domain.usecase.theme.GetThemeUseCase
import com.example.domain.usecase.appLanguage.SaveAppLanguageUseCase
import com.example.domain.usecase.theme.SaveThemeUseCase

class SettingsViewModel(
    private val getAppLanguageUseCase: GetAppLanguageUseCase,
    private val saveAppLanguageUseCase: SaveAppLanguageUseCase,
    private val getThemeUseCase: GetThemeUseCase,
    private val saveThemeUseCase: SaveThemeUseCase,
) : ViewModel() {
    val language = MutableLiveData<Language>()
    val theme = MutableLiveData<Theme>()


    init {
        language.value = getAppLanguageUseCase.execute()
        theme.value = getThemeUseCase.execute()
    }


    fun saveTheme(theme: Theme) {
        saveThemeUseCase.execute(theme)
    }

    fun saveLanguage(language: Language) {
        saveAppLanguageUseCase.execute(language)
    }
}