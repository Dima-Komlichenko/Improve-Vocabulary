package com.example.improvevocabulary.presentation.wordList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.improvevocabulary.R
import com.example.improvevocabulary.databinding.WordItemBinding


data class WordPair(var id: Int, var word: String, var translate: String)

class WordAdapter : RecyclerView.Adapter<WordAdapter.WordHolder>() {

    private val words = ArrayList<WordPair>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): WordHolder { // создает холдера
        val view = LayoutInflater.from(parent.context).inflate(R.layout.word_item, parent, false)
        return WordHolder(view)
    }

    override fun onBindViewHolder(holder: WordHolder, position: Int) { // вызывает bind холдера
        holder.bind(words[position])
    }

    override fun getItemCount(): Int {
        return words.size
    }


    inner class WordHolder(item: View) : RecyclerView.ViewHolder(item) {

        private val binding = WordItemBinding.bind(item)

        fun bind(word: WordPair) = with(binding) {
            tvWord.text = word.word
            tvTranslate.text = word.translate

            clWordView.setOnClickListener {
                //TODO: set TextViews to EditTexts
                //TODO: show count right answers instead of image if it's less than 10
                //TODO: show btnSave if user is editing any word
                //TODO: make "is_opportunity_transfer_word" longer
                //TODO: by pressed hide back extended view

                dividingLine.visibility = View.VISIBLE
                tvTranslate.visibility = View.VISIBLE
                btnRemove.visibility = View.VISIBLE
                btnMove.visibility = View.VISIBLE

                ConstraintSet().apply {
                    clone(clWordView)
                    //tvWord
                    clear(tvWord.id, ConstraintSet.BOTTOM)
                    clear(tvWord.id, ConstraintSet.TOP)
                    connect(tvWord.id, ConstraintSet.BOTTOM, dividingLine.id, ConstraintSet.TOP, 12)
                    connect(tvWord.id, ConstraintSet.TOP, it.id, ConstraintSet.TOP, 12)
                    //btnSound
                    clear(btnSound.id, ConstraintSet.TOP)
                    clear(btnSound.id, ConstraintSet.BOTTOM)
                    clear(btnSound.id, ConstraintSet.START)
                    clear(btnSound.id, ConstraintSet.END)
                    connect(btnSound.id, ConstraintSet.TOP, tvWord.id, ConstraintSet.TOP)
                    connect(btnSound.id, ConstraintSet.BOTTOM, tvWord.id, ConstraintSet.BOTTOM)
                    connect(btnSound.id, ConstraintSet.RIGHT, it.id, ConstraintSet.RIGHT)
                    connect(btnSound.id, ConstraintSet.LEFT, dividingLine.id, ConstraintSet.RIGHT)
                    setHorizontalBias(btnSound.id, 0.5F)
                }.applyTo(it as ConstraintLayout?)
            }
        }
    }

    fun init(words: ArrayList<WordPair>) {
        words.forEach { addWord(it) }
    }

    fun addWord(word: WordPair) {
        val diffUtil = MyDiffUtil(words, words + word)
        val diffResult = DiffUtil.calculateDiff(diffUtil)
        words.add(word)
        diffResult.dispatchUpdatesTo(this)
    }

    class MyDiffUtil(
        private val oldList: List<WordPair>,
        private val newList: List<WordPair>
    ) : DiffUtil.Callback() {

        override fun getOldListSize(): Int = oldList.size
        override fun getNewListSize(): Int = newList.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition].id == newList[newItemPosition].id
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList == newList //for DataClass
        }

    }
}