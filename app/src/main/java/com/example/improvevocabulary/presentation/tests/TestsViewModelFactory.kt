package com.example.improvevocabulary.presentation.tests

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.domain.usecase.onStudy.GetOnStudyWordPairCountUseCase

class TestsViewModelFactory(
    private val getOnStudyWordPairCountUseCase: GetOnStudyWordPairCountUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return TestsViewModel(
            getOnStudyWordPairCountUseCase = getOnStudyWordPairCountUseCase
        ) as T
    }
}
