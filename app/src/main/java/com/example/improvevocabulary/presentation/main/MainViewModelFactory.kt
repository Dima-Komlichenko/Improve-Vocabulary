package com.example.improvevocabulary.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
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

class MainViewModelFactory(
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
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(
            getLanguageFromLearningUseCase = getLanguageFromLearningUseCase,
            getLanguageOfLearningUseCase = getLanguageOfLearningUseCase,
            getAppLanguageUseCase = getAppLanguageUseCase,
            saveLanguageFromLearningUseCase = saveLanguageFromLearningUseCase,
            saveLanguageOfLearningUseCase = saveLanguageOfLearningUseCase,
            getStudiedWordPairCountUseCase = getStudiedWordPairCountUseCase,
            getRepetitionWasOfferedUseCase = getRepetitionWasOfferedUseCase,
            updateRepetitionWasOfferedUseCase = updateRepetitionWasOfferedUseCase,
            getIsFirstTimeLaunchUseCase = getIsFirstTimeLaunchUseCase,
            launchFirstTimeUseCase = launchFirstTimeUseCase,
            isOnStudyListContainsStudiedWordsUseCase = isOnStudyListContainsStudiedWordsUseCase,
        ) as T
    }
}