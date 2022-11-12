package com.example.improvevocabulary.presentation.lists.pendingList

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.model.Language
import com.example.domain.model.OnStudyWordPair
import com.example.domain.model.PendingWordPair
import com.example.domain.usecase.languages.GetLanguageFromLearningUseCase
import com.example.domain.usecase.languages.GetLanguageOfLearningUseCase
import com.example.domain.usecase.onStudy.RemoveOnStudyWordPairUseCase
import com.example.domain.usecase.onStudy.SaveOnStudyWordPairUseCase
import com.example.domain.usecase.onStudy.UpdateOnStudyWordPairUseCase
import com.example.domain.usecase.pending.RemovePendingWordPairUseCase
import com.example.domain.usecase.pending.SavePendingWordPairUseCase
import com.example.domain.usecase.pending.UpdatePendingWordPairUseCase
import com.example.domain.usecase.studied.RemoveStudiedWordPairUseCase
import com.example.domain.usecase.studied.SaveStudiedWordPairUseCase
import com.example.improvevocabulary.models.WordPair


class PendingListViewModel(
    var updatePendingWordPairUseCase: UpdatePendingWordPairUseCase,
    var removePendingWordPairUseCase: RemovePendingWordPairUseCase,
    var savePendingWordPairUseCase: SavePendingWordPairUseCase,
    var saveOnStudyWordPairUseCase: SaveOnStudyWordPairUseCase,
    var removeOnStudyWordPairUseCase: RemoveOnStudyWordPairUseCase,
    val getLanguageFromLearningUseCase: GetLanguageFromLearningUseCase,
    val getLanguageOfLearningUseCase: GetLanguageOfLearningUseCase
) : ViewModel() {

    val languageFromLearning: MutableLiveData<Language> = MutableLiveData()
    val languageOfLearning: MutableLiveData<Language> = MutableLiveData()

    init {
        languageFromLearning.value = getLanguageFromLearningUseCase.execute()
        languageOfLearning.value = getLanguageOfLearningUseCase.execute()
    }

    fun save(wordPair: WordPair) {
        savePendingWordPairUseCase.execute(mapToPending(wordPair))
    }

    private fun mapToPending(dataModel: WordPair): PendingWordPair {
        return PendingWordPair(dataModel.id, dataModel.word, dataModel.translate)
    }
}