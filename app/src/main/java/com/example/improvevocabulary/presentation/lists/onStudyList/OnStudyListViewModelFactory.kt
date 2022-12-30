package com.example.improvevocabulary.presentation.lists.onStudyList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.domain.usecase.languages.GetLanguageFromLearningUseCase
import com.example.domain.usecase.languages.GetLanguageOfLearningUseCase
import com.example.domain.usecase.onStudy.GetOnStudyMaxIdUseCase
import com.example.domain.usecase.onStudy.RemoveOnStudyWordPairUseCase
import com.example.domain.usecase.onStudy.SaveOnStudyWordPairUseCase
import com.example.domain.usecase.onStudy.UpdateOnStudyWordPairUseCase
import com.example.domain.usecase.pending.GetPendingMaxIdUseCase
import com.example.domain.usecase.studied.RemoveStudiedWordPairUseCase
import com.example.domain.usecase.studied.SaveStudiedWordPairUseCase

class OnStudyListViewModelFactory(
    val updateOnStudyWordPairUseCase: UpdateOnStudyWordPairUseCase,
    val removeOnStudyWordPairUseCase: RemoveOnStudyWordPairUseCase,
    val saveOnStudyWordPairUseCase: SaveOnStudyWordPairUseCase,
    val saveStudiedWordPairUseCase: SaveStudiedWordPairUseCase,
    val removeStudiedWordPairUseCase: RemoveStudiedWordPairUseCase,
    val getLanguageFromLearningUseCase: GetLanguageFromLearningUseCase,
    val getLanguageOfLearningUseCase: GetLanguageOfLearningUseCase,
    val getPendingMaxIdUseCase: GetPendingMaxIdUseCase,
    val getOnStudyMaxIdUseCase: GetOnStudyMaxIdUseCase,
    val getStudMaxIdUseCase: GetPendingMaxIdUseCase,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return OnStudyListViewModel(
            updateOnStudyWordPairUseCase = updateOnStudyWordPairUseCase,
            removeOnStudyWordPairUseCase = removeOnStudyWordPairUseCase,
            saveOnStudyWordPairUseCase = saveOnStudyWordPairUseCase,
            saveStudiedWordPairUseCase = saveStudiedWordPairUseCase,
            removeStudiedWordPairUseCase = removeStudiedWordPairUseCase,
            getLanguageFromLearningUseCase = getLanguageFromLearningUseCase,
            getLanguageOfLearningUseCase = getLanguageOfLearningUseCase,
            getPendingMaxIdUseCase = getPendingMaxIdUseCase,
            getOnStudyMaxIdUseCase = getOnStudyMaxIdUseCase,
            getStudMaxIdUseCase = getStudMaxIdUseCase
        ) as T
    }
}