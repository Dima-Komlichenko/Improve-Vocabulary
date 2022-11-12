package com.example.improvevocabulary.presentation.lists.studiedList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.domain.usecase.onStudy.RemoveOnStudyWordPairUseCase
import com.example.domain.usecase.onStudy.SaveOnStudyWordPairUseCase
import com.example.domain.usecase.studied.RemoveStudiedWordPairUseCase
import com.example.domain.usecase.studied.SaveStudiedWordPairUseCase

class StudiedListViewModelFactory(
    var removeStudiedWordPairUseCase: RemoveStudiedWordPairUseCase,
    var saveOnStudyWordPairUseCase: SaveOnStudyWordPairUseCase,
    var saveStudiedWordPairUseCase: SaveStudiedWordPairUseCase,
    var removeOnStudyWordPairUseCase: RemoveOnStudyWordPairUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return StudiedListViewModel(
            removeStudiedWordPairUseCase = removeStudiedWordPairUseCase,
            saveOnStudyWordPairUseCase = saveOnStudyWordPairUseCase,
            saveStudiedWordPairUseCase = saveStudiedWordPairUseCase,
            removeOnStudyWordPairUseCase = removeOnStudyWordPairUseCase
        ) as T
    }
}
