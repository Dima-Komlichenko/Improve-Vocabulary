package com.example.improvevocabulary.presentation.add

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.domain.usecase.onStudy.GetOnStudyWordPairCountUseCase

class AddViewModelFactory(
    private val getOnStudyWordPairCountUseCase: GetOnStudyWordPairCountUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AddViewModel(
            getOnStudyWordPairCountUseCase = getOnStudyWordPairCountUseCase
        ) as T
    }
}