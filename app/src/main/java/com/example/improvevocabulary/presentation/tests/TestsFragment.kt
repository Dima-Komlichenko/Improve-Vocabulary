package com.example.improvevocabulary.presentation.tests

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.util.Pair
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.improvevocabulary.R
import com.example.improvevocabulary.app.App
import com.example.improvevocabulary.databinding.FragmentTestsBinding
import com.example.improvevocabulary.presentation.test.TestActivity
import com.example.improvevocabulary.presentation.wordList.WordsActivity
import com.example.improvevocabulary.presentation.wordsFragment.WordListInfo
import com.example.improvevocabulary.presentation.wordsFragment.WordListInfoConst
import com.example.improvevocabulary.presentation.wordsFragment.WordsFragmentViewModelFactory
import soup.neumorphism.NeumorphCardView
import javax.inject.Inject

enum class TypeOfTestInfo { Test, Practice, Repetition }

const val TypeOfTestInfoConst = "TypeOfTestInfoConst"

class TestsFragment : Fragment() {

    private lateinit var binding: FragmentTestsBinding
    private lateinit var viewModel: TestsViewModel
    @Inject
    lateinit var testsViewModelFactory: TestsViewModelFactory

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentTestsBinding.inflate(inflater, container, false)
        (activity?.applicationContext as App).appComponent.inject(this)
        viewModel = ViewModelProvider(this, testsViewModelFactory)[TestsViewModel::class.java]

        viewModel.onStudyCount.observe(viewLifecycleOwner) {
            binding.tvTestCount.text = it.toString()
        }
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        viewModel.init()
        setOnClickListener(binding.btnTest, TypeOfTestInfo.Test)
        setOnClickListener(binding.btnPractice!!, TypeOfTestInfo.Practice)
        binding.tvTestCount.text = viewModel.onStudyCount.value?.toString()
    }

    private fun setOnClickListener(view: NeumorphCardView, typeOfTestInfo: TypeOfTestInfo) {
        view.setOnClickListener {
            view.setOnClickListener {  }
            val intent = Intent(activity, TestActivity::class.java)
            val options = ActivityOptions.makeSceneTransitionAnimation(
                activity,
                Pair(binding.root.rootView.findViewById(R.id.logo), "logo")
            )
            intent.putExtra(TypeOfTestInfoConst, typeOfTestInfo)
            startActivity(intent, options.toBundle())
        }
    }
}