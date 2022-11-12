package com.example.improvevocabulary.presentation.tests

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecase.onStudy.GetOnStudyWordPairCountUseCase
import com.example.domain.usecase.studied.GetStudiedWordPairCountUseCase
import kotlinx.coroutines.launch

class TestsViewModel(
    private val getOnStudyWordPairCountUseCase: GetOnStudyWordPairCountUseCase,
    private val getStudiedWordPairCountUseCase: GetStudiedWordPairCountUseCase,
) : ViewModel() {
    var onStudyCount: MutableLiveData<Int> = MutableLiveData()
    var studiedCount: MutableLiveData<Int> = MutableLiveData()

    fun init() {
        viewModelScope.launch {
            onStudyCount.value = getOnStudyWordPairCountUseCase.execute()
            studiedCount.value = getStudiedWordPairCountUseCase.execute()
        }
    }

}