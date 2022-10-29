package com.example.improvevocabulary.presentation.add

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecase.onStudy.GetOnStudyWordPairCountUseCase
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch

class AddViewModel(
    val getOnStudyWordPairCountUseCase: GetOnStudyWordPairCountUseCase,
) : ViewModel() {

    var onStudyCount: MutableLiveData<Int> =
        MutableLiveData() // property type is optional since it can be inferred from the getter's return type


    val firstFieldText = MutableLiveData<String>()
    val secondFieldText = MutableLiveData<String>()
    val clickBtnSave = MutableLiveData<Boolean>()

    init {
        updateOnStudyCount()
    }

    fun updateOnStudyCount() {
        viewModelScope.launch {
            onStudyCount.value = getOnStudyWordPairCountUseCase.execute()
        }
    }
}