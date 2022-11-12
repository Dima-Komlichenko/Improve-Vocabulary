package com.example.improvevocabulary.presentation.lists.studiedList

import androidx.lifecycle.ViewModel
import com.example.domain.usecase.onStudy.RemoveOnStudyWordPairUseCase
import com.example.domain.usecase.onStudy.SaveOnStudyWordPairUseCase
import com.example.domain.usecase.studied.RemoveStudiedWordPairUseCase
import com.example.domain.usecase.studied.SaveStudiedWordPairUseCase

class StudiedListViewModel(
    var removeStudiedWordPairUseCase: RemoveStudiedWordPairUseCase,
    var saveOnStudyWordPairUseCase: SaveOnStudyWordPairUseCase,
    var saveStudiedWordPairUseCase: SaveStudiedWordPairUseCase,
    var removeOnStudyWordPairUseCase: RemoveOnStudyWordPairUseCase
) : ViewModel()