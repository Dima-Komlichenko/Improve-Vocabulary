package com.example.improvevocabulary.presentation.lists.baseList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.SimpleItemAnimator
import com.example.domain.models.PressedSortButton
import com.example.improvevocabulary.databinding.FragmentWordListBinding
import com.example.improvevocabulary.models.WordPair
import com.example.improvevocabulary.presentation.filter.FilterViewModel
import com.example.improvevocabulary.presentation.search.SearchViewModel
import com.example.improvevocabulary.utlis.TextToSpeech


open class WordListFragment : Fragment() {

    protected lateinit var binding: FragmentWordListBinding

    protected val filterViewModel: FilterViewModel by activityViewModels()
    protected val searchViewModel: SearchViewModel by activityViewModels()

    protected lateinit var adapter : WordAdapter
    protected var words: ArrayList<WordPair> = ArrayList()
    protected val tts: TextToSpeech by lazy {
            TextToSpeech(requireContext())
    }


    protected open fun initAdapter(inflater: LayoutInflater, container: ViewGroup?) {}

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        (binding.recyclerView.itemAnimator as SimpleItemAnimator).supportsChangeAnimations = false

        filterViewModel.pressedSortButton.observe(viewLifecycleOwner) {
            words = adapter.getList()
            val sortedList = sortByFilter()
            adapter.sort(sortedList)
        }

        searchViewModel.searchingWord.observe(viewLifecycleOwner) {
            adapter.setNewList(words)
            val searchingText = searchViewModel.searchingWord.value
            val foundedWords = arrayListOf<WordPair>()


            foundedWords.addAll(
                words.filter { word ->
                    word.word.lowercase().contains(searchingText!!)
                            || word.translate.lowercase().contains(searchingText!!)
                })
            adapter.setNewList(foundedWords)
        }



        return binding.root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        binding.recyclerView.adapter = null // for tts.destroy() in WordAdapter
    }

    private fun sortByFilter(): ArrayList<WordPair> {
        val sortedList = when (filterViewModel.pressedSortButton.value) {
            PressedSortButton.ALPHABETICALLY -> sortByAlphabetically()
            PressedSortButton.NON_ALPHABETICALLY -> sortByNonAlphabetically()
            PressedSortButton.NEWER -> sortByNewer()
            PressedSortButton.OLDER -> sortByOlder()
            else -> sortByNewer()
        }
        return sortedList
    }

    private fun sortByAlphabetically(): ArrayList<WordPair> {
        if (words.isEmpty()) return words
        var sortedList = words.toList()
        sortedList = sortedList.sortedBy { it.word }
        return sortedList.toList() as ArrayList<WordPair>
    }

    private fun sortByNonAlphabetically(): ArrayList<WordPair> {
        if (words.isEmpty()) return words
        var sortedList = words.toList()
        sortedList = sortedList.sortedBy { it.word }
        return sortedList.reversed() as ArrayList<WordPair>
    }

    private fun sortByNewer(): ArrayList<WordPair> {
        if (words.isEmpty()) return words
        var sortedList = words.toList()
        sortedList = sortedList.sortedBy { it.id }
        return (sortedList.toList() as ArrayList<WordPair>)
    }

    private fun sortByOlder(): ArrayList<WordPair> {
        if (words.isEmpty()) return words
        var sortedList = words.toList()
        sortedList = sortedList.sortedBy { it.id }
        return (sortedList.reversed() as ArrayList<WordPair>)
    }
}

















