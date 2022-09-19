package com.example.improvevocabulary.presentation.lists.practiceList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.example.improvevocabulary.databinding.FragmentWordListBinding
import com.example.improvevocabulary.presentation.lists.baseList.WordAdapter
import com.example.improvevocabulary.presentation.lists.baseList.WordListFragment
import com.example.improvevocabulary.presentation.lists.baseList.WordPair
import com.example.improvevocabulary.presentation.lists.onStudyList.OnStudyWordAdapter
import com.example.improvevocabulary.utlis.TextToSpeech
import kotlinx.coroutines.launch

class StudiedListFragment : WordListFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        initAdapter(inflater, container)
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    private fun initAdapter(inflater: LayoutInflater, container: ViewGroup?) {
        binding = FragmentWordListBinding.inflate(inflater, container, false)
        adapter = StudiedWordAdapter()

        binding.recyclerView.adapter = adapter
        initWordList()
        adapter.init(words)
    }

    private fun initWordList() {
        words.addAll(
            arrayListOf(
                WordPair(0, "Checkout", "Проверить", 10),
                WordPair(1, "Available", "Доступный", 10),
                WordPair(2, "Sequential", "Последовательный", 10),
                WordPair(3, "Asynchronously", "Асинхронно", 10),
                WordPair(4, "Deployment", "Размещение, развертывание", 10),
                WordPair(5, "Pass", "Проходить, передавать", 10),
                WordPair(6, "Tech", "Технология", 10),
                WordPair(7, "Particularly", "Особенно", 10),
                WordPair(8, "Award", "Награда", 10),
                WordPair(9, "Reach", "Достигать", 10),
                WordPair(10, "Confirm", "Подтвердить", 10),
                WordPair(11, "Care", "Забота", 10),
                WordPair(12, "Majority", "Большинство", 10),
                WordPair(13, "Fear", "Страх", 10),
                WordPair(14, "Flow", "Поток", 10),
                WordPair(15, "Inheritance", "Наследование", 10),
                WordPair(16, "Property", "Свойство, имущество", 10),
                WordPair(17, "Enviropment", "Окружающая среда", 10)
            )
        )
    }
}