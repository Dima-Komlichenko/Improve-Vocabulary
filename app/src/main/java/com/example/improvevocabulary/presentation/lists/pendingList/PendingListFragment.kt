package com.example.improvevocabulary.presentation.lists.pendingList

import android.app.Application
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.data.storage.repositoriesImpl.WordPairRepository
import com.example.domain.usecase.onStudy.RemoveOnStudyWordPairUseCase
import com.example.domain.usecase.onStudy.SaveOnStudyWordPairUseCase
import com.example.domain.usecase.pending.RemovePendingWordPairUseCase
import com.example.domain.usecase.pending.SavePendingWordPairUseCase
import com.example.domain.usecase.pending.UpdatePendingWordPairUseCase
import com.example.improvevocabulary.R
import com.example.improvevocabulary.databinding.FragmentWordListBinding
import com.example.improvevocabulary.models.WordPair
import com.example.improvevocabulary.presentation.add.AddViewModel
import com.example.improvevocabulary.presentation.lists.baseList.WordListFragment
import com.google.android.material.snackbar.Snackbar

class PendingListFragment: WordListFragment() {

    private val addViewModel: AddViewModel by activityViewModels()

    private lateinit var updatePendingWordPairUseCase: UpdatePendingWordPairUseCase
    private lateinit var removePendingWordPairUseCase: RemovePendingWordPairUseCase
    private lateinit var savePendingWordPairUseCase: SavePendingWordPairUseCase
    private lateinit var saveOnStudyWordPairUseCase: SaveOnStudyWordPairUseCase
    private lateinit var removeOnStudyWordPairUseCase: RemoveOnStudyWordPairUseCase

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

        updatePendingWordPairUseCase = UpdatePendingWordPairUseCase(repository)
        removePendingWordPairUseCase = RemovePendingWordPairUseCase(repository)
        savePendingWordPairUseCase = SavePendingWordPairUseCase(repository)
        saveOnStudyWordPairUseCase = SaveOnStudyWordPairUseCase(repository)
        removeOnStudyWordPairUseCase = RemoveOnStudyWordPairUseCase(repository)

        adapter = PendingWordAdapter(tts,
            updatePendingWordPairUseCase,
            removePendingWordPairUseCase,
            savePendingWordPairUseCase,
            saveOnStudyWordPairUseCase,
            removeOnStudyWordPairUseCase)
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
            wordListViewModel.save(newWordPair)

            Snackbar.make(
                binding.recyclerView,
                resources.getString(R.string.new_word_created) + " \"" + newWordPair.word + "\"",
                Snackbar.LENGTH_SHORT or Snackbar.LENGTH_INDEFINITE
            ).setAction(resources.getString(R.string.ok), null)
                .show()
        }
    }
    /*
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
    }*/
}