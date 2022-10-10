package com.example.improvevocabulary.presentation.add

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AddViewModel : ViewModel() {
    val firstFieldText = MutableLiveData<String>()
    val secondFieldText = MutableLiveData<String>()
    val clickBtnSave = MutableLiveData<Boolean>()
}