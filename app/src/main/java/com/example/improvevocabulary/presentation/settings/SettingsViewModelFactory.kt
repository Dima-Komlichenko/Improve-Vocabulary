package com.example.improvevocabulary.presentation.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.domain.usecase.appLanguage.GetAppLanguageUseCase
import com.example.domain.usecase.theme.GetThemeUseCase
import com.example.domain.usecase.appLanguage.SaveAppLanguageUseCase
import com.example.domain.usecase.languages.GetLanguageFromLearningUseCase
import com.example.domain.usecase.languages.GetLanguageOfLearningUseCase
import com.example.domain.usecase.languages.SaveLanguageFromLearningUseCase
import com.example.domain.usecase.languages.SaveLanguageOfLearningUseCase
import com.example.domain.usecase.onStudy.ClearOnStudyWordPairsUseCase
import com.example.domain.usecase.pending.ClearPendingWordPairUseCase
import com.example.domain.usecase.studied.ClearStudiedWordPairsUseCase
import com.example.domain.usecase.theme.SaveThemeUseCase

class SettingsViewModelFactory(
    val getAppLanguageUseCase: GetAppLanguageUseCase,
    val saveAppLanguageUseCase: SaveAppLanguageUseCase,
    val getThemeUseCase: GetThemeUseCase,
    val saveThemeUseCase: SaveThemeUseCase,
    val getLanguageFromLearningUseCase: GetLanguageFromLearningUseCase,
    val getLanguageOfLearningUseCase: GetLanguageOfLearningUseCase,
    val saveLanguageFromLearningUseCase: SaveLanguageFromLearningUseCase,
    val saveLanguageOfLearningUseCase: SaveLanguageOfLearningUseCase,
    val clearOnStudyWordPairsUseCase: ClearOnStudyWordPairsUseCase,
    val clearStudiedWordPairsUseCase: ClearStudiedWordPairsUseCase,
    val clearPendingWordPairsUseCase: ClearPendingWordPairUseCase
) : ViewModelProvider.Factory {


    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SettingsViewModel(
            getAppLanguageUseCase = getAppLanguageUseCase,
            saveAppLanguageUseCase = saveAppLanguageUseCase,
            getThemeUseCase = getThemeUseCase,
            saveThemeUseCase = saveThemeUseCase,
            getLanguageFromLearningUseCase = getLanguageFromLearningUseCase,
            getLanguageOfLearningUseCase = getLanguageOfLearningUseCase,
            saveLanguageFromLearningUseCase = saveLanguageFromLearningUseCase,
            saveLanguageOfLearningUseCase = saveLanguageOfLearningUseCase,
            clearOnStudyWordPairsUseCase = clearOnStudyWordPairsUseCase,
            clearStudiedWordPairsUseCase = clearStudiedWordPairsUseCase,
            clearPendingWordPairsUseCase = clearPendingWordPairsUseCase
        ) as T
    }
}