package com.example.improvevocabulary.presentation.settings

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.Language
import com.example.domain.model.Theme
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
import kotlinx.coroutines.launch

class SettingsViewModel(
    private val getAppLanguageUseCase: GetAppLanguageUseCase,
    private val saveAppLanguageUseCase: SaveAppLanguageUseCase,
    private val getThemeUseCase: GetThemeUseCase,
    private val saveThemeUseCase: SaveThemeUseCase,
    val getLanguageFromLearningUseCase: GetLanguageFromLearningUseCase,
    val getLanguageOfLearningUseCase: GetLanguageOfLearningUseCase,
    val saveLanguageFromLearningUseCase: SaveLanguageFromLearningUseCase,
    val saveLanguageOfLearningUseCase: SaveLanguageOfLearningUseCase,
    val clearOnStudyWordPairsUseCase: ClearOnStudyWordPairsUseCase,
    val clearStudiedWordPairsUseCase: ClearStudiedWordPairsUseCase,
    val clearPendingWordPairsUseCase: ClearPendingWordPairUseCase
) : ViewModel() {
    val language = MutableLiveData<Language>()
    val theme = MutableLiveData<Theme>()

    var languageFromLearning: MutableLiveData<Language> =
        MutableLiveData()

    var languageOfLearning: MutableLiveData<Language> =
        MutableLiveData()

    fun init() {
        language.value = getAppLanguageUseCase.execute()
        theme.value = getThemeUseCase.execute()
        languageFromLearning.value = getLanguageFromLearningUseCase.execute()
        languageOfLearning.value = getLanguageOfLearningUseCase.execute()
    }

    fun saveTheme(theme: Theme) {
        saveThemeUseCase.execute(theme)
    }

    fun saveLanguage(language: Language) {
        saveAppLanguageUseCase.execute(language)
    }

    fun saveLanguageFromLearning(language: Language) {
        saveLanguageFromLearningUseCase.execute(language)
    }

    fun saveLanguageOfLearning(language: Language) {
        saveLanguageOfLearningUseCase.execute(language)
    }

    fun clearOnStudy() {
        clearOnStudyWordPairsUseCase.execute()
    }

    fun clearPending() {
        clearPendingWordPairsUseCase.execute()
    }

    fun clearStudied() {
        clearStudiedWordPairsUseCase.execute()
    }

}