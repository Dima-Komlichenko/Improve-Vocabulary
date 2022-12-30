package com.example.improvevocabulary.presentation.lists.studiedList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.improvevocabulary.app.App
import com.example.improvevocabulary.databinding.FragmentWordListBinding
import com.example.improvevocabulary.models.WordPair
import com.example.improvevocabulary.presentation.lists.baseList.WordListFragment
import com.example.improvevocabulary.presentation.lists.pendingList.PendingWordAdapter
import com.example.improvevocabulary.utlis.TextToSpeech
import javax.inject.Inject

class StudiedListFragment : WordListFragment() {
    private lateinit var viewModel: StudiedListViewModel

    @Inject
    lateinit var viewModelFactory: StudiedListViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity?.applicationContext as App).appComponent.inject(this)
        viewModel = ViewModelProvider(this, viewModelFactory)[StudiedListViewModel::class.java]
        initAdapter(inflater, container)
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun initAdapter(inflater: LayoutInflater, container: ViewGroup?) {
        super.initAdapter(inflater, container)
        if (words.isNotEmpty()) return
        words.reverse()
        binding = FragmentWordListBinding.inflate(inflater, container, false)


        adapter = StudiedWordAdapter(tts, viewModel)
        binding.recyclerView.adapter = adapter
        viewModel.onStudyCount.observe(viewLifecycleOwner) {
            (adapter as StudiedWordAdapter).getOnStudyCount(viewModel.onStudyCount.value!!)
        }
    }
}