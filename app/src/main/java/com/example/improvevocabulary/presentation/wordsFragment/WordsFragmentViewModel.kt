package com.example.improvevocabulary.presentation.wordsFragment

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecase.onStudy.GetOnStudyWordPairCountUseCase
import com.example.domain.usecase.pending.GetPendingWordPairCountUseCase
import com.example.domain.usecase.studied.GetStudiedWordPairCountUseCase
import com.example.domain.usecase.theme.GetThemeUseCase
import kotlinx.coroutines.launch

class WordsFragmentViewModel(
    private val getPendingWordPairCountUseCase: GetPendingWordPairCountUseCase,
    private val getOnStudyWordPairCountUseCase: GetOnStudyWordPairCountUseCase,
    private val getStudiedWordPairCountUseCase: GetStudiedWordPairCountUseCase,
) : ViewModel() {

    var pendingCount: MutableLiveData<Int> = MutableLiveData()
    var onStudyCount: MutableLiveData<Int> = MutableLiveData()
    var studiedCount: MutableLiveData<Int> = MutableLiveData()

    fun init() {
        viewModelScope.launch {
            pendingCount.value = getPendingWordPairCountUseCase.execute()
            onStudyCount.value = getOnStudyWordPairCountUseCase.execute()
            studiedCount.value = getStudiedWordPairCountUseCase.execute()
        }
    }
}

