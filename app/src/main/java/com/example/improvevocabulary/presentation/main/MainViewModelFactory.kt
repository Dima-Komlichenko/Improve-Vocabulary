package com.example.improvevocabulary.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.domain.usecase.appLanguage.GetAppLanguageUseCase
import com.example.domain.usecase.languages.GetLanguageFromLearningUseCase
import com.example.domain.usecase.languages.GetLanguageOfLearningUseCase
import com.example.domain.usecase.languages.SaveLanguageFromLearningUseCase
import com.example.domain.usecase.languages.SaveLanguageOfLearningUseCase
import com.example.domain.usecase.studied.GetStudiedWordPairCountUseCase

class MainViewModelFactory(
    private val getLanguageFromLearningUseCase: GetLanguageFromLearningUseCase,
    private val getLanguageOfLearningUseCase: GetLanguageOfLearningUseCase,
    private val getAppLanguageUseCase: GetAppLanguageUseCase,
    private val saveLanguageFromLearningUseCase: SaveLanguageFromLearningUseCase,
    private val saveLanguageOfLearningUseCase: SaveLanguageOfLearningUseCase,
    private val getStudiedWordPairCountUseCase: GetStudiedWordPairCountUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(
            getLanguageFromLearningUseCase = getLanguageFromLearningUseCase,
            getLanguageOfLearningUseCase = getLanguageOfLearningUseCase,
            getAppLanguageUseCase = getAppLanguageUseCase,
            saveLanguageFromLearningUseCase = saveLanguageFromLearningUseCase,
            saveLanguageOfLearningUseCase = saveLanguageOfLearningUseCase,
            getStudiedWordPairCountUseCase = getStudiedWordPairCountUseCase,
        ) as T
    }
}