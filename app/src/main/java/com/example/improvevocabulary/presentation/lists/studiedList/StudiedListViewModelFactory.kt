package com.example.improvevocabulary.presentation.lists.studiedList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.domain.usecase.onStudy.GetOnStudyMaxIdUseCase
import com.example.domain.usecase.onStudy.GetOnStudyWordPairCountUseCase
import com.example.domain.usecase.onStudy.RemoveOnStudyWordPairUseCase
import com.example.domain.usecase.onStudy.SaveOnStudyWordPairUseCase
import com.example.domain.usecase.pending.GetPendingMaxIdUseCase
import com.example.domain.usecase.studied.RemoveStudiedWordPairUseCase
import com.example.domain.usecase.studied.SaveStudiedWordPairUseCase

class StudiedListViewModelFactory(
    val removeStudiedWordPairUseCase: RemoveStudiedWordPairUseCase,
    val saveOnStudyWordPairUseCase: SaveOnStudyWordPairUseCase,
    val saveStudiedWordPairUseCase: SaveStudiedWordPairUseCase,
    val removeOnStudyWordPairUseCase: RemoveOnStudyWordPairUseCase,
    val getOnStudyWordPairCountUseCase: GetOnStudyWordPairCountUseCase,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return StudiedListViewModel(
            removeStudiedWordPairUseCase = removeStudiedWordPairUseCase,
            saveOnStudyWordPairUseCase = saveOnStudyWordPairUseCase,
            saveStudiedWordPairUseCase = saveStudiedWordPairUseCase,
            getOnStudyWordPairCountUseCase = getOnStudyWordPairCountUseCase,
            removeOnStudyWordPairUseCase = removeOnStudyWordPairUseCase,
        ) as T
    }
}
