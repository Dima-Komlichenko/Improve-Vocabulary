package com.example.improvevocabulary.presentation.wordList

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.example.improvevocabulary.R
import com.example.improvevocabulary.databinding.WordItemBinding

data class WordPair(var id: Int, var word: String, var translate: String)

class WordAdapter: RecyclerView.Adapter<WordAdapter.WordHolder>() {

    private val words = ArrayList<WordPair>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordHolder { // создает холдера
        val view = LayoutInflater.from(parent.context).inflate(R.layout.word_item, parent, false)
        return WordHolder(view)
    }

    override fun onBindViewHolder(holder: WordHolder, position: Int) { // вызывает bind холдера
        holder.bind(words[position])
    }

    override fun getItemCount(): Int {
        return words.size
    }

    // принимает каждый вью и заполняет его по шаблону
    inner class WordHolder(item: View): RecyclerView.ViewHolder(item) {

        private val binding = WordItemBinding.bind(item) // уже есть view, поэтому не надо делать inflate

        fun bind(word: WordPair) = with(binding) { // with позволяет не писать binding в каждой строке а как бы добавляет его в неймспейс метода
            /*im.setImageResource(word.imageId)
            tvTitle.text = word.title*/
            tvWord.text = word.word
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
    ): DiffUtil.Callback() {

        override fun getOldListSize(): Int = oldList.size
        override fun getNewListSize(): Int =newList.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition].id == newList[newItemPosition].id
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList == newList //for DataClass
        }

    }
}