package com.example.improvevocabulary.presentation.test

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.data.storage.repositoriesImpl.WordPairRepository
import com.example.domain.model.OnStudyWordPair
import com.example.domain.model.StudiedWordPair
import com.example.domain.usecase.onStudy.GetOnStudyWordPairsUseCase
import com.example.domain.usecase.studied.GetStudiedWordPairsUseCase
import com.example.improvevocabulary.app.App
import com.example.improvevocabulary.databinding.ActivityTestBinding
import com.example.improvevocabulary.models.WordPair
import com.example.improvevocabulary.presentation.add.AddViewModel
import com.example.improvevocabulary.presentation.filter.FilterViewModelFactory
import com.example.improvevocabulary.presentation.lists.baseList.WordListViewModelFactory
import com.example.improvevocabulary.presentation.tests.TypeOfTestInfo
import com.example.improvevocabulary.presentation.tests.TypeOfTestInfoConst
import kotlinx.coroutines.launch
import javax.inject.Inject

class TestActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTestBinding
    private lateinit var viewModel: TestViewModel

    @Inject
    lateinit var testViewModelFactory: TestViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityTestBinding.inflate(layoutInflater)

        (applicationContext as App).appComponent.inject(this)
        viewModel = ViewModelProvider(this, testViewModelFactory)[TestViewModel::class.java]

        setContentView(binding.root)

        viewModel.typeOfTestInfo = intent.getSerializableExtra(TypeOfTestInfoConst) as TypeOfTestInfo
    }
}