package com.example.improvevocabulary.presentation.wordsFragment

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.improvevocabulary.R
import com.example.improvevocabulary.presentation.wordList.WordsActivity
import com.example.improvevocabulary.databinding.FragmentWordsBinding
import android.util.Pair
import soup.neumorphism.NeumorphCardView

const val WordListInfoConst = "WordListInfo"

enum class WordListInfo {
    Pending, OnStudy, Studied
}

class WordsFragment : Fragment() {

    private lateinit var binding: FragmentWordsBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentWordsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        setOnClickListener(binding.btnPending!!, WordListInfo.Pending)
        setOnClickListener(binding.btnOnStudy, WordListInfo.OnStudy)
        setOnClickListener(binding.btnStudied, WordListInfo.Studied)
    }

    private fun setOnClickListener(view: NeumorphCardView, wordListInfo: WordListInfo) {
        view.setOnClickListener {
            view.setOnClickListener {  }
            val intent = Intent(activity, WordsActivity::class.java)
            val options = ActivityOptions.makeSceneTransitionAnimation(
                activity,
                Pair(binding.root.rootView.findViewById(R.id.logo), "logo")
            )
            intent.putExtra(WordListInfoConst, wordListInfo)
            startActivity(intent, options.toBundle())
        }
    }

}






















