package com.example.improvevocabulary.presentation.wordList

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.improvevocabulary.R
import com.example.improvevocabulary.app.App
import com.example.improvevocabulary.databinding.ActivityWordsBinding
import com.example.improvevocabulary.presentation.add.AddViewModel
import com.example.improvevocabulary.presentation.add.AddViewModelFactory
import com.example.improvevocabulary.presentation.filter.FilterViewModel
import com.example.improvevocabulary.presentation.filter.FilterViewModelFactory
import com.example.improvevocabulary.presentation.lists.listHeader.ListHeaderViewModel
import com.example.improvevocabulary.presentation.lists.listHeader.PressedFilterButton
import com.example.improvevocabulary.presentation.lists.baseList.WordListFragment
import com.example.improvevocabulary.presentation.lists.baseList.WordListViewModel
import com.example.improvevocabulary.presentation.lists.baseList.WordListViewModelFactory
import com.example.improvevocabulary.presentation.lists.onStudyList.OnStudyListFragment
import com.example.improvevocabulary.presentation.lists.pendingList.PendingListFragment
import com.example.improvevocabulary.presentation.lists.studiedList.StudiedListFragment
import com.example.improvevocabulary.presentation.search.SearchViewModel
import com.example.improvevocabulary.presentation.test.TypeOfList
import com.example.improvevocabulary.presentation.test.TypeOfListConst
import com.example.improvevocabulary.presentation.wordsFragment.WordListInfo
import com.example.improvevocabulary.presentation.wordsFragment.WordListInfoConst
import javax.inject.Inject

enum class WordsActivityResult {
    Back, RestartTest
}

class WordsActivity : AppCompatActivity() {

    private lateinit var filterViewModel: FilterViewModel
    private lateinit var searchViewModel: SearchViewModel
    private lateinit var addViewModel: AddViewModel

    private lateinit var wordListViewModel: WordListViewModel
    private lateinit var listHeaderViewModel: ListHeaderViewModel

    @Inject
    lateinit var filterViewModelFactory: FilterViewModelFactory

    @Inject
    lateinit var wordListViewModelFactory: WordListViewModelFactory

    @Inject
    lateinit var addViewModelFactory: AddViewModelFactory

    private lateinit var binding: ActivityWordsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWordsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        (applicationContext as App).appComponent.inject(this)
        filterViewModel =
            ViewModelProvider(this, filterViewModelFactory)[FilterViewModel::class.java]
        addViewModel = ViewModelProvider(this, addViewModelFactory)[AddViewModel::class.java]
        searchViewModel = ViewModelProvider(this)[SearchViewModel::class.java]
        listHeaderViewModel = ViewModelProvider(this)[ListHeaderViewModel::class.java]

        wordListViewModel =
            ViewModelProvider(this, wordListViewModelFactory)[WordListViewModel::class.java]

        filterViewModel.load()

        wordListViewModel.wordListInfo.value =
            intent.getSerializableExtra(WordListInfoConst) as WordListInfo

        wordListViewModel.typeOfList.value =
            (intent.getSerializableExtra(TypeOfListConst) as TypeOfList)


        showList()

        val intent = Intent().apply { putExtra("result", WordsActivityResult.Back) }
        setResult(1, intent)

        listHeaderViewModel.pressedFilterButtonId.observe(this) {
            if(it == PressedFilterButton.REPEAT_BTN) {
                val intent = Intent().apply { putExtra("result", WordsActivityResult.RestartTest) }
                setResult(1, intent)
                onBackPressed()
            }
        }
    }


    private fun showList() {
        when (wordListViewModel.wordListInfo.value) {
            WordListInfo.Pending -> {
                addViewModel.initListType("PendingListFragment")
                setExtraFragment(PendingListFragment())
            }
            WordListInfo.OnStudy -> {
                addViewModel.initListType("OnStudyListFragment")
                setExtraFragment(OnStudyListFragment())
            }
            WordListInfo.Studied -> {
                addViewModel.initListType("StudiedListFragment")
                setExtraFragment(StudiedListFragment())
            }
            else -> {}
        }
    }


    private fun setExtraFragment(fragment: WordListFragment) {
        val currentFragment =
            supportFragmentManager.findFragmentById(binding.fcvWordList.id)

        if (currentFragment != null) {

            if (wordListViewModel.words.value!!.isEmpty())
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fcv_word_list, fragment).commit()
            else
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fcv_word_list, fragment).commit()

        } else {
            supportFragmentManager.beginTransaction()
                .add(R.id.fcv_word_list, fragment).commit()
        }
    }
}