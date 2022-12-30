package com.example.improvevocabulary.presentation.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.Language
import com.example.domain.usecase.appLanguage.GetAppLanguageUseCase
import com.example.domain.usecase.isFirstTimeLaunch.GetIsFirstTimeLaunchUseCase
import com.example.domain.usecase.isFirstTimeLaunch.LaunchFirstTimeUseCase
import com.example.domain.usecase.languages.GetLanguageFromLearningUseCase
import com.example.domain.usecase.languages.GetLanguageOfLearningUseCase
import com.example.domain.usecase.languages.SaveLanguageFromLearningUseCase
import com.example.domain.usecase.languages.SaveLanguageOfLearningUseCase
import com.example.domain.usecase.onStudy.IsOnStudyListContainsStudiedWordsUseCase
import com.example.domain.usecase.languages.repetitionWasOffered.GetRepetitionWasOfferedUseCase
import com.example.domain.usecase.languages.repetitionWasOffered.UpdateRepetitionWasOfferedUseCase
import com.example.domain.usecase.studied.GetStudiedWordPairCountUseCase
import kotlinx.coroutines.launch

class MainViewModel(
    private val getLanguageFromLearningUseCase: GetLanguageFromLearningUseCase,
    private val getLanguageOfLearningUseCase: GetLanguageOfLearningUseCase,
    private val getAppLanguageUseCase: GetAppLanguageUseCase,
    private val saveLanguageFromLearningUseCase: SaveLanguageFromLearningUseCase,
    private val saveLanguageOfLearningUseCase: SaveLanguageOfLearningUseCase,
    private val getStudiedWordPairCountUseCase: GetStudiedWordPairCountUseCase,
    private val getRepetitionWasOfferedUseCase: GetRepetitionWasOfferedUseCase,
    private val updateRepetitionWasOfferedUseCase: UpdateRepetitionWasOfferedUseCase,
    private val getIsFirstTimeLaunchUseCase: GetIsFirstTimeLaunchUseCase,
    private val launchFirstTimeUseCase: LaunchFirstTimeUseCase,
    private val isOnStudyListContainsStudiedWordsUseCase: IsOnStudyListContainsStudiedWordsUseCase,
) : ViewModel() {

    val appLanguage: MutableLiveData<Language> =
        MutableLiveData()

    private val _languageFromLearning: MutableLiveData<Language> =
        MutableLiveData()

    private val _languageOfLearning: MutableLiveData<Language> =
        MutableLiveData()

    val studiedCount: MutableLiveData<Int> =
        MutableLiveData()

    val isFirstTimeLaunch: MutableLiveData<Boolean> = MutableLiveData()

    val repetitionWasOffered: MutableLiveData<String> = MutableLiveData()

    val isOnStudyListContainsStudiedWords: MutableLiveData<Boolean> = MutableLiveData()


    fun init() {
        appLanguage.value = getAppLanguageUseCase.execute()
        viewModelScope.launch {
            isOnStudyListContainsStudiedWords.value = isOnStudyListContainsStudiedWordsUseCase.execute()
            _languageFromLearning.value = getLanguageFromLearningUseCase.execute()
            _languageOfLearning.value = getLanguageOfLearningUseCase.execute()
            studiedCount.value = getStudiedWordPairCountUseCase.execute()
        }
        repetitionWasOffered.value = getRepetitionWasOfferedUseCase.execute()
        isFirstTimeLaunch.value = getIsFirstTimeLaunchUseCase.execute()

    }

    fun saveLanguages(languageFromLearning: Language, languageOfLearning: Language) {
        saveLanguageFromLearningUseCase.execute(languageFromLearning)
        saveLanguageOfLearningUseCase.execute(languageOfLearning)
    }

    fun updateRepetitionWasOffered(day: String) {
        updateRepetitionWasOfferedUseCase.execute(day)
    }

    fun launchAppFirstTime() {
        launchFirstTimeUseCase.execute()
    }
}