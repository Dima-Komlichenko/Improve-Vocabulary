package com.example.improvevocabulary.presentation.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.domain.usecase.GetLanguageUseCase
import com.example.domain.usecase.GetThemeUseCase
import com.example.domain.usecase.SaveLanguageUseCase
import com.example.domain.usecase.SaveThemeUseCase
import dagger.Provides
import javax.inject.Inject

//фабрика в которой создаются все зависимости, фабрику будем предоставлять через di (AppModule)
class SettingsViewModelFactory(
    val getLanguageUseCase: GetLanguageUseCase,
    val saveLanguageUseCase: SaveLanguageUseCase,
    val getThemeUseCase: GetThemeUseCase,
    val saveThemeUseCase: SaveThemeUseCase
) : ViewModelProvider.Factory {


    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SettingsViewModel(
            getLanguageUseCase = getLanguageUseCase,
            saveLanguageUseCase = saveLanguageUseCase,
            getThemeUseCase = getThemeUseCase,
            saveThemeUseCase = saveThemeUseCase
        ) as T
    }
}