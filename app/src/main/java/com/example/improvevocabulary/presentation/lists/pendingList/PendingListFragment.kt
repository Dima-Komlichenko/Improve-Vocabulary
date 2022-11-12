package com.example.improvevocabulary.presentation.lists.pendingList

import android.app.Application
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import com.example.data.storage.repositoriesImpl.WordPairRepository
import com.example.domain.usecase.onStudy.RemoveOnStudyWordPairUseCase
import com.example.domain.usecase.onStudy.SaveOnStudyWordPairUseCase
import com.example.domain.usecase.pending.RemovePendingWordPairUseCase
import com.example.domain.usecase.pending.SavePendingWordPairUseCase
import com.example.domain.usecase.pending.UpdatePendingWordPairUseCase
import com.example.improvevocabulary.R
import com.example.improvevocabulary.app.App
import com.example.improvevocabulary.databinding.FragmentWordListBinding
import com.example.improvevocabulary.models.WordPair
import com.example.improvevocabulary.presentation.add.AddViewModel
import com.example.improvevocabulary.presentation.lists.baseList.WordListFragment
import com.example.improvevocabulary.presentation.lists.onStudyList.OnStudyListViewModel
import com.example.improvevocabulary.presentation.lists.onStudyList.OnStudyListViewModelFactory
import com.google.android.material.snackbar.Snackbar
import javax.inject.Inject

class PendingListFragment: WordListFragment() {

    private val addViewModel: AddViewModel by activityViewModels()
    private lateinit var viewModel: PendingListViewModel
    @Inject
    lateinit var viewModelFactory: PendingListViewModelFactory

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        (activity?.applicationContext as App).appComponent.inject(this)
        viewModel = ViewModelProvider(this, viewModelFactory)[PendingListViewModel::class.java]
        initAdapter(inflater, container)
        addWordHandler()
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun initAdapter(inflater: LayoutInflater, container: ViewGroup?) {
        super.initAdapter(inflater, container)
        if(words.isNotEmpty()) return
        binding = FragmentWordListBinding.inflate(inflater, container, false)


        adapter = PendingWordAdapter(tts,
            viewModel.updatePendingWordPairUseCase,
            viewModel.removePendingWordPairUseCase,
            viewModel.savePendingWordPairUseCase,
            viewModel.saveOnStudyWordPairUseCase,
            viewModel.removeOnStudyWordPairUseCase,
            viewModel.languageFromLearning.value!!,
            viewModel.languageOfLearning.value!!,)
        binding.recyclerView.adapter = adapter

    }

    private fun addWordHandler() {
        addViewModel.clickBtnSave.observe(viewLifecycleOwner) {
            //создаем новое слово
            var newWordPair = WordPair(
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
}