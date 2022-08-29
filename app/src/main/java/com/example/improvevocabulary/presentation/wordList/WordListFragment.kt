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
        WordPair(0, "Aware", "Осведомленный", 0),
        WordPair(1, "Reduce", "Уменьшать", 7),
        WordPair(2, "Impact", "Влияние", 10),
        WordPair(3, "Complexity", "Сложность", 5),
        WordPair(4, "Reusability", "Возможность повторного переиспользования", 2),
        WordPair(4, "Eliminate", "Исключить", 10),
        WordPair(4, "Redundant", "Избыточный", 10),
        WordPair(4, "Statement", "Утверждение, оператор", 4),
        WordPair(4, "Implicit", "Скрытый", 0),
        WordPair(4, "Sandbox", "Песочница", 9)
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