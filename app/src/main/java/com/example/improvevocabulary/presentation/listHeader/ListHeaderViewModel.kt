package com.example.improvevocabulary.presentation.listHeader

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

enum class PressedFilterButton {
    FILTER_BTN, SEARCH_BTN, ADD_BTN
}

class ListHeaderViewModel : ViewModel() {
     val pressedFilterButtonId = MutableLiveData<PressedFilterButton>()

}