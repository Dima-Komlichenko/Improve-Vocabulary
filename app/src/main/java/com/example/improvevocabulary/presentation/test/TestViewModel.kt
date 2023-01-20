package com.example.improvevocabulary.presentation.test

import android.app.Activity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.Language
import com.example.domain.model.OnStudyWordPair
import com.example.domain.model.StudiedWordPair
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
import com.example.domain.utils.LanguageConverter
import com.example.improvevocabulary.models.AnswersModel
import com.example.improvevocabulary.models.TestModel
import com.example.improvevocabulary.models.WordPair
import com.example.improvevocabulary.presentation.tests.TypeOfTestInfo
import com.example.improvevocabulary.utlis.SpeechToText
import com.example.improvevocabulary.utlis.TextToSpeech
import kotlinx.coroutines.launch
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashSet
import kotlin.random.Random

class TestViewModel(
    private val getOnStudyWordPairsUseCase: GetOnStudyWordPairsUseCase,
    private val updateOnStudyWordPairsUseCase: UpdateOnStudyWordPairUseCase,
    private val removeStudiedWordPairUseCase: RemoveStudiedWordPairUseCase,
    private val getStudiedWordPairsUseCase: GetStudiedWordPairsUseCase,
    private val saveOnStudyWordPairUseCase: SaveOnStudyWordPairUseCase,
    private val getLanguageFromLearningUseCase: GetLanguageFromLearningUseCase,
    private val getLanguageOfLearningUseCase: GetLanguageOfLearningUseCase,
    private val getOnStudyWordPairCountUseCase: GetOnStudyWordPairCountUseCase,
    private val getWasTestDescriptionShownUseCase: GetWasTestDescriptionShownUseCase,
    private val launchWasTestDescriptionShownUseCase: LaunchWasTestDescriptionShownUseCase,
    private val getWasPracticeDescriptionShownUseCase: GetWasPracticeDescriptionShownUseCase,
    private val launchWasPracticeDescriptionShownUseCase: LaunchWasPracticeDescriptionShownUseCase,
    val tts: TextToSpeech,
    val stt: SpeechToText
) : ViewModel() {
    lateinit var typeOfTestInfo: TypeOfTestInfo

    var words: MutableLiveData<ArrayList<WordPair>> = MutableLiveData<ArrayList<WordPair>>()
    var tests: MutableLiveData<ArrayList<TestModel>> = MutableLiveData<ArrayList<TestModel>>()
    var countOnStudyWords: Int = 0
    var testIndex = 0
    var pbProgress = 0

    val isFinishTest: MutableLiveData<Boolean> = MutableLiveData()

    init {
        viewModelScope.launch {
            countOnStudyWords = getOnStudyWordPairCountUseCase.execute()
        }
        isFinishTest.value = false
    }

    override fun onCleared() {
        super.onCleared()
        tts.destroy()
    }
    fun initSTT(activity: Activity) {
        stt.init(activity)
    }

    fun setSTTLang(language: Language) {
        var locale = Locale(LanguageConverter.convertLangToCode(language))
        stt.setLanguage(locale)
    }

    fun getAnswerLanguage(questionLanguage: Language): Language {
        val langFrom = getLanguageFromLearningUseCase.execute()

        return if (langFrom.value == questionLanguage.value) getLanguageOfLearningUseCase.execute()
               else langFrom
    }


    fun setTTSLanguage(value: Language) {
        tts.setLanguage(value)
    }

    fun setTTSText(value: String) {
        tts.setText(value)
    }

    fun startTTS() {
        tts.onInit(android.speech.tts.TextToSpeech.SUCCESS)
    }

    fun wasTestDescriptionShownOnce(): Boolean {
        return getWasTestDescriptionShownUseCase.execute()
    }

    fun launchTestDescriptionOnce() {
        launchWasTestDescriptionShownUseCase.execute()
    }

    fun wasPracticeDescriptionShownOnce(): Boolean {
        return getWasPracticeDescriptionShownUseCase.execute()
    }

    fun launchPracticeDescriptionOnce() {
        launchWasPracticeDescriptionShownUseCase.execute()
    }

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
                    var temp20Tests = arrayListOf<TestModel>()

                    var size = if (tempTests.count() > 19) 20 else tempTests.count()

                    for (i in 0 until size) {
                        temp20Tests.add(tempTests[i])
                    }
                    tests.value = temp20Tests
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
        viewModelScope.launch {
            saveOnStudyWordPairUseCase.execute(mapToOnStudyWordPair(wordPair))
        }
        countOnStudyWords++
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