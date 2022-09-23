package com.example.improvevocabulary.presentation.wordList

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.improvevocabulary.R
import com.example.improvevocabulary.app.App
import com.example.improvevocabulary.databinding.ActivityWordsBinding
import com.example.improvevocabulary.presentation.add.AddViewModel
import com.example.improvevocabulary.presentation.filter.FilterViewModel
import com.example.improvevocabulary.presentation.filter.FilterViewModelFactory
import com.example.improvevocabulary.presentation.lists.baseList.WordListViewModel
import com.example.improvevocabulary.presentation.lists.onStudyList.OnStudyListFragment
import com.example.improvevocabulary.presentation.lists.studiedList.StudiedListFragment
import com.example.improvevocabulary.presentation.search.SearchViewModel
import com.example.improvevocabulary.presentation.wordsFragment.WordListInfo
import com.example.improvevocabulary.presentation.wordsFragment.WordListInfoConst
import javax.inject.Inject

class WordsActivity : AppCompatActivity() {

    private lateinit var filterViewModel: FilterViewModel
    private lateinit var searcViewModel: SearchViewModel
    private lateinit var addViewModel: AddViewModel

    @Inject
    lateinit var filterViewModelFactory: FilterViewModelFactory

    private lateinit var wordListViewModel: WordListViewModel

    private lateinit var binding: ActivityWordsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_words)

        binding = ActivityWordsBinding.inflate(layoutInflater)

        (applicationContext as App).appComponent.inject(this)
        filterViewModel =
            ViewModelProvider(this, filterViewModelFactory)[FilterViewModel::class.java]
        addViewModel = ViewModelProvider(this)[AddViewModel::class.java]
        searcViewModel = ViewModelProvider(this)[SearchViewModel::class.java]
        wordListViewModel = ViewModelProvider(this)[WordListViewModel::class.java]
        filterViewModel.load()


        wordListViewModel.wordListInfo.value =
            intent.getSerializableExtra(WordListInfoConst) as WordListInfo

        when (wordListViewModel.wordListInfo.value) {
            WordListInfo.OnStudy -> {
                val currentFragment =
                    supportFragmentManager.findFragmentById(binding.fcvWordList.id)

                if (currentFragment != null) {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fcv_word_list, OnStudyListFragment())
                        .commit()
                } else {
                    supportFragmentManager.beginTransaction()
                        .add(R.id.fcv_word_list, OnStudyListFragment())
                        .commit()
                }

            }
            WordListInfo.Studied -> {
                val currentFragment =
                    supportFragmentManager.findFragmentById(binding.fcvWordList.id)

                if (currentFragment != null) {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fcv_word_list, StudiedListFragment())
                        .commit()
                } else {
                    supportFragmentManager.beginTransaction()
                        .add(R.id.fcv_word_list, StudiedListFragment())
                        .commit()
                }

            }
            else -> {}
        }
    }
}