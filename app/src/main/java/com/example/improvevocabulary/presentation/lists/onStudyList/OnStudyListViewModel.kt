package com.example.improvevocabulary.presentation.lists.onStudyList

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.model.Language
import com.example.domain.model.OnStudyWordPair
import com.example.domain.usecase.languages.GetLanguageFromLearningUseCase
import com.example.domain.usecase.languages.GetLanguageOfLearningUseCase
import com.example.domain.usecase.onStudy.RemoveOnStudyWordPairUseCase
import com.example.domain.usecase.onStudy.SaveOnStudyWordPairUseCase
import com.example.domain.usecase.onStudy.UpdateOnStudyWordPairUseCase
import com.example.domain.usecase.studied.RemoveStudiedWordPairUseCase
import com.example.domain.usecase.studied.SaveStudiedWordPairUseCase
import com.example.improvevocabulary.models.WordPair

class OnStudyListViewModel(
    val updateOnStudyWordPairUseCase: UpdateOnStudyWordPairUseCase,
    val removeOnStudyWordPairUseCase: RemoveOnStudyWordPairUseCase,
    val saveOnStudyWordPairUseCase: SaveOnStudyWordPairUseCase,
    val saveStudiedWordPairUseCase: SaveStudiedWordPairUseCase,
    val removeStudiedWordPairUseCase: RemoveStudiedWordPairUseCase,
    val getLanguageFromLearningUseCase: GetLanguageFromLearningUseCase,
    val getLanguageOfLearningUseCase: GetLanguageOfLearningUseCase
) : ViewModel() {

    val languageFromLearning: MutableLiveData<Language> = MutableLiveData()
    val languageOfLearning: MutableLiveData<Language> = MutableLiveData()

    init {
        languageFromLearning.value = getLanguageFromLearningUseCase.execute()
        languageOfLearning.value = getLanguageOfLearningUseCase.execute()
    }

    fun save(wordPair: WordPair) {
        saveOnStudyWordPairUseCase.execute(mapToOnStudy(wordPair))
    }

    private fun mapToOnStudy(dataModel: WordPair): OnStudyWordPair {
        return OnStudyWordPair(dataModel.id, dataModel.word, dataModel.translate)
    }
}