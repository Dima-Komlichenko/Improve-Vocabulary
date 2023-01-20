package com.example.improvevocabulary.presentation.lists.baseList

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.SimpleItemAnimator
import com.example.domain.model.PressedSortButton
import com.example.improvevocabulary.R
import com.example.improvevocabulary.databinding.FragmentWordListBinding
import com.example.improvevocabulary.models.WordPair
import com.example.improvevocabulary.presentation.add.AddViewModel
import com.example.improvevocabulary.presentation.filter.FilterViewModel
import com.example.improvevocabulary.presentation.search.SearchViewModel
import com.example.improvevocabulary.presentation.wordsFragment.WordListInfo
import com.example.improvevocabulary.utlis.TextToSpeech
import kotlinx.coroutines.*


open class WordListFragment : Fragment() {

    protected lateinit var binding: FragmentWordListBinding

    protected val filterViewModel: FilterViewModel by activityViewModels()
    protected val searchViewModel: SearchViewModel by activityViewModels()
    protected open val wordListViewModel: WordListViewModel by activityViewModels()
    protected open val addViewModel: AddViewModel by activityViewModels()
    protected lateinit var adapter: WordAdapter
    protected var words: ArrayList<WordPair> = ArrayList()
    protected lateinit var tts: TextToSpeech

    protected open fun initAdapter(inflater: LayoutInflater, container: ViewGroup?) {
        wordListViewModel.words.observe(viewLifecycleOwner) {
            if (adapter.itemCount == 0) {
                words = wordListViewModel.words.value!!
                adapter.initWordsUpdateFlag(wordListViewModel.isEmptyList)
                adapter.init(words, filterViewModel.pressedSortButton.value!!)
            }

        }
        wordListViewModel.init()

        CoroutineScope(SupervisorJob() + Dispatchers.Default).launch(Job()) {
            var flag = false
            val lang = wordListViewModel.getLanguageOfLearningUseCase.execute()
            while (!flag) {
                if(wordListViewModel.tts.isLanguageAvailable(lang) != -2) flag = true
            }
            wordListViewModel.tts.setLanguage(wordListViewModel.getLanguageOfLearningUseCase.execute())
        }

        tts = wordListViewModel.tts
        setListOrEmpty()

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        (binding.recyclerView.itemAnimator as SimpleItemAnimator).supportsChangeAnimations = false

        filterViewModel.pressedSortButton.observe(viewLifecycleOwner) {
            words = adapter.getList()
            val sortedList = adapter.sortByFilter(it)
            adapter.sort(sortedList)

        }

        searchViewModel.searchingWord.observe(viewLifecycleOwner) {
            adapter.setNewList(words)
            val searchingText = searchViewModel.searchingWord.value
            val foundedWords = arrayListOf<WordPair>()

            foundedWords.addAll(
                words.filter { word ->
                    word.word.lowercase().contains(searchingText!!)
                            || word.translate.lowercase().contains(searchingText)
                })
            adapter.setNewList(foundedWords)
            //


        }

        wordListViewModel.words.observe(viewLifecycleOwner) {
            adapter.sortByFilter(filterViewModel.pressedSortButton.value!!)
            wordListViewModel.isEmptyList.value = wordListViewModel.words.value!!.isEmpty()
        }

        Log.i("wordListPerform", "WordListFragment onCreateView() finish")
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        tts.destroy()
    }

    private fun setListOrEmpty() {
        wordListViewModel.isEmptyList.observe(viewLifecycleOwner) {
            if (it) {

                when (wordListViewModel.wordListInfo.value) {
                    WordListInfo.OnStudy -> {
                        binding.tvEmptyListInfoStart.text =
                            getString(R.string.empty_list_on_study_start)
                        binding.tvEmptyListInfoEnd.text =
                            getString(R.string.empty_list_on_study_end)
                    }
                    WordListInfo.Studied -> {
                        binding.tvEmptyListInfoStart.text =
                            getString(R.string.empty_list_studied_start)
                        binding.tvEmptyListInfoEnd.text = getString(R.string.empty_list_studied_end)
                    }
                    WordListInfo.Pending -> {
                        binding.tvEmptyListInfoStart.text =
                            getString(R.string.empty_list_pending_start)
                        binding.tvEmptyListInfoEnd.text = getString(R.string.empty_list_pending_end)
                    }
                    else -> ({}).toString()
                }

                binding.recyclerView.visibility = View.GONE
                binding.clEmptyList.visibility = View.VISIBLE
            } else {
                binding.recyclerView.visibility = View.VISIBLE
                binding.clEmptyList.visibility = View.GONE
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.recyclerView.adapter = null // for tts.destroy() in WordAdapter
    }


}

