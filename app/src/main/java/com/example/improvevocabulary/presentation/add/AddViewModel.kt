package com.example.improvevocabulary.presentation.add

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.models.PressedSortButton

class AddViewModel : ViewModel() {
    val firstFieldText = MutableLiveData<String>()
    val secondFieldText = MutableLiveData<String>()
    val clickBtnSave = MutableLiveData<Boolean>()
}