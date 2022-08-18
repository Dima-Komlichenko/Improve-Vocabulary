package com.example.improvevocabulary.presentation.wordList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.SimpleItemAnimator
import com.example.improvevocabulary.databinding.FragmentWordListBinding


class WordListFragment : Fragment() {
    private lateinit var binding: FragmentWordListBinding
    private val adapter = WordAdapter()
    private val words = arrayListOf(
        WordPair(0, "Hello", "Привет"),
        WordPair(1, "Goodbye", "Пока"),
        WordPair(2, "Window", "Окно"),
        WordPair(3, "Stairs", "Лестница"),
        WordPair(4, "Moon", "Луна")
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWordListBinding.inflate(inflater, container, false)
        binding.list.adapter = adapter
        adapter.init(words)
        (binding.list.itemAnimator as SimpleItemAnimator).supportsChangeAnimations = false
        return binding.root
    }
}