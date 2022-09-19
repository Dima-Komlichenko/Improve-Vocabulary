package com.example.improvevocabulary.presentation.wordList

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.example.improvevocabulary.R
import com.example.improvevocabulary.app.App
import com.example.improvevocabulary.presentation.add.AddViewModel
import com.example.improvevocabulary.presentation.filter.FilterViewModel
import com.example.improvevocabulary.presentation.filter.FilterViewModelFactory
import com.example.improvevocabulary.presentation.lists.baseList.WordListViewModel
import com.example.improvevocabulary.presentation.lists.onStudyList.OnStudyListFragment
import com.example.improvevocabulary.presentation.lists.practiceList.StudiedListFragment
import com.example.improvevocabulary.presentation.search.SearchViewModel
import com.example.improvevocabulary.presentation.wordsFragment.WordListInfo
import com.example.improvevocabulary.presentation.wordsFragment.WordListInfoConst
import java.util.*
import javax.inject.Inject

class WordsActivity : AppCompatActivity() {

    private lateinit var filterViewModel: FilterViewModel
    private lateinit var searcViewModel: SearchViewModel
    private lateinit var addViewModel: AddViewModel
    private lateinit var wordListViewModel: WordListViewModel

    @Inject
    lateinit var filterViewModelFactory: FilterViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_words)
        (applicationContext as App).appComponent.inject(this)
        filterViewModel = ViewModelProvider(this, filterViewModelFactory)[FilterViewModel::class.java]
        addViewModel = ViewModelProvider(this)[AddViewModel::class.java]
        searcViewModel = ViewModelProvider(this)[SearchViewModel::class.java]
        wordListViewModel = ViewModelProvider(this)[WordListViewModel::class.java]
        filterViewModel.load()

        wordListViewModel.wordListInfo.value = intent.getSerializableExtra(WordListInfoConst) as WordListInfo

        when(wordListViewModel.wordListInfo.value) {
            WordListInfo.OnStudy -> {
                supportFragmentManager.beginTransaction()
                    .add(R.id.fcv_word_list, OnStudyListFragment())
                    .commit()
            }
            WordListInfo.Practice -> {
                supportFragmentManager.beginTransaction()
                   .add(R.id.fcv_word_list, StudiedListFragment())
                   .commit()
            }
            else -> {}
        }



    }
}