package com.example.improvevocabulary.presentation.wordsFragment

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
import com.example.improvevocabulary.databinding.FragmentWordsBinding
import com.example.improvevocabulary.presentation.wordList.WordsActivity
import soup.neumorphism.NeumorphCardView
import javax.inject.Inject

const val WordListInfoConst = "WordListInfo"

enum class WordListInfo {
    Pending, OnStudy, Studied
}

class WordsFragment : Fragment() {

    private lateinit var binding: FragmentWordsBinding
    private lateinit var viewModel: WordsFragmentViewModel

    @Inject
    lateinit var wordsFragmentViewModelFactory: WordsFragmentViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentWordsBinding.inflate(inflater, container, false)

        (activity?.applicationContext as App).appComponent.inject(this)
        viewModel =
            ViewModelProvider(
                this,
                wordsFragmentViewModelFactory
            )[WordsFragmentViewModel::class.java]

        viewModel.init()

        viewModel.onStudyCount.observe(viewLifecycleOwner) {
            binding.tvOnStudyCount.text = it.toString()
        }

        viewModel.pendingCount.observe(viewLifecycleOwner) {
            binding.tvPedningCount!!.text = it.toString()
        }

        viewModel.studiedCount.observe(viewLifecycleOwner) {
            binding.tvStudiedCount.text = it.toString()
        }

        viewModel.isOnStudyListContainsStudiedWords.observe(viewLifecycleOwner) {
            viewModel.isOnStudyListContainsStudiedWords.value?.let {
                if (it)
                    binding.isOpportunityTransferWord.visibility = View.VISIBLE
            }
        }

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        viewModel.init()
        setOnClickListener(binding.btnPending!!, WordListInfo.Pending)
        setOnClickListener(binding.btnOnStudy, WordListInfo.OnStudy)
        setOnClickListener(binding.btnStudied, WordListInfo.Studied)

        binding.tvOnStudyCount.text = viewModel.onStudyCount.value?.toString()
        binding.tvPedningCount!!.text = viewModel.pendingCount.value?.toString()
        binding.tvStudiedCount.text = viewModel.studiedCount.value?.toString()

        viewModel.isOnStudyListContainsStudiedWords.value?.let {
            binding.isOpportunityTransferWord.visibility =
                if (it) View.VISIBLE else View.GONE
        }
    }


    private fun setOnClickListener(view: NeumorphCardView, wordListInfo: WordListInfo) {
        view.setOnClickListener {
            view.setOnClickListener { }
            val intent = Intent(activity, WordsActivity::class.java)
            val options = ActivityOptions.makeSceneTransitionAnimation(
                activity,
                Pair(binding.root.rootView.findViewById(R.id.logo), "logo")
            )
            intent.putExtra(WordListInfoConst, wordListInfo)
            startActivity(intent, options.toBundle())
        }
    }

}






















