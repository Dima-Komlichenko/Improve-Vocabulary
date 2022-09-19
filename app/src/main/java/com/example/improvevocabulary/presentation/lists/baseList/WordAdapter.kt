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
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.improvevocabulary.R
import com.example.improvevocabulary.databinding.WordItemBinding
import com.example.improvevocabulary.presentation.wordsFragment.WordListInfo
import com.example.improvevocabulary.utlis.TextToSpeech
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


open class WordAdapter :
    RecyclerView.Adapter<WordAdapter.WordHolder>() {

    protected var words = ArrayList<WordPair>()
    protected var context: Context? = null
    private lateinit var tts: TextToSpeech

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): WordHolder { // создает холдера
        val view = LayoutInflater.from(parent.context).inflate(R.layout.word_item, parent, false)
        context = parent.context

        //tts = TextToSpeech(context!!)

        return WordHolder(view)
    }

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        super.onDetachedFromRecyclerView(recyclerView)
        //tts.destroy()
    }

    override fun onBindViewHolder(holder: WordHolder, position: Int) { // вызывает bind холдера
        holder.bind(words[position])
    }

    override fun getItemCount(): Int {
        return words.size
    }


    open inner class WordHolder(open var item: View) :
        RecyclerView.ViewHolder(item) {



        protected open lateinit var binding: WordItemBinding/* = WordItemBinding.bind(item)*/
        private var areItemDetailsShown = false
        protected lateinit var wordPair: WordPair


        open fun bind(word: WordPair) = with(binding) {
            wordPair = word

            tvWord.text = word.word
            if(0 == word.id) Log.d("time", "WordHolder binding finished")
            //cvItem.setOnClickListener { setCardForm() }
            //tvWord.onFocusChangeListener = View.OnFocusChangeListener { _, _ -> setCardForm() }
//
            //btnRemove.setOnClickListener {
//
            //    val index = getWordPairIndexById(wordPair.id)
            //    removeWord(word.id)
//
            //    Snackbar.make(
            //        item.parent as RecyclerView,
            //        context!!.resources.getString(R.string.snack_bar_removing_word) + " \"" + wordPair.word + "\"",
            //        Snackbar.LENGTH_SHORT or Snackbar.LENGTH_INDEFINITE
            //    )
            //        .setAction(context!!.resources.getString(R.string.undo)) {
            //            addWordAtPosition(wordPair, index)
            //        }.show()
//
            //}
//
//
            //btnSound.setOnClickListener {
            //    //var tts = TextToSpeech(context!!)
            //    //tts.setText(tvWord.text.toString())
            //    //tts.onInit(SUCCESS)
            //}
//
            //if (wordPair.countRightAnswers > 9)
            //    btnMove.setOnClickListener {
//
            //        val index = getWordPairIndexById(wordPair.id)
            //        moveWordToAnotherList(wordPair)
//
            //        Snackbar.make(
            //            item.parent as RecyclerView,
            //            context!!.resources.getString(R.string.snack_bar_moving_word_first_part) + " \"" + wordPair.word + "\" "
            //                    + context!!.resources.getString(R.string.snack_bar_moving_word_last_part),
            //            Snackbar.LENGTH_SHORT or Snackbar.LENGTH_INDEFINITE
            //        )
            //            .setAction(context!!.resources.getString(R.string.undo)) {
            //                addWordAtPosition(wordPair, index)
            //                //TODO: remove WordPair from studiedWordList
            //            }.show()
            //    }
        }


        private fun setCardForm() {
            if (areItemDetailsShown) {
                hideCardDetails()
                areItemDetailsShown = false
            } else {
                showCardDetails()
                areItemDetailsShown = true
            }
        }

        protected open fun showCardDetails() = with(binding) {
            tvTranslate.text = wordPair.translate
            tvTranslate.visibility = VISIBLE

            animateView(btnSound, 65F, 0F, 0F, 0F)

            dividingLine.visibility = VISIBLE
            btnRemove.visibility = VISIBLE
            btnMove.visibility = VISIBLE

            setConstraintsToShowCardDetails()
        }

        protected fun animateView(view: View, fromXDelta: Float, toXDelta: Float, fromYDelta: Float, toYDelta: Float) {
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
                clear(tvWord.id, ConstraintSet.BOTTOM)
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
            tvTranslate.visibility = View.GONE
            dividingLine.visibility = View.GONE
            btnRemove.visibility = View.GONE
            btnMove.visibility = View.GONE
            btnSave.visibility = View.GONE

            animateView(btnSound, -65F, 0F, 0F, 0F)
            setConstraintsToHideCardDetails()
        }


        protected open fun setConstraintsToHideCardDetails() = with(binding) {
            ConstraintSet().apply {
                clone(clWordView)

                //tvWord
                clear(tvWord.id, ConstraintSet.TOP)
                clear(tvWord.id, ConstraintSet.BOTTOM)
                connect(tvWord.id, ConstraintSet.TOP, clWordView.id, ConstraintSet.TOP)
                connect(tvWord.id, ConstraintSet.BOTTOM, clWordView.id, ConstraintSet.BOTTOM)

                //btnSound
                clear(btnSound.id, ConstraintSet.TOP)
                clear(btnSound.id, ConstraintSet.BOTTOM)
                clear(btnSound.id, ConstraintSet.LEFT)
                clear(btnSound.id, ConstraintSet.RIGHT)
                connect(btnSound.id, ConstraintSet.TOP, clWordView.id, ConstraintSet.TOP, 10)
                connect(btnSound.id, ConstraintSet.BOTTOM, clWordView.id, ConstraintSet.BOTTOM, 10)
                connect(btnSound.id, ConstraintSet.RIGHT, clWordView.id, ConstraintSet.RIGHT, 10)
            }.applyTo(clWordView)
        }

    }


    fun init(words: ArrayList<WordPair>) {
        words.forEach { addWord(it) }
    }

    fun moveWordToAnotherList(word: WordPair) {
        removeWord(word.id)
        //TODO: move studiedWord to studiedWordList
    }

    fun addWord(word: WordPair) {
        val diffUtil = MyDiffUtil(words, words + word)
        val diffResult = DiffUtil.calculateDiff(diffUtil)
        words.add(word)
        diffResult.dispatchUpdatesTo(this)
    }

    fun addWordAtPosition(word: WordPair, index: Int) {
        val wordsclone = (words.clone() as ArrayList<WordPair>).apply { add(index, word) }
        val diffUtil = MyDiffUtil(words, wordsclone)
        val diffResult = DiffUtil.calculateDiff(diffUtil)
        words.add(index, word)
        diffResult.dispatchUpdatesTo(this)
    }

    fun getWordPairIndexById(id: Int): Int {
        return words.indexOf(words.find { word -> word.id == id })
    }

    fun removeWord(id: Int): Int {
        val index = words.indexOf(words.find { word -> word.id == id })

        val newList = arrayListOf<WordPair>()
        words.forEach { word ->
            if (word.id != id)
                newList.add(word)
        }
        val diffUtil = MyDiffUtil(words, newList)
        val diffResult = DiffUtil.calculateDiff(diffUtil)
        words.remove(words.find { word -> word.id == id })
        diffResult.dispatchUpdatesTo(this)

        return index
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