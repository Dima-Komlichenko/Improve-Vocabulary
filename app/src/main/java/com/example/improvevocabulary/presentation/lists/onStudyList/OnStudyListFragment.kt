package com.example.improvevocabulary.presentation.lists.onStudyList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import com.example.improvevocabulary.R
import com.example.improvevocabulary.app.App
import com.example.improvevocabulary.databinding.FragmentWordListBinding
import com.example.improvevocabulary.models.WordPair
import com.example.improvevocabulary.presentation.add.AddViewModel
import com.example.improvevocabulary.presentation.lists.baseList.WordListFragment
import com.google.android.material.snackbar.Snackbar
import javax.inject.Inject

open class OnStudyListFragment : WordListFragment() {

    private val addViewModel: AddViewModel by activityViewModels()
    private lateinit var viewModel: OnStudyListViewModel
    @Inject
    lateinit var viewModelFactory: OnStudyListViewModelFactory



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        (activity?.applicationContext as App).appComponent.inject(this)
        viewModel = ViewModelProvider(this, viewModelFactory)[OnStudyListViewModel::class.java]
        initAdapter(inflater, container)
        addWordHandler()
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun initAdapter(inflater: LayoutInflater, container: ViewGroup?) {
        super.initAdapter(inflater, container)
        if(words.isNotEmpty()) return
        binding = FragmentWordListBinding.inflate(inflater, container, false)


        adapter = OnStudyWordAdapter(tts,
            viewModel.updateOnStudyWordPairUseCase,
            viewModel.removeOnStudyWordPairUseCase,
            viewModel.saveOnStudyWordPairUseCase,
            viewModel.saveStudiedWordPairUseCase,
            viewModel.removeStudiedWordPairUseCase,
            viewModel.languageFromLearning.value!!,
            viewModel.languageOfLearning.value!!,
            addViewModel,
            )

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
            viewModel.save(newWordPair)
            Snackbar.make(
                binding.recyclerView,
                resources.getString(R.string.new_word_created) + " \"" + newWordPair.word + "\"",
                Snackbar.LENGTH_SHORT or Snackbar.LENGTH_INDEFINITE
            ).setAction(R.string.ok) {}
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