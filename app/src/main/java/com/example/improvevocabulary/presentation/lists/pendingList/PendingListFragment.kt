package com.example.improvevocabulary.presentation.lists.pendingList

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
import com.example.improvevocabulary.presentation.lists.baseList.WordListViewModel
import com.google.android.material.snackbar.Snackbar
import javax.inject.Inject

class PendingListFragment: WordListFragment() {

    override val addViewModel: AddViewModel by activityViewModels()
    override val wordListViewModel: WordListViewModel by activityViewModels()

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
        words.reverse()
        binding = FragmentWordListBinding.inflate(inflater, container, false)

        adapter = PendingWordAdapter(tts, viewModel)
        binding.recyclerView.adapter = adapter

        viewModel.onStudyCount.observe(viewLifecycleOwner) {
            (adapter as PendingWordAdapter).getOnStudyCount(viewModel.onStudyCount.value!!)
        }
    }

    private fun addWordHandler() {
        addViewModel.clickBtnSave.observe(viewLifecycleOwner) {
            //создаем новое слово
            var newWordPair = WordPair(
                viewModel.maxWordId.value!!,
                addViewModel.firstFieldText.value!!,
                addViewModel.secondFieldText.value!!
            )
            viewModel.generateNewId()

            adapter.addWord(newWordPair, filterViewModel.pressedSortButton.value!!)
            viewModel.save(newWordPair)

            Snackbar.make(
                binding.recyclerView,
                resources.getString(R.string.new_word_created) + " \"" + newWordPair.word + "\"",
                Snackbar.LENGTH_SHORT or Snackbar.LENGTH_INDEFINITE
            )
                .show()
        }
    }
}