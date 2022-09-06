package com.example.improvevocabulary.presentation.wordList

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.improvevocabulary.R
import com.example.improvevocabulary.app.App
import com.example.improvevocabulary.presentation.add.AddViewModel
import com.example.improvevocabulary.presentation.filter.FilterViewModel
import com.example.improvevocabulary.presentation.filter.FilterViewModelFactory
import com.example.improvevocabulary.presentation.search.SearchViewModel
import javax.inject.Inject

class WordsActivity : AppCompatActivity() {

    private lateinit var filterViewModel: FilterViewModel
    private lateinit var searcViewModel: SearchViewModel
    private lateinit var addViewModel: AddViewModel


    @Inject
    lateinit var filterViewModelFactory: FilterViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_words)

        (applicationContext as App).appComponent.inject(this)
        filterViewModel = ViewModelProvider(this, filterViewModelFactory)[FilterViewModel::class.java]
        filterViewModel.load()

        addViewModel = ViewModelProvider(this)[AddViewModel::class.java]

        searcViewModel = ViewModelProvider(this)[SearchViewModel::class.java]
    }
}