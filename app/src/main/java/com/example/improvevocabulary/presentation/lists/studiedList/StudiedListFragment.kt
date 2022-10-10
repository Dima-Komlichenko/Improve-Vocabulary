package com.example.improvevocabulary.presentation.lists.studiedList

import android.app.Application
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.data.storage.repositoriesImpl.WordPairRepository
import com.example.domain.usecase.onStudy.RemoveOnStudyWordPairUseCase
import com.example.domain.usecase.onStudy.SaveOnStudyWordPairUseCase
import com.example.domain.usecase.pending.RemovePendingWordPairUseCase
import com.example.domain.usecase.pending.UpdatePendingWordPairUseCase
import com.example.domain.usecase.studied.RemoveStudiedWordPairUseCase
import com.example.domain.usecase.studied.SaveStudiedWordPairUseCase
import com.example.domain.usecase.studied.UpdateStudiedWordPairUseCase
import com.example.improvevocabulary.databinding.FragmentWordListBinding
import com.example.improvevocabulary.models.WordPair
import com.example.improvevocabulary.presentation.lists.baseList.WordListFragment

class StudiedListFragment : WordListFragment() {

    private lateinit var removeStudiedWordPairUseCase: RemoveStudiedWordPairUseCase
    private lateinit var saveOnStudyWordPairUseCase: SaveOnStudyWordPairUseCase
    private lateinit var saveStudiedWordPairUseCase: SaveStudiedWordPairUseCase
    private lateinit var removeOnStudyWordPairUseCase: RemoveOnStudyWordPairUseCase

    private var repository = WordPairRepository(Application())

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        initAdapter(inflater, container)
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun initAdapter(inflater: LayoutInflater, container: ViewGroup?) {
        super.initAdapter(inflater, container)
        if(words.isNotEmpty()) return
        binding = FragmentWordListBinding.inflate(inflater, container, false)

        removeStudiedWordPairUseCase = RemoveStudiedWordPairUseCase(repository)
        saveOnStudyWordPairUseCase = SaveOnStudyWordPairUseCase(repository)
        saveStudiedWordPairUseCase = SaveStudiedWordPairUseCase(repository)
        removeOnStudyWordPairUseCase = RemoveOnStudyWordPairUseCase(repository)

        adapter = StudiedWordAdapter(tts,
            removeStudiedWordPairUseCase,
            saveOnStudyWordPairUseCase,
            saveStudiedWordPairUseCase,
            removeOnStudyWordPairUseCase
        )
        binding.recyclerView.adapter = adapter
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
                WordPair(17, "Enviropment", "Окружающая среда", 10),
                WordPair(18, "Reusability", "Возможность повторного переиспользования", 2)
            )
        )
    }
}