package com.example.improvevocabulary.presentation.listHeader

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

enum class PressedButton {
    FILTER_BTN, SEARCH_BTN, ADD_BTN
}

class ListHeaderViewModel : ViewModel() {
     val pressedButtonId = MutableLiveData<PressedButton>()

}