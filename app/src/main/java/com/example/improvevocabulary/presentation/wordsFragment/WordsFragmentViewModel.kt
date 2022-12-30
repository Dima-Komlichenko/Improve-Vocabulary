package com.example.improvevocabulary.presentation.wordsFragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecase.onStudy.GetOnStudyWordPairCountUseCase
import com.example.domain.usecase.onStudy.IsOnStudyListContainsStudiedWordsUseCase
import com.example.domain.usecase.pending.GetPendingWordPairCountUseCase
import com.example.domain.usecase.studied.GetStudiedWordPairCountUseCase
import kotlinx.coroutines.launch

class WordsFragmentViewModel(
    private val getPendingWordPairCountUseCase: GetPendingWordPairCountUseCase,
    private val getOnStudyWordPairCountUseCase: GetOnStudyWordPairCountUseCase,
    private val getStudiedWordPairCountUseCase: GetStudiedWordPairCountUseCase,
    private val isOnStudyListContainsStudiedWordsUseCase: IsOnStudyListContainsStudiedWordsUseCase,
) : ViewModel() {

    var pendingCount: MutableLiveData<Int> = MutableLiveData()
    var onStudyCount: MutableLiveData<Int> = MutableLiveData()
    var studiedCount: MutableLiveData<Int> = MutableLiveData()
    var isOnStudyListContainsStudiedWords: MutableLiveData<Boolean> = MutableLiveData()

    fun init() {
        viewModelScope.launch {
            pendingCount.value = getPendingWordPairCountUseCase.execute()
            onStudyCount.value = getOnStudyWordPairCountUseCase.execute()
            studiedCount.value = getStudiedWordPairCountUseCase.execute()
            isOnStudyListContainsStudiedWords.value = isOnStudyListContainsStudiedWordsUseCase.execute()
        }
    }

    fun resetIsOnStudyListContainsStudiedWords() {
        viewModelScope.launch {
            isOnStudyListContainsStudiedWords.value = isOnStudyListContainsStudiedWordsUseCase.execute()
        }
    }
}

