package com.example.improvevocabulary.presentation.test

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import com.example.improvevocabulary.R
import com.example.improvevocabulary.models.WordPair
import com.example.improvevocabulary.presentation.wordList.WordsActivity
import com.example.improvevocabulary.presentation.wordList.WordsActivityResult
import com.example.improvevocabulary.presentation.wordsFragment.WordListInfo
import com.example.improvevocabulary.presentation.wordsFragment.WordListInfoConst
import com.google.android.gms.ads.FullScreenContentCallback

open class TestFragment : TestBaseFragment() {

    private val launchCheatActivity =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { res ->
            var result = res.data?.getSerializableExtra("result") as WordsActivityResult
            when (result) {
                WordsActivityResult.RestartTest -> {
                    viewModel.initTests()
                    adMob.loadAds(requireContext())

                    viewModel.testIndex = 0
                    binding.pbProgress.progress = 0
                }
                WordsActivityResult.Back -> {
                    viewModel.tts.destroy()
                    activity?.finish()
                }
            }
        }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = super.onCreateView(inflater, container, savedInstanceState)

        if(!viewModel.wasTestDescriptionShownOnce()) {
            context?.let {
                AlertDialog.Builder(it)
                    .setTitle(R.string.test)
                    .setMessage(R.string.test_description)
                    .setPositiveButton(R.string.ok) { _, _ ->
                        viewModel.launchTestDescriptionOnce()
                    }
                    .create()
                    .show()
            }
        }

        return view
    }

    override fun showTest() = with(binding) {
        super.showTest()
        tvQuestionNumber.text = (viewModel.testIndex + 1).toString()
        tvQuestionsCount.text = viewModel.tests.value!!.count().toString()
    }


    override fun rightAnswerHandler(wordPair: WordPair) {
        wordPair.countRightAnswers++
        viewModel.updateOnStudy(wordPair)
    }

    override fun wrongAnswerHandler(wordPair: WordPair) {
        wordPair.countRightAnswers = 0
        viewModel.updateOnStudy(wordPair)
    }

    override fun finishTestHandler() {
        adMob. mInterstitialAd?.fullScreenContentCallback = object: FullScreenContentCallback() {
            override fun onAdDismissedFullScreenContent() {
                Log.d(TAG, "Ad dismissed fullscreen content.")
                showWordListActivity()
            }
        }

        if(!adMob.show(requireActivity())) {
            showWordListActivity()
        }
    }

    fun showWordListActivity() {
        val intent = Intent(activity, WordsActivity::class.java)
        intent.putExtra(WordListInfoConst, WordListInfo.OnStudy)
            .putExtra(TypeOfListConst, TypeOfList.AfterTest)
        launchCheatActivity.launch(intent)
    }
}