package com.example.improvevocabulary.presentation.tests

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecase.onStudy.GetOnStudyWordPairCountUseCase
import kotlinx.coroutines.launch

class TestsViewModel(
    val getOnStudyWordPairCountUseCase: GetOnStudyWordPairCountUseCase,
) : ViewModel() {
    var onStudyCount: MutableLiveData<Int> = MutableLiveData()

    fun init() {
        viewModelScope.launch {
            onStudyCount.value = getOnStudyWordPairCountUseCase.execute()
        }
    }
}