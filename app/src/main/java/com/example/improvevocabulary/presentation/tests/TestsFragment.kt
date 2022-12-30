package com.example.improvevocabulary.presentation.tests

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.util.Pair
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.improvevocabulary.R
import com.example.improvevocabulary.app.App
import com.example.improvevocabulary.databinding.FragmentTestsBinding
import com.example.improvevocabulary.presentation.test.TestActivity
import com.google.android.material.snackbar.Snackbar
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

        viewModel.studiedCount.observe(viewLifecycleOwner) {
            binding.tvTestCount.text = viewModel.onStudyCount.value!!.toString()
            setOnClickListener(binding.btnTest, TypeOfTestInfo.Test, viewModel.onStudyCount.value!!)
            setOnClickListener(binding.btnPractice, TypeOfTestInfo.Practice, viewModel.onStudyCount.value!! + viewModel.studiedCount.value!!)
        }
        viewModel.init()
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        viewModel.init()
    }

    private fun setOnClickListener(view: NeumorphCardView, typeOfTestInfo: TypeOfTestInfo, wordsCount: Int) {
        view.setOnClickListener {
            view.setOnClickListener {  }
            if(wordsCount < 5) {
                Snackbar.make(
                    binding.root,
                    resources.getString(R.string.unable_start_test),
                    Snackbar.LENGTH_SHORT or Snackbar.LENGTH_INDEFINITE
                )
                    .show()
                return@setOnClickListener
            }
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