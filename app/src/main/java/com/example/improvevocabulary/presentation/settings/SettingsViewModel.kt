package com.example.improvevocabulary.presentation.settings

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.models.Language
import com.example.domain.usecase.GetLanguageUseCase
import com.example.domain.usecase.SaveLanguageUseCase

class SettingsViewModel(private val getLanguageUseCase: GetLanguageUseCase, private val saveLanguageUseCase: SaveLanguageUseCase) : ViewModel() {
    val language = MutableLiveData<String>()

    fun saveLanguage(language: String) {
        val lang = Language(language)
        saveLanguageUseCase.execute(lang)
    }

    fun load() {
        language.value = getLanguageUseCase.execute().language
    }
}