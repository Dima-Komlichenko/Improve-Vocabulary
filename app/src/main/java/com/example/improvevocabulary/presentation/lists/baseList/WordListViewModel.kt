package com.example.improvevocabulary.presentation.lists.baseList

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.improvevocabulary.presentation.wordsFragment.WordListInfo

class WordListViewModel: ViewModel() {
    var wordListInfo: MutableLiveData<WordListInfo> = MutableLiveData(WordListInfo.OnStudy)
}