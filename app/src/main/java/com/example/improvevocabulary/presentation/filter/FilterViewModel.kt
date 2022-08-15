package com.example.improvevocabulary.presentation.filter

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.improvevocabulary.presentation.listHeader.PressedButton

enum class PressedAlphabeticallyButton {
    ALPHABETICALLY, NON_ALPHABETICALLY
}

enum class PressedInOrderButton {
    NEWER, OLDER
}

class FilterViewModel : ViewModel() {
    val pressedAlphabeticallyButton = MutableLiveData<PressedAlphabeticallyButton>()
    val pressedInOrderButton = MutableLiveData<PressedInOrderButton>()
}