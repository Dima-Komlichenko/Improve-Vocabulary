package com.example.improvevocabulary.presentation.wordsFragment

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.improvevocabulary.R
import com.example.improvevocabulary.WordsActivity
import com.example.improvevocabulary.databinding.FragmentWordsBinding
import android.util.Pair

class WordsFragment : Fragment() {

    private lateinit var binding: FragmentWordsBinding
    private lateinit var viewModel: WordsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this)[WordsViewModel::class.java]
        binding = FragmentWordsBinding.inflate(inflater, container, false)

        binding.btnOnStudy.setOnClickListener {
            val intent = Intent(activity, WordsActivity::class.java)
            var options = ActivityOptions.makeSceneTransitionAnimation(
                activity,
                Pair(binding.root.rootView.findViewById(R.id.logo), "logo")
            )
            startActivity(intent, options.toBundle())
        }

        return binding.root
    }
}






















