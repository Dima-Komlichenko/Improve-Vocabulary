package com.example.improvevocabulary.presentation.wordList

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.example.improvevocabulary.R
import com.example.improvevocabulary.app.App
import com.example.improvevocabulary.databinding.ActivityWordsBinding
import com.example.improvevocabulary.presentation.add.AddViewModel
import com.example.improvevocabulary.presentation.filter.FilterViewModel
import com.example.improvevocabulary.presentation.filter.FilterViewModelFactory
import com.example.improvevocabulary.presentation.lists.baseList.WordListFragment
import com.example.improvevocabulary.presentation.lists.baseList.WordListViewModel
import com.example.improvevocabulary.presentation.lists.baseList.WordListViewModelFactory
import com.example.improvevocabulary.presentation.lists.onStudyList.OnStudyListFragment
import com.example.improvevocabulary.presentation.lists.pendingList.PendingListFragment
import com.example.improvevocabulary.presentation.lists.studiedList.StudiedListFragment
import com.example.improvevocabulary.presentation.search.SearchViewModel
import com.example.improvevocabulary.presentation.wordsFragment.WordListInfo
import com.example.improvevocabulary.presentation.wordsFragment.WordListInfoConst
import javax.inject.Inject

class WordsActivity : AppCompatActivity() {

    private lateinit var filterViewModel: FilterViewModel
    private lateinit var searcViewModel: SearchViewModel
    private lateinit var addViewModel: AddViewModel

    private lateinit var wordListViewModel: WordListViewModel

    @Inject
    lateinit var filterViewModelFactory: FilterViewModelFactory

    @Inject
    lateinit var wordListViewModelFactory: WordListViewModelFactory



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

        wordListViewModel =
            ViewModelProvider(this, WordListViewModelFactory(application))[WordListViewModel::class.java]

        filterViewModel.load()

        wordListViewModel.wordListInfo.value =
            intent.getSerializableExtra(WordListInfoConst) as WordListInfo


        when (wordListViewModel.wordListInfo.value) {
            WordListInfo.Pending -> setExtraFragment(PendingListFragment())
            WordListInfo.OnStudy -> setExtraFragment(OnStudyListFragment())
            WordListInfo.Studied -> setExtraFragment(StudiedListFragment())
            else -> {}
        }
    }

    private fun setExtraFragment(fragment: WordListFragment) {
        val currentFragment =
            supportFragmentManager.findFragmentById(binding.fcvWordList.id)

        if (currentFragment != null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fcv_word_list, fragment).commit()
        } else {
            supportFragmentManager.beginTransaction()
                .add(R.id.fcv_word_list, fragment).commit()
        }
    }
}