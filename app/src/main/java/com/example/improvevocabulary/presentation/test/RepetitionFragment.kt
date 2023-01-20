package com.example.improvevocabulary.presentation.test

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import com.example.improvevocabulary.R
import com.example.improvevocabulary.models.WordPair
import com.example.improvevocabulary.presentation.wordList.WordsActivity
import com.example.improvevocabulary.presentation.wordsFragment.WordListInfo
import com.example.improvevocabulary.presentation.wordsFragment.WordListInfoConst
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.material.snackbar.Snackbar

class RepetitionFragment : TestBaseFragment() {

    val launchCheatActivity =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            viewModel.tts.destroy()
            activity?.finish()
        }


    override fun showTest() = with(binding) {
        super.showTest()
        tvQuestionNumber.text = (viewModel.testIndex + 1).toString()
        tvQuestionsCount.text = viewModel.tests.value!!.count().toString()
    }

    override fun wrongAnswerHandler(wordPair: WordPair) {
        if (viewModel.countOnStudyWords >= 30) {
            Snackbar.make(
                binding.root,
                resources.getString(R.string.limit_30),
                Snackbar.LENGTH_SHORT or Snackbar.LENGTH_INDEFINITE
            )
                .show()
            return
        }
        viewModel.moveStudiedToOnStudy(wordPair)
    }

    override fun finishTestHandler() {
        adMob. mInterstitialAd?.fullScreenContentCallback = object: FullScreenContentCallback() {
            override fun onAdDismissedFullScreenContent() {
                Log.d(ContentValues.TAG, "Ad dismissed fullscreen content.")
                showWordListActivity()
            }
        }
        if(!adMob.show(requireActivity())) {
            showWordListActivity()
        }
    }

    fun showWordListActivity() {
        val intent = Intent(activity, WordsActivity::class.java)
        intent.putExtra(WordListInfoConst, WordListInfo.Studied)
            .putExtra(TypeOfListConst, TypeOfList.AfterTest)
        launchCheatActivity.launch(intent)
    }
}