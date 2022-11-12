package com.example.improvevocabulary.presentation.lists.pendingList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.domain.usecase.languages.GetLanguageFromLearningUseCase
import com.example.domain.usecase.languages.GetLanguageOfLearningUseCase
import com.example.domain.usecase.onStudy.RemoveOnStudyWordPairUseCase
import com.example.domain.usecase.onStudy.SaveOnStudyWordPairUseCase
import com.example.domain.usecase.pending.RemovePendingWordPairUseCase
import com.example.domain.usecase.pending.SavePendingWordPairUseCase
import com.example.domain.usecase.pending.UpdatePendingWordPairUseCase

class PendingListViewModelFactory(
    val updatePendingWordPairUseCase: UpdatePendingWordPairUseCase,
    val removePendingWordPairUseCase: RemovePendingWordPairUseCase,
    val savePendingWordPairUseCase: SavePendingWordPairUseCase,
    val saveOnStudyWordPairUseCase: SaveOnStudyWordPairUseCase,
    val removeOnStudyWordPairUseCase: RemoveOnStudyWordPairUseCase,
    val getLanguageFromLearningUseCase: GetLanguageFromLearningUseCase,
    val getLanguageOfLearningUseCase: GetLanguageOfLearningUseCase,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return PendingListViewModel(
            updatePendingWordPairUseCase = updatePendingWordPairUseCase,
            removePendingWordPairUseCase = removePendingWordPairUseCase,
            savePendingWordPairUseCase = savePendingWordPairUseCase,
            saveOnStudyWordPairUseCase = saveOnStudyWordPairUseCase,
            removeOnStudyWordPairUseCase = removeOnStudyWordPairUseCase,
            getLanguageFromLearningUseCase = getLanguageFromLearningUseCase,
            getLanguageOfLearningUseCase = getLanguageOfLearningUseCase,
        ) as T
    }
}
