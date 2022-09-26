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

const val WordListInfoConst = "WordListInfo"

enum class WordListInfo {
    OnStudy, Studied
}

class WordsFragment : Fragment() {

    private lateinit var binding: FragmentWordsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentWordsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        binding.btnOnStudy.setOnClickListener {
            binding.btnOnStudy.setOnClickListener {  }
            val intent = Intent(activity, WordsActivity::class.java)
            var options = ActivityOptions.makeSceneTransitionAnimation(
                activity,
                Pair(binding.root.rootView.findViewById(R.id.logo), "logo")
            )
            intent.putExtra(WordListInfoConst, WordListInfo.OnStudy)
            startActivity(intent, options.toBundle())
        }

        binding.btnStudied.setOnClickListener {
            binding.btnStudied.setOnClickListener {  }
            val intent = Intent(activity, WordsActivity::class.java)
            var options = ActivityOptions.makeSceneTransitionAnimation(
                activity,
                Pair(binding.root.rootView.findViewById(R.id.logo), "logo")
            )
            intent.putExtra(WordListInfoConst, WordListInfo.Studied)
            startActivity(intent, options.toBundle())
        }
    }

}






















