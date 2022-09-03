package com.example.improvevocabulary.presentation.wordList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SimpleItemAnimator
import com.example.improvevocabulary.R
import com.example.improvevocabulary.databinding.FragmentWordListBinding
import com.google.android.material.snackbar.Snackbar


class WordListFragment : Fragment() {
    private lateinit var binding: FragmentWordListBinding
    private val adapter = WordAdapter()
    private val words = arrayListOf(
        WordPair(0, "Aware", "Осведомленный", 0),
        WordPair(1, "Reduce", "Уменьшать", 7),
        WordPair(2, "Impact", "Влияние", 10),
        WordPair(3, "Complexity", "Сложность", 5),
        WordPair(4, "Reusability", "Возможность повторного переиспользования", 2),
        WordPair(5, "Eliminate", "Исключить", 10),
        WordPair(6, "Redundant", "Избыточный", 10),
        WordPair(7, "Statement", "Утверждение, оператор", 4),
        WordPair(8, "Implicit", "Скрытый", 0),
        WordPair(9, "Sandbox", "Песочница", 9)
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWordListBinding.inflate(inflater, container, false)
        binding.recyclerView.adapter = adapter
        adapter.init(words)
        (binding.recyclerView.itemAnimator as SimpleItemAnimator).supportsChangeAnimations = false

        val swipeGesture = object : SwipeGesture(context) {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {

                val wordPair = (viewHolder as WordAdapter.WordHolder).getWordPair()
                val index = adapter.getWordPairIndexById(wordPair.id)
                when(direction) {
                    ItemTouchHelper.LEFT -> {
                        adapter.removeWord(wordPair.id)
                        Snackbar.make(binding.recyclerView, resources.getString(R.string.snack_bar_removing_word) + " \"" + wordPair.word + "\"" , Snackbar.LENGTH_SHORT or Snackbar.LENGTH_INDEFINITE)
                            .setAction(resources.getString(R.string.undo)) {
                                adapter.addWordAtPosition(wordPair, index)
                            }.show()
                    }

                    ItemTouchHelper.RIGHT -> {
                        val studiedWord = arrayListOf(wordPair.id)
                        //TODO: move studiedWord to studiedWordList
                        adapter.removeWord(wordPair.id)
                        Snackbar.make(binding.recyclerView, resources.getString(R.string.snack_bar_moving_word_first_part) + " \"" + wordPair.word + "\" " + resources.getString(R.string.snack_bar_moving_word_last_part), Snackbar.LENGTH_SHORT or Snackbar.LENGTH_INDEFINITE)
                            .setAction(resources.getString(R.string.undo)) {
                                adapter.addWordAtPosition(wordPair, index)
                                //TODO: remove WordPair from studiedWordList
                            }.show()

                    }
                }

            }
        }

        val touchHelper = ItemTouchHelper(swipeGesture)
        touchHelper.attachToRecyclerView(binding.recyclerView)

        return binding.root
    }
}

















