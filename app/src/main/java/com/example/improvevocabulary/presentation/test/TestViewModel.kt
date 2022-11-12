package com.example.improvevocabulary.presentation.test

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.OnStudyWordPair
import com.example.domain.model.StudiedWordPair
import com.example.domain.usecase.languages.GetLanguageFromLearningUseCase
import com.example.domain.usecase.languages.GetLanguageOfLearningUseCase
import com.example.domain.usecase.onStudy.GetOnStudyWordPairsUseCase
import com.example.domain.usecase.onStudy.SaveOnStudyWordPairUseCase
import com.example.domain.usecase.onStudy.UpdateOnStudyWordPairUseCase
import com.example.domain.usecase.studied.GetStudiedWordPairsUseCase
import com.example.domain.usecase.studied.RemoveStudiedWordPairUseCase
import com.example.domain.model.Language
import com.example.improvevocabulary.models.AnswersModel
import com.example.improvevocabulary.models.TestModel
import com.example.improvevocabulary.models.WordPair
import com.example.improvevocabulary.presentation.tests.TypeOfTestInfo
import com.example.improvevocabulary.utlis.TextToSpeech
import kotlinx.coroutines.launch
import kotlin.random.Random

class TestViewModel(
    private val getOnStudyWordPairsUseCase: GetOnStudyWordPairsUseCase,
    private val updateOnStudyWordPairsUseCase: UpdateOnStudyWordPairUseCase,
    private val removeStudiedWordPairUseCase: RemoveStudiedWordPairUseCase,
    private val getStudiedWordPairsUseCase: GetStudiedWordPairsUseCase,
    private val saveOnStudyWordPairUseCase: SaveOnStudyWordPairUseCase,
    private val getLanguageFromLearningUseCase: GetLanguageFromLearningUseCase,
    private val getLanguageOfLearningUseCase: GetLanguageOfLearningUseCase,
    val tts: TextToSpeech
) : ViewModel() {
    lateinit var typeOfTestInfo: TypeOfTestInfo

    var words: MutableLiveData<ArrayList<WordPair>> = MutableLiveData<ArrayList<WordPair>>()
    var tests: MutableLiveData<ArrayList<TestModel>> = MutableLiveData<ArrayList<TestModel>>()

    fun initTests() {
        var tempWords = arrayListOf<WordPair>()
        when (typeOfTestInfo) {

            TypeOfTestInfo.Test -> {
                viewModelScope.launch {
                    getOnStudyWordPairsUseCase.execute()
                        .forEach { tempWords.add(mapToWordPair(it)) }
                    words.value = tempWords

                    var tempTests = arrayListOf<TestModel>()
                    tempWords.forEach { tempTests.add(getTest(it.id)) }
                    tempTests.shuffle()
                    tests.value = tempTests
                }
            }

            TypeOfTestInfo.Practice -> {
                viewModelScope.launch {
                    getOnStudyWordPairsUseCase.execute()
                        .forEach { tempWords.add(mapToWordPair(it)) }
                    getStudiedWordPairsUseCase.execute()
                        .forEach { tempWords.add(mapToWordPair(it)) }
                    words.value = tempWords

                    var tempTests = arrayListOf<TestModel>()
                    tempWords.forEach { tempTests.add(getTest(it.id)) }
                    tempTests.shuffle()
                    tests.value = tempTests
                }
            }

            TypeOfTestInfo.Repetition -> {
                viewModelScope.launch {
                    getStudiedWordPairsUseCase.execute()
                        .forEach { tempWords.add(mapToWordPair(it)) }
                    words.value = tempWords

                    var tempTests = arrayListOf<TestModel>()
                    tempWords.forEach { tempTests.add(getTest(it.id)) }
                    tempTests.shuffle()
                    tests.value = tempTests
                }
            }
        }
    }

    fun shuffleTests() {
        tests.value!!.shuffle()
    }

    fun updateOnStudy(wordPair: WordPair) {
        updateOnStudyWordPairsUseCase.execute(mapToOnStudyWordPair(wordPair))
    }

    fun moveStudiedToOnStudy(wordPair: WordPair) {
        removeStudiedWordPairUseCase.execute(mapToStudiedWordPair(wordPair))
        saveOnStudyWordPairUseCase.execute(mapToOnStudyWordPair(wordPair))
    }

    private fun getTest(wordPairId: Int): TestModel {
        val testIds = getAnswerIds(wordPairId)

        val id: Int
        val question: String
        val answers = mutableListOf<String>()
        val rightAnswerPosition: Int
        val language: Language

        val testOrder: Boolean = Random.nextBoolean()
        if (testOrder) {
            id = words.value?.find { it.id == wordPairId }?.id!!
            question = words.value?.find { it.id == wordPairId }?.word!!
            testIds.answers.forEach { testId -> answers.add(words.value!!.find { word -> word.id == testId }!!.translate) }
            rightAnswerPosition = testIds.rightAnswerPosition
            language = getLanguageOfLearningUseCase.execute()

        } else {
            id = words.value?.find { it.id == wordPairId }?.id!!
            question = words.value?.find { it.id == wordPairId }?.translate!!
            testIds.answers.forEach { testId -> answers.add(words.value!!.find { word -> word.id == testId }!!.word) }
            rightAnswerPosition = testIds.rightAnswerPosition
            language = getLanguageFromLearningUseCase.execute()
        }
        //TODO: проверить тот ли язык приходит в language
        return TestModel(id, question, answers, rightAnswerPosition, language)
    }

    private fun getAnswerIds(wordPairId: Int): AnswersModel {

        var uniqueAnswers: HashSet<Int> = hashSetOf()
        uniqueAnswers.add(words.value!!.find { it.id == wordPairId }!!.id) //add right answer

        while (uniqueAnswers.count() < 4) {
            var randPosition = (0 until words.value!!.count()).random()
            uniqueAnswers.add(words.value!![randPosition].id)
        }
        uniqueAnswers.shuffled()
        var answers = arrayListOf<Int>()
        answers.addAll(uniqueAnswers)
        val rightAnswerPosition = answers.indexOf(wordPairId)
        return AnswersModel(answers, rightAnswerPosition!!)
    }

    private fun mapToWordPair(dataModel: OnStudyWordPair): WordPair {
        return WordPair(
            dataModel.id,
            dataModel.word,
            dataModel.translate,
            dataModel.countRightAnswers
        )
    }

    private fun mapToWordPair(dataModel: StudiedWordPair): WordPair {
        return WordPair(
            dataModel.id,
            dataModel.word,
            dataModel.translate
        )
    }

    private fun mapToOnStudyWordPair(dataModel: WordPair): OnStudyWordPair {
        return OnStudyWordPair(
            dataModel.id,
            dataModel.word,
            dataModel.translate,
            dataModel.countRightAnswers
        )
    }

    private fun mapToStudiedWordPair(dataModel: WordPair): StudiedWordPair {
        return StudiedWordPair(
            dataModel.id,
            dataModel.word,
            dataModel.translate
        )
    }


}