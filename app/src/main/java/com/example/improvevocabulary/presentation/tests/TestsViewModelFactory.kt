package com.example.improvevocabulary.presentation.tests

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.domain.usecase.onStudy.GetOnStudyWordPairCountUseCase
import com.example.domain.usecase.studied.GetStudiedWordPairCountUseCase

class TestsViewModelFactory(
    private val getOnStudyWordPairCountUseCase: GetOnStudyWordPairCountUseCase,
    private val getStudiedWordPairCountUseCase: GetStudiedWordPairCountUseCase,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return TestsViewModel(
            getOnStudyWordPairCountUseCase = getOnStudyWordPairCountUseCase,
            getStudiedWordPairCountUseCase = getStudiedWordPairCountUseCase,
        ) as T
    }
}
