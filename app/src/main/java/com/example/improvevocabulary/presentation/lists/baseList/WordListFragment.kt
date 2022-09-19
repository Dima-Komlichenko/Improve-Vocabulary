package com.example.improvevocabulary.presentation.lists.baseList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SimpleItemAnimator
import com.example.domain.models.PressedSortButton
import com.example.improvevocabulary.R
import com.example.improvevocabulary.databinding.FragmentWordListBinding
import com.example.improvevocabulary.presentation.add.AddViewModel
import com.example.improvevocabulary.presentation.filter.FilterViewModel
import com.example.improvevocabulary.presentation.search.SearchViewModel
import com.example.improvevocabulary.presentation.wordList.SwipeGesture
import com.example.improvevocabulary.presentation.wordsFragment.WordListInfo
import com.google.android.material.snackbar.Snackbar

data class WordPair(
    var id: Int,
    var word: String,
    var translate: String,
    var countRightAnswers: Int = 0
)


open class WordListFragment : Fragment() {

    protected lateinit var binding: FragmentWordListBinding

    protected val filterViewModel: FilterViewModel by activityViewModels()
    protected val searchViewModel: SearchViewModel by activityViewModels()

    protected lateinit var  adapter: WordAdapter
    protected val words: ArrayList<WordPair> = ArrayList()



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        filterViewModel.pressedSortButton.observe(viewLifecycleOwner) {
            var sortedList = sortByFilter()
            adapter.sort(sortedList)
        }

        searchViewModel.searchingWord.observe(viewLifecycleOwner) {
            var searchingText = searchViewModel.searchingWord.value
            var foundedWords = arrayListOf<WordPair>()

            foundedWords.addAll(
                words.filter { word ->
                    word.word.lowercase().contains(searchingText!!)
                            || word.translate.lowercase().contains(searchingText!!)
                })


            adapter.setNewList(foundedWords)
            (binding.recyclerView.itemAnimator as SimpleItemAnimator).supportsChangeAnimations = false
        }

        //val swipeGesture = object : SwipeGesture(context) {

            //override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {

                //val wordPair = (viewHolder as WordAdapter.WordHolder).getWordPair()
                //val index = adapter.getWordPairIndexById(wordPair.id)
                //when (direction) {
                //    ItemTouchHelper.LEFT -> {
                //        adapter.removeWord(wordPair.id)
                //        Snackbar.make(
                //            binding.recyclerView,
                //            resources.getString(R.string.snack_bar_removing_word) + " \"" + wordPair.word + "\"",
                //            Snackbar.LENGTH_SHORT or Snackbar.LENGTH_INDEFINITE
                //        )
                //            .setAction(resources.getString(R.string.undo)) {
                //                adapter.addWordAtPosition(wordPair, index)
                //            }.show()
                //    }

                //    ItemTouchHelper.RIGHT -> {

                //        val studiedWord = arrayListOf(wordPair.id)
                //        //TODO: move studiedWord to studiedWordList
                //        adapter.removeWord(wordPair.id)
                //        Snackbar.make(
                //            binding.recyclerView,
                //            resources.getString(R.string.snack_bar_moving_word_first_part) + " \"" + wordPair.word + "\" " + resources.getString(
                //                R.string.snack_bar_moving_word_last_part
                //            ),
                //            Snackbar.LENGTH_SHORT or Snackbar.LENGTH_INDEFINITE
                //        )
                //            .setAction(resources.getString(R.string.undo)) {
                //                adapter.addWordAtPosition(wordPair, index)
                //                //TODO: remove WordPair from studiedWordList
                //            }.show()


                //    }
                //}

            //}
        //}

        //val touchHelper = ItemTouchHelper(swipeGesture)
        //touchHelper.attachToRecyclerView(binding.recyclerView)



        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.recyclerView.adapter = null
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

















