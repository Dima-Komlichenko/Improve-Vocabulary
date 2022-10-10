package com.example.improvevocabulary.presentation.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.domain.usecase.appLanguage.GetAppLanguageUseCase
import com.example.domain.usecase.theme.GetThemeUseCase
import com.example.domain.usecase.appLanguage.SaveAppLanguageUseCase
import com.example.domain.usecase.theme.SaveThemeUseCase

//фабрика в которой создаются все зависимости, фабрику будем предоставлять через di (AppModule)
class SettingsViewModelFactory(
    val getAppLanguageUseCase: GetAppLanguageUseCase,
    val saveAppLanguageUseCase: SaveAppLanguageUseCase,
    val getThemeUseCase: GetThemeUseCase,
    val saveThemeUseCase: SaveThemeUseCase
) : ViewModelProvider.Factory {


    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SettingsViewModel(
            getAppLanguageUseCase = getAppLanguageUseCase,
            saveAppLanguageUseCase = saveAppLanguageUseCase,
            getThemeUseCase = getThemeUseCase,
            saveThemeUseCase = saveThemeUseCase
        ) as T
    }
}