package com.example.improvevocabulary.presentation.words

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.improvevocabulary.databinding.FragmentWordsBinding

class WordsFragment : Fragment() {

    private lateinit var binding: FragmentWordsBinding
    private lateinit var viewModel: WordsViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        viewModel = ViewModelProvider(this)[WordsViewModel::class.java]
        binding = FragmentWordsBinding.inflate(inflater, container, false)
        return binding.root
    }
}