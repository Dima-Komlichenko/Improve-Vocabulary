package com.example.improvevocabulary.presentation.test

import android.content.Context
import android.os.Bundle
import android.view.ContextThemeWrapper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.example.domain.utils.LanguageConverter
import com.example.improvevocabulary.R
import com.example.improvevocabulary.databinding.FragmentTestBinding
import com.example.improvevocabulary.models.WordPair
import com.example.improvevocabulary.utlis.AdMob
import com.example.improvevocabulary.utlis.DataConverter
import com.example.improvevocabulary.utlis.SpeechToText
import kotlinx.coroutines.*
import soup.neumorphism.NeumorphCardView
import java.util.*


const val TypeOfListConst = "TypeOfListConst"

enum class TypeOfList {
    Base, AfterTest
}

open class TestBaseFragment : Fragment() {

    protected lateinit var binding: FragmentTestBinding
    protected val viewModel: TestViewModel by activityViewModels()
    protected lateinit var adMob: AdMob


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentTestBinding.inflate(inflater, container, false)
        binding.tvAnswer5.text = getString(R.string.wrong_answer)

        adMob = AdMob()
        adMob.loadAds(requireContext())
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        viewModel.tests.observe(viewLifecycleOwner) {
            if (viewModel.tests.value!!.isNotEmpty())
                showTest()
        }
    }

    protected open fun showTest() = with(binding) {
        CoroutineScope(SupervisorJob() + Dispatchers.Default).launch(Job()) {
            var flag = false
            while (!flag) {
                if (viewModel.tts.isLanguageAvailable(viewModel.tests.value!![viewModel.testIndex].questionLang) != -2) flag =
                    true
            }
            viewModel.setTTSLanguage(viewModel.tests.value!![viewModel.testIndex].questionLang)
            viewModel.setTTSText(viewModel.tests.value!![viewModel.testIndex].question)
            viewModel.startTTS()
        }

        tvQuestion.text = viewModel.tests.value!![viewModel.testIndex].question
        tvAnswer1.text = viewModel.tests.value!![viewModel.testIndex].answers[0]
        tvAnswer2.text = viewModel.tests.value!![viewModel.testIndex].answers[1]
        tvAnswer3.text = viewModel.tests.value!![viewModel.testIndex].answers[2]
        tvAnswer4.text = viewModel.tests.value!![viewModel.testIndex].answers[3]
        tvWrongSpeechAnswer.text = ""
        cvAnswer1.setOnClickListener {
            answerTest(0, cvAnswer1, clAnswer1, tvAnswer1, false)
        }
        cvAnswer2.setOnClickListener {
            answerTest(1, cvAnswer2, clAnswer2, tvAnswer2, false)
        }
        cvAnswer3.setOnClickListener {
            answerTest(2, cvAnswer3, clAnswer3, tvAnswer3, false)
        }
        cvAnswer4.setOnClickListener {
            answerTest(3, cvAnswer4, clAnswer4, tvAnswer4, false)
        }
        cvAnswer5.setOnClickListener {
            answerTest(4, cvAnswer5, clAnswer5, tvAnswer5, false)
        }

        btnSound.setOnClickListener {
            viewModel.startTTS()
        }

        var stt = SpeechToText(requireActivity())
        var locale = Locale(
            LanguageConverter.convertLangToCode(
                viewModel.getAnswerLanguage(viewModel.tests.value!![viewModel.testIndex].questionLang)
            )
        )
        stt.setLanguage(locale)

        stt.result.observe(viewLifecycleOwner) {

            val res1 = DataConverter.convertToLowerCaseAndNoSymbols(tvAnswer1.text.toString())
            if(res1 == it!!) {
                answerTest(0, cvAnswer1, clAnswer1, tvAnswer1, false)
                return@observe
            }

            val res2 = DataConverter.convertToLowerCaseAndNoSymbols(tvAnswer2.text.toString())
            if(res2 == it) {
                answerTest(1, cvAnswer2, clAnswer2, tvAnswer2, false)
                return@observe
            }

            val res3 = DataConverter.convertToLowerCaseAndNoSymbols(tvAnswer3.text.toString())
            if(res3 == it) {
                answerTest(2, cvAnswer3, clAnswer3, tvAnswer3, false)
                return@observe
            }

            val res4 = DataConverter.convertToLowerCaseAndNoSymbols(tvAnswer4.text.toString())
            if(res4 == it) {
                answerTest(3, cvAnswer4, clAnswer4, tvAnswer4, false)
                return@observe
            }
            tvWrongSpeechAnswer.text = it
            answerTest(4, cvAnswer5, clAnswer5, tvAnswer5, true)
        }

        btnSpeech.setOnClickListener {
            stt.isRecording = if (!stt.isRecording) {
                stt.record()
                true
            } else {
                stt.finish()
                false
            }
        }

    }

    protected open fun answerTest(position: Int, cardView: NeumorphCardView, constraintLayout: ConstraintLayout, textView: TextView, delay: Boolean) {
            var wordPair =
                viewModel.words.value!!.find { viewModel.tests.value!![viewModel.testIndex].id == it.id }

            var cl: ConstraintLayout? = null

            if (viewModel.tests.value!![viewModel.testIndex].rightAnswerPosition == position) { //right answer

                val contextThemeWrapper: Context =
                    ContextThemeWrapper(requireContext(), R.style.right_answer_background)
                cl = ConstraintLayout(contextThemeWrapper)

                cl.layoutParams = constraintLayout.layoutParams

                constraintLayout.removeView(textView)
                cl.addView(textView)

                cardView.removeView(constraintLayout)
                cardView.addView(cl)

                constraintLayout.visibility = View.GONE

                rightAnswerHandler(wordPair!!)
            } else { //wrong answer

                val contextThemeWrapper: Context =
                    ContextThemeWrapper(requireContext(), R.style.wrong_answer_background)
                cl = ConstraintLayout(contextThemeWrapper)

                cl.layoutParams = constraintLayout.layoutParams

                constraintLayout.removeView(textView)
                cl.addView(textView)

                cardView.removeView(constraintLayout)
                cardView.addView(cl)

                constraintLayout.visibility = View.GONE

                wrongAnswerHandler(wordPair!!)
            }

            lifecycleScope.launch {
                for (i in 0..40) {
                    delay(10)
                    binding.pbProgress.progress += 1 // 1
                }
                if(delay) delay(500)

                cl.removeView(textView)
                constraintLayout.addView(textView)

                cardView.removeView(cl)
                cardView.addView(constraintLayout)

                constraintLayout.visibility = View.VISIBLE
                cl.visibility = View.GONE

                if (viewModel.testIndex + 1 == viewModel.tests.value!!.count()) { //если конец теста
                    finishTestHandler()
                } else {
                    viewModel.testIndex++
                    showTest()
                }
        }
    }

    protected open fun rightAnswerHandler(wordPair: WordPair) {}
    protected open fun wrongAnswerHandler(wordPair: WordPair) {}
    protected open fun finishTestHandler() {}
}