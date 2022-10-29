package com.example.improvevocabulary.presentation.lists.baseList

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.storage.repositoriesImpl.WordPairRepository
import com.example.domain.model.OnStudyWordPair
import com.example.domain.model.PendingWordPair
import com.example.domain.model.StudiedWordPair
import com.example.domain.usecase.onStudy.GetOnStudyWordPairsUseCase
import com.example.domain.usecase.pending.GetPendingWordPairsUseCase
import com.example.domain.usecase.onStudy.SaveOnStudyWordPairUseCase
import com.example.domain.usecase.pending.SavePendingWordPairUseCase
import com.example.domain.usecase.studied.GetStudiedWordPairsUseCase
import com.example.domain.usecase.studied.SaveStudiedWordPairUseCase
import com.example.improvevocabulary.models.WordPair
import com.example.improvevocabulary.presentation.wordsFragment.WordListInfo
import kotlinx.coroutines.launch

open class WordListViewModel(var application: Application) : ViewModel() {

    var wordListInfo: MutableLiveData<WordListInfo> = MutableLiveData()
    var words = MutableLiveData<ArrayList<WordPair>>()

    private var getPendingWordPairsUseCase: GetPendingWordPairsUseCase? = null
    private var getOnStudyWordPairsUseCase: GetOnStudyWordPairsUseCase? = null
    private var getStudiedWordPairsUseCase: GetStudiedWordPairsUseCase? = null

    private var savePendingWordPairsUseCase: SavePendingWordPairUseCase? = null
    private var saveOnStudyWordPairsUseCase: SaveOnStudyWordPairUseCase? = null
    private var saveStudiedWordPairsUseCase: SaveStudiedWordPairUseCase? = null

    private var repository = WordPairRepository(application)

    fun init() {

        when (wordListInfo.value) {
            WordListInfo.Pending -> {
                getPendingWordPairsUseCase = GetPendingWordPairsUseCase(repository)
                savePendingWordPairsUseCase = SavePendingWordPairUseCase(repository)
                viewModelScope.launch {
                    var temp: ArrayList<WordPair> = arrayListOf()
                    getPendingWordPairsUseCase?.execute()?.forEach { temp.add(mapToWordPair(it)) }
                    words.value = temp
                }
            }

            WordListInfo.OnStudy -> {
                getOnStudyWordPairsUseCase = GetOnStudyWordPairsUseCase(repository)
                saveOnStudyWordPairsUseCase = SaveOnStudyWordPairUseCase(repository)
                viewModelScope.launch {
                    var temp: ArrayList<WordPair> = arrayListOf()
                     getOnStudyWordPairsUseCase?.execute()?.forEach { temp.add(mapToWordPair(it)) }
                    words.value = temp
                }
            }

            WordListInfo.Studied -> {
                getStudiedWordPairsUseCase = GetStudiedWordPairsUseCase(repository)
                saveStudiedWordPairsUseCase = SaveStudiedWordPairUseCase(repository)
                viewModelScope.launch {
                    var temp: ArrayList<WordPair> = arrayListOf()
                    getStudiedWordPairsUseCase?.execute()?.forEach { temp.add(mapToWordPair(it)) }
                    words.value = temp
                }
            }
            else -> {}
        }
        //dataList = repository.dataList

    }

    fun save(wordPair: WordPair) {
        savePendingWordPairsUseCase?.execute(mapToPending(wordPair))
        saveOnStudyWordPairsUseCase?.execute(mapToOnStudy(wordPair))

        //saveStudiedWordPairsUseCase?.execute(mapToStudiedDomain(wordPair))
    }

    //private fun mapToPendingDomain(dataModel: WordPair): PendingWordPair {
    //    return PendingWordPair(dataModel.id, dataModel.word, dataModel.translate)
    //}

    private fun mapToOnStudy(dataModel: WordPair): OnStudyWordPair {
        return OnStudyWordPair(dataModel.id, dataModel.word, dataModel.translate)
    }

    private fun mapToPending(dataModel: WordPair): PendingWordPair {
        return PendingWordPair(dataModel.id, dataModel.word, dataModel.translate)
    }

    private fun mapToStudied(dataModel: WordPair): StudiedWordPair {
        return StudiedWordPair(dataModel.id, dataModel.word, dataModel.translate)
    }

    private fun mapToWordPair(dataModel: OnStudyWordPair): WordPair {
        return WordPair(
            dataModel.id,
            dataModel.word,
            dataModel.translate,
            dataModel.countRightAnswers
        )
    }

    private fun mapToWordPair(dataModel: PendingWordPair): WordPair {
        return WordPair(
            dataModel.id,
            dataModel.word,
            dataModel.translate
        )
    }

    private fun mapToWordPair(dataModel: StudiedWordPair): WordPair {
        return WordPair(
            dataModel.id,
            dataModel.word,
            dataModel.translate
        )
    }





}