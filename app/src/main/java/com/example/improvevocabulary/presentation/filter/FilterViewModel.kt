package com.example.improvevocabulary.presentation.filter

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.models.FilterBy
import com.example.domain.models.PressedSortButton
import com.example.domain.usecase.GetFilterByUseCase
import com.example.domain.usecase.SaveFilterByUseCase

class FilterViewModel(
    private val getFilterByUseCase: GetFilterByUseCase,
    private val saveFilterByUseCase: SaveFilterByUseCase
) : ViewModel() {

    val pressedSortButton = MutableLiveData<PressedSortButton>()


    fun saveFilterBy(filterBy: PressedSortButton) {
        saveFilterByUseCase.execute(FilterBy(filterBy))
    }

    fun load() {
        //pressedSortButton.value = PressedSortButton.valueOf(getFilterByUseCase.execute().toString())

        var pressedSortBtn = getFilterByUseCase.execute().value

        pressedSortButton.value = pressedSortBtn


    }
}