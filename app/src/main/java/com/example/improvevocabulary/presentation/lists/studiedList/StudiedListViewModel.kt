package com.example.improvevocabulary.presentation.lists.studiedList

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.OnStudyWordPair
import com.example.domain.usecase.onStudy.GetOnStudyMaxIdUseCase
import com.example.domain.usecase.onStudy.GetOnStudyWordPairCountUseCase
import com.example.domain.usecase.onStudy.RemoveOnStudyWordPairUseCase
import com.example.domain.usecase.onStudy.SaveOnStudyWordPairUseCase
import com.example.domain.usecase.pending.GetPendingMaxIdUseCase
import com.example.domain.usecase.studied.RemoveStudiedWordPairUseCase
import com.example.domain.usecase.studied.SaveStudiedWordPairUseCase
import com.example.improvevocabulary.models.WordPair
import kotlinx.coroutines.launch

class StudiedListViewModel(
    val removeStudiedWordPairUseCase: RemoveStudiedWordPairUseCase,
    val saveOnStudyWordPairUseCase: SaveOnStudyWordPairUseCase,
    val saveStudiedWordPairUseCase: SaveStudiedWordPairUseCase,
    val removeOnStudyWordPairUseCase: RemoveOnStudyWordPairUseCase,
    val getOnStudyWordPairCountUseCase: GetOnStudyWordPairCountUseCase,
) : ViewModel() {
    val onStudyCount: MutableLiveData<Int> = MutableLiveData()

    init {
        viewModelScope.launch {
            onStudyCount.value = getOnStudyWordPairCountUseCase.execute()
        }
    }

    fun saveOnStudy(wordPair: WordPair) {
        viewModelScope.launch {
            saveOnStudyWordPairUseCase.execute(mapToOnStudy(wordPair))
        }
    }

    private fun mapToOnStudy(dataModel: WordPair): OnStudyWordPair {
        return OnStudyWordPair(dataModel.id, dataModel.word, dataModel.translate)
    }
}
