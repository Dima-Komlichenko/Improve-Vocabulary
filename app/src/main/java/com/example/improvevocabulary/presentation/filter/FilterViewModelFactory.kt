package com.example.improvevocabulary.presentation.filter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.domain.usecase.*
import com.example.improvevocabulary.presentation.settings.SettingsViewModel

class FilterViewModelFactory (
    private val getFilterByUseCase: GetFilterByUseCase,
    private val saveFilterByUseCase: SaveFilterByUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return FilterViewModel(
            getFilterByUseCase = getFilterByUseCase,
            saveFilterByUseCase = saveFilterByUseCase
        ) as T
    }
}