package com.example.improvevocabulary.presentation.lists.onStudyList

import android.app.Application
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.data.storage.repositoriesImpl.WordPairRepository
import com.example.domain.usecase.onStudy.RemoveOnStudyWordPairUseCase
import com.example.domain.usecase.onStudy.SaveOnStudyWordPairUseCase
import com.example.domain.usecase.onStudy.UpdateOnStudyWordPairUseCase
import com.example.domain.usecase.studied.RemoveStudiedWordPairUseCase
import com.example.domain.usecase.studied.SaveStudiedWordPairUseCase
import com.example.improvevocabulary.R
import com.example.improvevocabulary.databinding.FragmentWordListBinding
import com.example.improvevocabulary.models.WordPair
import com.example.improvevocabulary.presentation.add.AddViewModel
import com.example.improvevocabulary.presentation.lists.baseList.WordListFragment
import com.google.android.material.snackbar.Snackbar

open class OnStudyListFragment : WordListFragment() {

    private val addViewModel: AddViewModel by activityViewModels()

    private lateinit var updateOnStudyWordPairUseCase: UpdateOnStudyWordPairUseCase
    private lateinit var removeOnStudyWordPairUseCase: RemoveOnStudyWordPairUseCase
    private lateinit var saveOnStudyWordPairUseCase: SaveOnStudyWordPairUseCase
    private lateinit var saveStudiedWordPairUseCase: SaveStudiedWordPairUseCase
    private lateinit var removeStudiedWordPairUseCase: RemoveStudiedWordPairUseCase

    private var repository = WordPairRepository(Application())

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        initAdapter(inflater, container)
        addWordHandler()
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun initAdapter(inflater: LayoutInflater, container: ViewGroup?) {
        super.initAdapter(inflater, container)
        if(words.isNotEmpty()) return
        binding = FragmentWordListBinding.inflate(inflater, container, false)

        updateOnStudyWordPairUseCase = UpdateOnStudyWordPairUseCase(repository)
        removeOnStudyWordPairUseCase = RemoveOnStudyWordPairUseCase(repository)
        saveOnStudyWordPairUseCase = SaveOnStudyWordPairUseCase(repository)
        saveStudiedWordPairUseCase = SaveStudiedWordPairUseCase(repository)
        removeStudiedWordPairUseCase = RemoveStudiedWordPairUseCase(repository)

        adapter = OnStudyWordAdapter(tts,
            updateOnStudyWordPairUseCase,
            removeOnStudyWordPairUseCase,
            saveOnStudyWordPairUseCase,
            saveStudiedWordPairUseCase,
            removeStudiedWordPairUseCase)

        binding.recyclerView.adapter = adapter
    }

    private fun addWordHandler() {
        addViewModel.clickBtnSave.observe(viewLifecycleOwner) {
            //создаем новое слово
            var newWordPair = WordPair(
                //words.last().id + 1,
                0,
                addViewModel.firstFieldText.value!!,
                addViewModel.secondFieldText.value!!
            )
            adapter.addWord(newWordPair)
            wordListViewModel.save(newWordPair)
            Snackbar.make(
                binding.recyclerView,
                resources.getString(R.string.new_word_created) + " \"" + newWordPair.word + "\"",
                Snackbar.LENGTH_SHORT or Snackbar.LENGTH_INDEFINITE
            ).setAction(resources.getString(R.string.ok), null)
                .show()
        }
    }

    //private fun initWordList() {
    //    words.addAll(
    //        arrayListOf(
    //            WordPair(0, "Aware", "Осведомленный", 0),
    //            WordPair(1, "Reduce", "Уменьшать", 7),
    //            WordPair(2, "Impact", "Влияние", 10),
    //            WordPair(3, "Complexity", "Сложность", 5),
    //            WordPair(4, "Reusability", "Возможность повторного переиспользования", 2),
    //            WordPair(5, "Eliminate", "Исключить", 10),
    //            WordPair(6, "Redundant", "Избыточный", 10),
    //            WordPair(7, "Statement", "Утверждение, оператор", 4),
    //            WordPair(8, "Implicit", "Скрытый", 0),
    //            WordPair(9, "Sandbox", "Песочница", 9),
    //            WordPair(10, "10", "Песочница", 9),
    //            WordPair(11, "11", "Песочница", 9),
    //            WordPair(12, "12", "Песочница", 9),
    //            WordPair(13, "13", "Песочница", 9),
    //            WordPair(14, "14", "Песочница", 9),
    //            WordPair(15, "15", "Песочница", 9),
    //            WordPair(16, "16", "Песочница", 9),
    //            WordPair(17, "17", "Песочница", 9),
    //            WordPair(18, "18", "Песочница", 9),
    //            WordPair(19, "19", "Песочница", 9)
    //        )
    //    )
    //}

}