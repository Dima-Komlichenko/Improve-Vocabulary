package com.example.improvevocabulary.presentation.lists.listHeader

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

enum class PressedFilterButton {
    FILTER_BTN, SEARCH_BTN, ADD_BTN, REPEAT_BTN
}

class ListHeaderViewModel : ViewModel() {
     val pressedFilterButtonId = MutableLiveData<PressedFilterButton>()
}