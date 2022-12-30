package com.example.improvevocabulary.presentation.lists.pendingList

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.Language
import com.example.domain.model.OnStudyWordPair
import com.example.domain.model.PendingWordPair
import com.example.domain.usecase.languages.GetLanguageFromLearningUseCase
import com.example.domain.usecase.languages.GetLanguageOfLearningUseCase
import com.example.domain.usecase.onStudy.GetOnStudyMaxIdUseCase
import com.example.domain.usecase.onStudy.GetOnStudyWordPairCountUseCase
import com.example.domain.usecase.onStudy.RemoveOnStudyWordPairUseCase
import com.example.domain.usecase.onStudy.SaveOnStudyWordPairUseCase
import com.example.domain.usecase.pending.GetPendingMaxIdUseCase
import com.example.domain.usecase.pending.RemovePendingWordPairUseCase
import com.example.domain.usecase.pending.SavePendingWordPairUseCase
import com.example.domain.usecase.pending.UpdatePendingWordPairUseCase
import com.example.improvevocabulary.models.WordPair
import kotlinx.coroutines.launch


class PendingListViewModel(
    var updatePendingWordPairUseCase: UpdatePendingWordPairUseCase,
    var removePendingWordPairUseCase: RemovePendingWordPairUseCase,
    var savePendingWordPairUseCase: SavePendingWordPairUseCase,
    var saveOnStudyWordPairUseCase: SaveOnStudyWordPairUseCase,
    var removeOnStudyWordPairUseCase: RemoveOnStudyWordPairUseCase,
    val getOnStudyWordPairCountUseCase: GetOnStudyWordPairCountUseCase,
    val getLanguageFromLearningUseCase: GetLanguageFromLearningUseCase,
    val getLanguageOfLearningUseCase: GetLanguageOfLearningUseCase,
    val getPendingMaxIdUseCase: GetPendingMaxIdUseCase,
    val getOnStudyMaxIdUseCase: GetOnStudyMaxIdUseCase,
    val getStudMaxIdUseCase: GetPendingMaxIdUseCase,
) : ViewModel() {

    val languageFromLearning: MutableLiveData<Language> = MutableLiveData()
    val languageOfLearning: MutableLiveData<Language> = MutableLiveData()
    val onStudyCount: MutableLiveData<Int> = MutableLiveData()

    val maxWordId: MutableLiveData<Int> = MutableLiveData()

    init {
        viewModelScope.launch {
            onStudyCount.value = getOnStudyWordPairCountUseCase.execute()
        }
        languageFromLearning.value = getLanguageFromLearningUseCase.execute()
        languageOfLearning.value = getLanguageOfLearningUseCase.execute()
        generateNewId()
    }

    fun save(wordPair: WordPair) {
        viewModelScope.launch {
            savePendingWordPairUseCase.execute(mapToPending(wordPair))
        }
    }

    fun saveOnStudy(wordPair: WordPair) {
        viewModelScope.launch {
            saveOnStudyWordPairUseCase.execute(mapToOnStudy(wordPair))
        }
    }

    fun generateNewId() {
        var pendingMax = 0
        var onStudyMax = 0
        var studiedMax = 0
        var max = 0
        viewModelScope.launch {
            try {
                pendingMax = getPendingMaxIdUseCase.execute()
            }
            catch (e: Exception){
                Log.e("pendingMax", e.message.toString())
            }
            try {
                onStudyMax = getOnStudyMaxIdUseCase.execute()
            }
            catch (e: Exception){
                Log.e("onStudyMax", e.message.toString())
            }
            try {
                studiedMax = getStudMaxIdUseCase.execute()
            }
            catch (e: Exception){
                Log.e("studiedMax", e.message.toString())
            }
            max = Math.max(pendingMax, onStudyMax)
            max = Math.max(max, studiedMax)
            maxWordId.value = max + 1
        }
    }

    private fun mapToPending(dataModel: WordPair): PendingWordPair {
        return PendingWordPair(dataModel.id, dataModel.word, dataModel.translate)
    }

    private fun mapToOnStudy(dataModel: WordPair): OnStudyWordPair {
        return OnStudyWordPair(dataModel.id, dataModel.word, dataModel.translate)
    }
}