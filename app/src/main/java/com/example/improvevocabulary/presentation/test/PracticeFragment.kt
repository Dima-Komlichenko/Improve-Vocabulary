package com.example.improvevocabulary.presentation.test

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import com.example.improvevocabulary.R
import com.example.improvevocabulary.models.WordPair
import com.google.android.material.snackbar.Snackbar

class PracticeFragment : TestBaseFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = super.onCreateView(inflater, container, savedInstanceState)

        binding.pbProgress.visibility = View.INVISIBLE
        binding.tvQuestionNumber.visibility = View.INVISIBLE
        binding.tvSlash.visibility = View.INVISIBLE
        binding.tvQuestionsCount.visibility = View.INVISIBLE

        if (!viewModel.wasPracticeDescriptionShownOnce()) {
            context?.let {
                AlertDialog.Builder(it)
                    .setTitle(R.string.practice)
                    .setMessage(R.string.practice_description)
                    .setPositiveButton(R.string.ok) { _, _ ->
                        viewModel.launchPracticeDescriptionOnce()
                    }
                    .create()
                    .show()
            }
        }

        return view
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.tts.destroy()
    }

    override fun wrongAnswerHandler(wordPair: WordPair) {
        if (viewModel.countOnStudyWords >= 30) {
            Snackbar.make(
                binding.root,
                resources.getString(R.string.limit_30),
                Snackbar.LENGTH_SHORT or Snackbar.LENGTH_INDEFINITE
            ).show()
            return
        }
        viewModel.moveStudiedToOnStudy(wordPair)
    }

    override fun finishTestHandler() {
        viewModel.testIndex = 0
        viewModel.shuffleTests()
        showTest()
    }
}