package com.example.improvevocabulary.presentation.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.domain.usecase.GetLanguageUseCase
import com.example.domain.usecase.SaveLanguageUseCase

//фабрика в которой создаются все зависимости, фабрику будем предоставлять через di (AppModule)
class SettingsViewModelFactory(
    val getLanguageUseCase: GetLanguageUseCase,
    val saveLanguageUseCase: SaveLanguageUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SettingsViewModel(
            getLanguageUseCase = getLanguageUseCase,
            saveLanguageUseCase = saveLanguageUseCase
        ) as T
    }
}