package com.example.improvevocabulary.presentation.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SearchViewModel : ViewModel() {
    val searchingWord = MutableLiveData<String>()
}