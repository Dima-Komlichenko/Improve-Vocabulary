package com.example.improvevocabulary.presentation.wordsFragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.domain.usecase.onStudy.GetOnStudyWordPairCountUseCase
import com.example.domain.usecase.pending.GetPendingWordPairCountUseCase
import com.example.domain.usecase.studied.GetStudiedWordPairCountUseCase

class WordsFragmentViewModelFactory(
    val getPendingWordPairCountUseCase: GetPendingWordPairCountUseCase,
    val getOnStudyWordPairCountUseCase: GetOnStudyWordPairCountUseCase,
    val getStudiedWordPairCountUseCase: GetStudiedWordPairCountUseCase,
)  : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return WordsFragmentViewModel(
            getPendingWordPairCountUseCase = getPendingWordPairCountUseCase,
            getOnStudyWordPairCountUseCase = getOnStudyWordPairCountUseCase,
            getStudiedWordPairCountUseCase = getStudiedWordPairCountUseCase
        ) as T
    }
}
