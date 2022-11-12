package com.example.improvevocabulary.presentation.add

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.domain.usecase.languages.GetLanguageFromLearningUseCase
import com.example.domain.usecase.languages.GetLanguageOfLearningUseCase
import com.example.domain.usecase.onStudy.GetOnStudyWordPairCountUseCase

class AddViewModelFactory(
    private val getOnStudyWordPairCountUseCase: GetOnStudyWordPairCountUseCase,
    private val getLanguageFromLearningUseCase: GetLanguageFromLearningUseCase,
    private val getLanguageOfLearningUseCase: GetLanguageOfLearningUseCase,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AddViewModel(
            getOnStudyWordPairCountUseCase = getOnStudyWordPairCountUseCase,
            getLanguageFromLearningUseCase = getLanguageFromLearningUseCase,
            getLanguageOfLearningUseCase = getLanguageOfLearningUseCase
            ) as T
    }
}