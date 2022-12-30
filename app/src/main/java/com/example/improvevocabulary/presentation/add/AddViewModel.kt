package com.example.improvevocabulary.presentation.add

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecase.languages.GetLanguageFromLearningUseCase
import com.example.domain.usecase.languages.GetLanguageOfLearningUseCase
import com.example.domain.usecase.onStudy.GetOnStudyWordPairCountUseCase
import com.example.domain.model.Language
import com.example.improvevocabulary.utlis.TextToSpeech
import kotlinx.coroutines.launch

class AddViewModel(
    val getOnStudyWordPairCountUseCase: GetOnStudyWordPairCountUseCase,
    val getLanguageFromLearningUseCase: GetLanguageFromLearningUseCase,
    val getLanguageOfLearningUseCase: GetLanguageOfLearningUseCase,
    ) : ViewModel() {

    var onStudyCount: MutableLiveData<Int> =
        MutableLiveData()

    var languageFromLearning: MutableLiveData<Language> =
        MutableLiveData()

    var languageOfLearning: MutableLiveData<Language> =
        MutableLiveData()

    lateinit var listType: String

    val firstFieldText = MutableLiveData<String>()
    val secondFieldText = MutableLiveData<String>()
    val clickBtnSave = MutableLiveData<Boolean>()

    init {
        updateOnStudyCount()
        viewModelScope.launch {
            languageFromLearning.value = getLanguageFromLearningUseCase.execute()
            languageOfLearning.value = getLanguageOfLearningUseCase.execute()
        }
    }

    fun updateOnStudyCount() {
        viewModelScope.launch {
            onStudyCount.value = getOnStudyWordPairCountUseCase.execute()

        }
    }

    fun initListType(value: String) {
        listType = value
    }
}