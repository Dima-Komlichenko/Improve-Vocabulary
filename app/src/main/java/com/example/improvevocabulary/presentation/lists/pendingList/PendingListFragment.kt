package com.example.improvevocabulary.presentation.lists.pendingList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.improvevocabulary.R
import com.example.improvevocabulary.databinding.FragmentWordListBinding
import com.example.improvevocabulary.models.WordPair
import com.example.improvevocabulary.presentation.add.AddViewModel
import com.example.improvevocabulary.presentation.lists.baseList.WordListFragment
import com.example.improvevocabulary.presentation.lists.onStudyList.OnStudyWordAdapter
import com.google.android.material.snackbar.Snackbar

class PendingListFragment: WordListFragment() {

    private val addViewModel: AddViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        initAdapter(inflater, container)
        addWordHandler()
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun initAdapter(inflater: LayoutInflater, container: ViewGroup?) {
        if(words.isNotEmpty()) return
        binding = FragmentWordListBinding.inflate(inflater, container, false)
        adapter = PendingWordAdapter(tts)

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
                WordPair(0, "Beyond", "Вне", 0),
                WordPair(1, "Belong", "Принадлежать", 0),
                WordPair(2, "Pretty much", "Довольно много", 0),
                WordPair(3, "Therefore", "Следовательно", 0),
                WordPair(4, "Layer", "Слой", 0),
                WordPair(5, "Perform", "Выполнять", 0),
                WordPair(6, "Sensitive", "Чувствительный, конфиденциальный", 0),
                WordPair(7, "Explicit", "Явный, точный", 0),
                WordPair(8, "Tool", "Инструмент", 0),
                WordPair(9, "Above", "Выше", 0),
                WordPair(10, "Foreground", "Передний план", 0),
                WordPair(11, "Observe", "Наблюдать", 0),
                WordPair(12, "Aware", "Осведомленный", 0),
                WordPair(13, "Within", "В пределах", 0),
                WordPair(14, "Correspond", "Соответствовать", 0),
                WordPair(15, "Therefore", " Следовательно", 0),
                WordPair(16, "Tend", "Иметь тенденцию", 0),
                WordPair(17, "Serve", "Обслуживать", 0),
                WordPair(18, "Assign", "Назначить", 0),
                WordPair(19, "Hold on", "Подождать", 0),
                WordPair(19, "Detect", "Обнаружить", 0),
                WordPair(19, "Decline", "Отклонить", 0),
                WordPair(19, "Necessary", "Необходимый", 0)
            )
        )
    }
}