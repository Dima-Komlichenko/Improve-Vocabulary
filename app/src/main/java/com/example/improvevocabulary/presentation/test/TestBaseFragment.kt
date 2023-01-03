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
import com.example.improvevocabulary.R
import com.example.improvevocabulary.databinding.FragmentTestBinding
import com.example.improvevocabulary.models.WordPair
import com.example.improvevocabulary.utlis.AdMob
import com.example.improvevocabulary.utlis.TextToSpeech
import kotlinx.coroutines.*
import soup.neumorphism.NeumorphCardView


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
        viewModel.tests.observe(viewLifecycleOwner) {
            if (viewModel.tests.value!!.isNotEmpty())
                showTest()
        }

        adMob = AdMob()
        adMob.loadAds(requireContext())
        return binding.root
    }

    protected open fun showTest() = with(binding) {
        CoroutineScope(SupervisorJob() + Dispatchers.Default).launch(Job()) {
            var flag = false
            while (!flag) {
                if(viewModel.tts.isLanguageAvailable(viewModel.tests.value!![viewModel.testIndex].questionLang) != -2) flag = true
            }
            viewModel.setTTSLanguage(viewModel.tests.value!![viewModel.testIndex].questionLang)
            viewModel.setTTSText(viewModel.tests.value!![viewModel.testIndex].question)
            viewModel.startTTS()
        }
        tvQuestion?.text = viewModel.tests.value!![viewModel.testIndex].question
        tvAnswer1?.text = viewModel.tests.value!![viewModel.testIndex].answers[0]
        tvAnswer2?.text = viewModel.tests.value!![viewModel.testIndex].answers[1]
        tvAnswer3?.text = viewModel.tests.value!![viewModel.testIndex].answers[2]
        tvAnswer4?.text = viewModel.tests.value!![viewModel.testIndex].answers[3]

        setOnClickAnswerButton(0, binding.cvAnswer1, binding.clAnswer1!!, binding.tvAnswer1!!)
        setOnClickAnswerButton(1, binding.cvAnswer2, binding.clAnswer2!!, binding.tvAnswer2!!)
        setOnClickAnswerButton(2, binding.cvAnswer3, binding.clAnswer3!!, binding.tvAnswer3!!)
        setOnClickAnswerButton(3, binding.cvAnswer4, binding.clAnswer4!!, binding.tvAnswer4!!)

        binding.btnSound.setOnClickListener {
            viewModel.startTTS()
        }
    }

    protected open fun setOnClickAnswerButton(
        position: Int,
        cardView: NeumorphCardView,
        constraintLayout: ConstraintLayout,
        textView: TextView
    ) {
        cardView.setOnClickListener {
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
    }

    protected open fun rightAnswerHandler(wordPair: WordPair) {}
    protected open fun wrongAnswerHandler(wordPair: WordPair) {}
    protected open fun finishTestHandler() {}
}