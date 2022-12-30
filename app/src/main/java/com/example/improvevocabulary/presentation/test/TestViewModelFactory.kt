package com.example.improvevocabulary.presentation.test

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.domain.repositoriesI.WasTestDescriptionShownOnceRepository
import com.example.domain.usecase.languages.GetLanguageFromLearningUseCase
import com.example.domain.usecase.languages.GetLanguageOfLearningUseCase
import com.example.domain.usecase.onStudy.GetOnStudyWordPairCountUseCase
import com.example.domain.usecase.onStudy.GetOnStudyWordPairsUseCase
import com.example.domain.usecase.onStudy.SaveOnStudyWordPairUseCase
import com.example.domain.usecase.onStudy.UpdateOnStudyWordPairUseCase
import com.example.domain.usecase.studied.GetStudiedWordPairsUseCase
import com.example.domain.usecase.studied.RemoveStudiedWordPairUseCase
import com.example.domain.usecase.wereTestsDescShownOnce.GetWasPracticeDescriptionShownUseCase
import com.example.domain.usecase.wereTestsDescShownOnce.GetWasTestDescriptionShownUseCase
import com.example.domain.usecase.wereTestsDescShownOnce.LaunchWasPracticeDescriptionShownUseCase
import com.example.domain.usecase.wereTestsDescShownOnce.LaunchWasTestDescriptionShownUseCase
import com.example.improvevocabulary.utlis.TextToSpeech

class TestViewModelFactory(
    val getOnStudyWordPairsUseCase: GetOnStudyWordPairsUseCase,
    val updateOnStudyWordPairsUseCase: UpdateOnStudyWordPairUseCase,
    val removeStudiedWordPairUseCase: RemoveStudiedWordPairUseCase,
    val getStudiedWordPairsUseCase: GetStudiedWordPairsUseCase,
    val saveOnStudyWordPairUseCase: SaveOnStudyWordPairUseCase,
    val getLanguageFromLearningUseCase: GetLanguageFromLearningUseCase,
    val getLanguageOfLearningUseCase: GetLanguageOfLearningUseCase,
    val getOnStudyWordPairCountUseCase: GetOnStudyWordPairCountUseCase,
    val getWasTestDescriptionShownUseCase: GetWasTestDescriptionShownUseCase,
    val launchWasTestDescriptionShownUseCase: LaunchWasTestDescriptionShownUseCase,
    val getWasPracticeDescriptionShownUseCase: GetWasPracticeDescriptionShownUseCase,
    val launchWasPracticeDescriptionShownUseCase: LaunchWasPracticeDescriptionShownUseCase,
    val tts: TextToSpeech,
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
            getOnStudyWordPairCountUseCase = getOnStudyWordPairCountUseCase,
            getWasTestDescriptionShownUseCase = getWasTestDescriptionShownUseCase,
            launchWasTestDescriptionShownUseCase = launchWasTestDescriptionShownUseCase,
            getWasPracticeDescriptionShownUseCase = getWasPracticeDescriptionShownUseCase,
            launchWasPracticeDescriptionShownUseCase = launchWasPracticeDescriptionShownUseCase,
            tts = tts,
        ) as T
    }
}
