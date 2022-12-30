package com.example.improvevocabulary.presentation.lists.baseList

import android.content.Context
import android.speech.tts.TextToSpeech.SUCCESS
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.view.animation.TranslateAnimation
import androidx.constraintlayout.widget.ConstraintSet
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.model.Language
import com.example.improvevocabulary.R
import com.example.improvevocabulary.databinding.WordItemBinding
import com.example.improvevocabulary.models.WordPair
import com.example.improvevocabulary.utlis.TextToSpeech
import com.google.android.material.snackbar.Snackbar

open class WordAdapter(
    private val tts: TextToSpeech,
) :
    RecyclerView.Adapter<WordAdapter.WordHolder>() {

    protected var words = ArrayList<WordPair>()
    protected var context: Context? = null

    private lateinit var isEmptyList: MutableLiveData<Boolean>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.word_item, parent, false)
        return WordHolder(view, tts)
    }

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        super.onDetachedFromRecyclerView(recyclerView)
        tts.destroy()

    }

    fun initWordsUpdateFlag(isEmptyL: MutableLiveData<Boolean>) {
        isEmptyList = isEmptyL
    }

    override fun onBindViewHolder(holder: WordHolder, position: Int) {
        holder.bind(words[position])
    }

    override fun getItemCount(): Int {
        return words.size
    }


    open inner class WordHolder(open var item: View, private val tts: TextToSpeech) :
        RecyclerView.ViewHolder(item) {

        protected open lateinit var binding: WordItemBinding
        protected lateinit var wordPair: WordPair
        protected var areItemDetailsShown: Boolean = false

        open fun bind(word: WordPair) {
            wordPair = word

            binding = WordItemBinding.bind(item)

            if (!word.areItemDetailsShown && areItemDetailsShown) {
                hideCardDetails()
            }
            if (word.areItemDetailsShown && !areItemDetailsShown) {
                showCardDetails()
            }

            binding.tvWord.text = word.word

            binding.clWordView.setOnClickListener {
                setCardForm()
            }
            binding.tvWord.setOnClickListener {
                setCardForm()
            }
            binding.btnRemove.setOnClickListener {
                btnRemoveHandler()
            }

            tts.setVolume(0.8F)
            binding.btnSound.setOnClickListener {
                tts.setText(binding.tvWord.text.toString())
                tts.onInit(SUCCESS)
            }
        }

        protected open fun btnMoveHandler(): Int {
            val index = getWordPairIndexById(wordPair.id)
            removeWord(wordPair.id)
            return index
        }


        protected open fun undoMoveHandler(index: Int) {
            addWordAtPosition(wordPair, index)
        }

        protected fun btnRemoveHandler() {
            val index = getWordPairIndexById(wordPair.id)
            removeWord(wordPair.id)

            Snackbar.make(
                item.parent as RecyclerView,
                context!!.resources.getString(R.string.word_is_removed) + " \"" + wordPair.word + "\"",
                Snackbar.LENGTH_SHORT or Snackbar.LENGTH_INDEFINITE
            )
                .setAction(context!!.resources.getString(R.string.undo)) {
                    addWordAtPosition(wordPair, index)
                }
                .show()
        }

        private fun setCardForm() {
            if (wordPair.areItemDetailsShown) {
                hideCardDetailsAnimated()
                wordPair.areItemDetailsShown = false
            } else {
                showCardDetailsAnimated()
                wordPair.areItemDetailsShown = true
            }
        }

        protected open fun showCardDetails() = with(binding) {
            setConstraintsToShowCardDetails()
            tvTranslate.text = wordPair.translate
            tvTranslate.visibility = VISIBLE
            dividingLine.visibility = VISIBLE
            btnRemove.visibility = VISIBLE
            btnMove.visibility = VISIBLE

            tvTranslate.setOnClickListener {
                hideCardDetailsAnimated()
                wordPair.areItemDetailsShown = false
            }
            wordPair.areItemDetailsShown = true
            areItemDetailsShown = true
        }

        protected open fun showCardDetailsAnimated() {
            tts.setText(binding.tvWord.text.toString())
            tts.onInit(SUCCESS)
            showCardDetails()
            animateView(binding.btnSound, 65F, 0F, 0F, 0F)
        }

        protected fun animateView(
            view: View,
            fromXDelta: Float,
            toXDelta: Float,
            fromYDelta: Float,
            toYDelta: Float
        ) {
            val animation = TranslateAnimation(fromXDelta, toXDelta, fromYDelta, toYDelta).apply {
                duration = 200
                fillAfter = true
            }
            view.startAnimation(animation)
        }

        protected open fun setConstraintsToShowCardDetails() = with(binding) {
            ConstraintSet().apply {
                clone(clWordView)
                //tvWord
                clear(tvWord.id, ConstraintSet.TOP)
                clear(tvWord.id, ConstraintSet.BOTTOM)
                connect(tvWord.id, ConstraintSet.TOP, clWordView.id, ConstraintSet.TOP, 52)
                connect(tvWord.id, ConstraintSet.BOTTOM, dividingLine.id, ConstraintSet.TOP)
                //btnSound
                clear(btnSound.id, ConstraintSet.TOP)
                clear(btnSound.id, ConstraintSet.BOTTOM)
                clear(btnSound.id, ConstraintSet.START)
                clear(btnSound.id, ConstraintSet.END)
                connect(btnSound.id, ConstraintSet.TOP, tvWord.id, ConstraintSet.TOP)
                connect(btnSound.id, ConstraintSet.BOTTOM, tvWord.id, ConstraintSet.BOTTOM)
                connect(btnSound.id, ConstraintSet.RIGHT, btnMove.id, ConstraintSet.RIGHT)
                connect(btnSound.id, ConstraintSet.LEFT, btnRemove.id, ConstraintSet.LEFT)
            }.applyTo(clWordView)
        }


        protected open fun hideCardDetails() = with(binding) {
            tvTranslate.visibility = GONE
            dividingLine.visibility = GONE
            btnRemove.visibility = GONE
            btnMove.visibility = GONE
            setConstraintsToHideCardDetails()
            areItemDetailsShown = false
            wordPair.areItemDetailsShown = false
        }


        protected open fun hideCardDetailsAnimated() {
            hideCardDetails()
            animateView(binding.btnSound, -65F, 0F, 0F, 0F)

        }

        protected open fun setConstraintsToHideCardDetails() = with(binding) {
            ConstraintSet().apply {
                clone(clWordView)
                //tvWord
                clear(tvWord.id, ConstraintSet.TOP)
                clear(tvWord.id, ConstraintSet.BOTTOM)
                connect(tvWord.id, ConstraintSet.TOP, clWordView.id, ConstraintSet.TOP, 52)
                connect(tvWord.id, ConstraintSet.BOTTOM, clWordView.id, ConstraintSet.BOTTOM, 52)

                //btnSound
                clear(btnSound.id, ConstraintSet.TOP)
                clear(btnSound.id, ConstraintSet.BOTTOM)
                clear(btnSound.id, ConstraintSet.START)
                clear(btnSound.id, ConstraintSet.END)
                connect(btnSound.id, ConstraintSet.TOP, clWordView.id, ConstraintSet.TOP)
                connect(btnSound.id, ConstraintSet.BOTTOM, clWordView.id, ConstraintSet.BOTTOM)
                connect(btnSound.id, ConstraintSet.END, clWordView.id, ConstraintSet.END, 8)
            }.applyTo(clWordView)
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
        isEmptyList.value = words.isEmpty()
    }

    protected open fun addWordAtPosition(word: WordPair, index: Int) {
        val newList = (words.clone() as ArrayList<WordPair>).apply { add(index, word) }
        val diffUtil = MyDiffUtil(words, newList)
        val diffResult = DiffUtil.calculateDiff(diffUtil)
        words.add(index, word)
        diffResult.dispatchUpdatesTo(this)
        //переопределить с логикой бд
    }

    fun getWordPairIndexById(id: Int): Int {
        return words.indexOf(words.find { word -> word.id == id })
    }

    fun removeWord(id: Int): Int {
        val index = words.indexOf(words.find { word -> word.id == id })

        val newList = arrayListOf<WordPair>()
        newList.addAll(words.filter { word -> word.id != id })
        val diffUtil = MyDiffUtil(words, newList)
        val diffResult = DiffUtil.calculateDiff(diffUtil)
        words.remove(words.find { word -> word.id == id })

        diffResult.dispatchUpdatesTo(this)
        isEmptyList.value = words.isEmpty()
        return index
    }

    fun getList(): ArrayList<WordPair> {
        return words
    }

    fun editWord(word: WordPair, editedWord: String, editedTranslate: String) {
        word.word = editedWord
        word.translate = editedTranslate
    }

    fun sort(sortedList: ArrayList<WordPair>) {
        val diffUtil = MyDiffUtil(words, sortedList)
        val diffResult = DiffUtil.calculateDiff(diffUtil)
        words = sortedList
        diffResult.dispatchUpdatesTo(this)
    }

    fun setNewList(newList: ArrayList<WordPair>) {
        val diffUtil = MyDiffUtil(words, newList)
        val diffResult = DiffUtil.calculateDiff(diffUtil)
        words = newList
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