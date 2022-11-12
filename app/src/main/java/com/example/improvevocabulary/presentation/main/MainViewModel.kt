package com.example.improvevocabulary.presentation.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.Language
import com.example.domain.usecase.appLanguage.GetAppLanguageUseCase
import com.example.domain.usecase.languages.GetLanguageFromLearningUseCase
import com.example.domain.usecase.languages.GetLanguageOfLearningUseCase
import com.example.domain.usecase.languages.SaveLanguageFromLearningUseCase
import com.example.domain.usecase.languages.SaveLanguageOfLearningUseCase
import com.example.domain.usecase.studied.GetStudiedWordPairCountUseCase
import kotlinx.coroutines.launch

class MainViewModel(
    private val getLanguageFromLearningUseCase: GetLanguageFromLearningUseCase,
    private val getLanguageOfLearningUseCase: GetLanguageOfLearningUseCase,
    private val getAppLanguageUseCase: GetAppLanguageUseCase,
    private val saveLanguageFromLearningUseCase: SaveLanguageFromLearningUseCase,
    private val saveLanguageOfLearningUseCase: SaveLanguageOfLearningUseCase,
    private val getStudiedWordPairCountUseCase: GetStudiedWordPairCountUseCase,
) : ViewModel() {

    var appLanguage: MutableLiveData<Language> =
        MutableLiveData()

    var _languageFromLearning: MutableLiveData<Language> =
        MutableLiveData()

    var _languageOfLearning: MutableLiveData<Language> =
        MutableLiveData()

    var studiedCount: MutableLiveData<Int> =
        MutableLiveData()

    fun init() {
        viewModelScope.launch {
            appLanguage.value = getAppLanguageUseCase.execute()
            _languageFromLearning.value = getLanguageFromLearningUseCase.execute()
            _languageOfLearning.value = getLanguageOfLearningUseCase.execute()
            studiedCount.value = getStudiedWordPairCountUseCase.execute()
        }
    }

    fun saveLanguages(languageFromLearning: Language, languageOfLearning: Language) {
        saveLanguageFromLearningUseCase.execute(languageFromLearning)
        saveLanguageOfLearningUseCase.execute(languageOfLearning)
    }





}