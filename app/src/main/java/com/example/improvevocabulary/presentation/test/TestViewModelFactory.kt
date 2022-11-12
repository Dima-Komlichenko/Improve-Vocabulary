package com.example.improvevocabulary.presentation.test

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.domain.usecase.languages.GetLanguageFromLearningUseCase
import com.example.domain.usecase.languages.GetLanguageOfLearningUseCase
import com.example.domain.usecase.onStudy.GetOnStudyWordPairsUseCase
import com.example.domain.usecase.onStudy.SaveOnStudyWordPairUseCase
import com.example.domain.usecase.onStudy.UpdateOnStudyWordPairUseCase
import com.example.domain.usecase.studied.GetStudiedWordPairsUseCase
import com.example.domain.usecase.studied.RemoveStudiedWordPairUseCase
import com.example.improvevocabulary.utlis.TextToSpeech

class TestViewModelFactory(
    val getOnStudyWordPairsUseCase: GetOnStudyWordPairsUseCase,
    val updateOnStudyWordPairsUseCase: UpdateOnStudyWordPairUseCase,
    val removeStudiedWordPairUseCase: RemoveStudiedWordPairUseCase,
    val getStudiedWordPairsUseCase: GetStudiedWordPairsUseCase,
    val saveOnStudyWordPairUseCase: SaveOnStudyWordPairUseCase,
    val getLanguageFromLearningUseCase: GetLanguageFromLearningUseCase,
    val getLanguageOfLearningUseCase: GetLanguageOfLearningUseCase,
    val tts: TextToSpeech
)  : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return TestViewModel(
            getOnStudyWordPairsUseCase = getOnStudyWordPairsUseCase,
            updateOnStudyWordPairsUseCase = updateOnStudyWordPairsUseCase,
            removeStudiedWordPairUseCase = removeStudiedWordPairUseCase,
            getStudiedWordPairsUseCase = getStudiedWordPairsUseCase,
            saveOnStudyWordPairUseCase = saveOnStudyWordPairUseCase,
            getLanguageFromLearningUseCase = getLanguageFromLearningUseCase,
            getLanguageOfLearningUseCase = getLanguageOfLearningUseCase,
            tts = tts
        ) as T
    }
}
