package com.example.improvevocabulary.presentation.lists.onStudyList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.example.improvevocabulary.R
import com.example.improvevocabulary.databinding.FragmentWordListBinding
import com.example.improvevocabulary.presentation.add.AddViewModel
import com.example.improvevocabulary.presentation.lists.baseList.WordListFragment
import com.example.improvevocabulary.presentation.lists.baseList.WordPair
import com.example.improvevocabulary.presentation.lists.practiceList.StudiedWordAdapter
import com.example.improvevocabulary.utlis.TextToSpeech
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch

open class OnStudyListFragment : WordListFragment() {

    private val addViewModel: AddViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        initAdapter(inflater, container)
        addWordHandler()
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    private fun initAdapter(inflater: LayoutInflater, container: ViewGroup?) {
        binding = FragmentWordListBinding.inflate(inflater, container, false)
        adapter = OnStudyWordAdapter()

        binding.recyclerView.adapter = adapter
        initWordList()
        adapter.init(words)
    }

    private fun addWordHandler() {
        addViewModel.clickBtnSave.observe(viewLifecycleOwner) {
            //создаем новое слово
            var newWordPair = WordPair(
                words.last().id + 1,
                addViewModel.firstFieldText.value!!,
                addViewModel.secondFieldText.value!!
            )
            adapter.addWord(newWordPair)

            Snackbar.make(
                binding.recyclerView,
                resources.getString(R.string.new_word_created) + " \"" + newWordPair.word + "\"",
                Snackbar.LENGTH_SHORT or Snackbar.LENGTH_INDEFINITE
            ).setAction(resources.getString(R.string.ok), null)
                .show()
        }
    }

    private fun initWordList() {
        words.addAll(
            arrayListOf(
                WordPair(0, "Aware", "Осведомленный", 0),
                WordPair(1, "Reduce", "Уменьшать", 7),
                WordPair(2, "Impact", "Влияние", 10),
                WordPair(3, "Complexity", "Сложность", 5),
                WordPair(4, "Reusability", "Возможность повторного переиспользования", 2),
                WordPair(5, "Eliminate", "Исключить", 10),
                WordPair(6, "Redundant", "Избыточный", 10),
                WordPair(7, "Statement", "Утверждение, оператор", 4),
                WordPair(8, "Implicit", "Скрытый", 0),
                WordPair(9, "Sandbox", "Песочница", 9)
            )
        )
    }

}