package com.example.improvevocabulary.presentation.lists.onStudyList

import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.recyclerview.widget.RecyclerView
import com.example.improvevocabulary.R
import com.example.improvevocabulary.databinding.OnStudyWordItemBinding
import com.example.improvevocabulary.databinding.WordItemBinding
import com.example.improvevocabulary.presentation.lists.baseList.WordAdapter
import com.example.improvevocabulary.presentation.lists.baseList.WordPair
import com.example.improvevocabulary.utlis.TextToSpeech
import com.google.android.material.snackbar.Snackbar

class OnStudyWordAdapter(private val tts: TextToSpeech) : WordAdapter(tts) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnStudyWordHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.on_study_word_item, parent, false)
        context = parent.context
        return OnStudyWordHolder(view, tts)
    }

    inner class OnStudyWordHolder(override var item: View, tts: TextToSpeech) :
        WordHolder(item, tts) {

        override var binding = WordItemBinding.bind(item)
        private var bindingOnStudy = OnStudyWordItemBinding.bind(item)
        var textWatchers: ArrayList<TextWatcher> = arrayListOf()

        override fun bind(word: WordPair) = with(bindingOnStudy) {

            if (textWatchers.isNotEmpty()) {
                etWord.removeTextChangedListener(textWatchers[0])
                tvTranslate.removeTextChangedListener(textWatchers[1])
                textWatchers.clear()
            }

            super.bind(word)

            etWord.setText(word.word)
            tvTranslate.setText(word.translate)

            textChangingHandlerToShowCardDetails(etWord, wordPair.word)
            textChangingHandlerToShowCardDetails(tvTranslate, wordPair.translate)



            if (!word.areItemDetailsShown && areItemDetailsShown) {
                setIsOpportunityTransferWordToHideCardDetails()
            }

            if(areItemDetailsShown && word.areItemDetailsShown && word.countRightAnswers > 9) {
                //TODO: вынести переиспользуемую логику в 2 метода
                val layoutParams = ConstraintLayout.LayoutParams(17, 60)
                isOpportunityTransferWord.layoutParams = layoutParams
                isOpportunityTransferWord.setImageResource(R.drawable.ic_btn_words_message_long)
                ConstraintSet().apply {
                    clone(clWordView)
                    //isOpportunityTransferWord
                    clear(isOpportunityTransferWord.id, ConstraintSet.TOP)
                    clear(isOpportunityTransferWord.id, ConstraintSet.BOTTOM)
                    clear(isOpportunityTransferWord.id, ConstraintSet.START)
                    clear(isOpportunityTransferWord.id, ConstraintSet.END)
                    connect(isOpportunityTransferWord.id, ConstraintSet.TOP, clWordView.id, ConstraintSet.TOP)
                    connect(isOpportunityTransferWord.id, ConstraintSet.BOTTOM, clWordView.id, ConstraintSet.BOTTOM)
                    connect(isOpportunityTransferWord.id, ConstraintSet.START, clWordView.id, ConstraintSet.START)
                    connect(isOpportunityTransferWord.id, ConstraintSet.END, dividingLine.id, ConstraintSet.START)
                }.applyTo(clWordView)
            }

            setImageBtnMove()


            if (wordPair.countRightAnswers > 9)
                isOpportunityTransferWord.visibility = View.VISIBLE
            else
                isOpportunityTransferWord.visibility = View.GONE

            btnSave.setOnClickListener {
                editWord(word, etWord.text.toString(), tvTranslate.text.toString())
                tvWord.text = word.word
                Snackbar.make(
                    item.parent as RecyclerView,
                    "The word " + " \"" + wordPair.word + "\" "
                            + " is saved",
                    Snackbar.LENGTH_SHORT or Snackbar.LENGTH_INDEFINITE
                ).show()
            }
        }

        //TODO: метод который будет лазить в бд и обновлять список, вызываем его при поиске
        // (возможно, а возможно после всех изменений слов просто их сохранять в бд сразу и обновлять списки)

        override fun showCardDetails() = with(bindingOnStudy) {
            super.showCardDetails()
            tvWord.visibility = View.GONE
            etWord.visibility = View.VISIBLE

            tvTranslate.setOnClickListener {} // delete listener from base adapter so that don't close card on click


        }

        override fun showCardDetailsAnimated() {
            setIsOpportunityTransferWordToShowCardDetails()
            super.showCardDetailsAnimated()
        }

        override fun setConstraintsToShowCardDetails() = with(bindingOnStudy) {
            super.setConstraintsToShowCardDetails()
            ConstraintSet().apply {
                clone(clWordView)

                //etWord
                clear(etWord.id, ConstraintSet.TOP)
                clear(etWord.id, ConstraintSet.BOTTOM)
                connect(etWord.id, ConstraintSet.TOP, clWordView.id, ConstraintSet.TOP, 52)
                connect(etWord.id, ConstraintSet.BOTTOM, dividingLine.id, ConstraintSet.TOP)
                //isOpportunityTransferWord
                clear(isOpportunityTransferWord.id, ConstraintSet.TOP)
                clear(isOpportunityTransferWord.id, ConstraintSet.BOTTOM)
                clear(isOpportunityTransferWord.id, ConstraintSet.START)
                clear(isOpportunityTransferWord.id, ConstraintSet.END)
                connect(isOpportunityTransferWord.id, ConstraintSet.TOP, clWordView.id, ConstraintSet.TOP)
                connect(isOpportunityTransferWord.id, ConstraintSet.BOTTOM, clWordView.id, ConstraintSet.BOTTOM)
                connect(isOpportunityTransferWord.id, ConstraintSet.START, clWordView.id, ConstraintSet.START)
                connect(isOpportunityTransferWord.id, ConstraintSet.END, dividingLine.id, ConstraintSet.START)
            }.applyTo(clWordView)
        }

        override fun hideCardDetails() = with(bindingOnStudy) {
            setIsOpportunityTransferWordToHideCardDetails()
            super.hideCardDetails()
            btnSave.visibility = View.GONE
            tvWord.visibility = View.VISIBLE
            etWord.visibility = View.GONE
        }

        override fun setConstraintsToHideCardDetails() = with(bindingOnStudy) {
            super.setConstraintsToHideCardDetails()
            ConstraintSet().apply {
                clone(clWordView)
                //isOpportunityTransferWord
                clear(isOpportunityTransferWord.id, ConstraintSet.TOP)
                clear(isOpportunityTransferWord.id, ConstraintSet.BOTTOM)
                clear(isOpportunityTransferWord.id, ConstraintSet.START)
                clear(isOpportunityTransferWord.id, ConstraintSet.END)
                connect(isOpportunityTransferWord.id, ConstraintSet.TOP, clWordView.id, ConstraintSet.TOP)
                connect(isOpportunityTransferWord.id, ConstraintSet.BOTTOM, clWordView.id, ConstraintSet.BOTTOM)
                connect(isOpportunityTransferWord.id, ConstraintSet.START, clWordView.id, ConstraintSet.START)
                connect(isOpportunityTransferWord.id, ConstraintSet.END, tvWord.id, ConstraintSet.START)
            }.applyTo(clWordView)
        }

        protected fun setImageBtnMove() = with(bindingOnStudy) {
            btnMove.setImageResource(
                when (wordPair.countRightAnswers) {
                    0 -> R.drawable.ic_0_from_10
                    1 -> R.drawable.ic_1_from_10
                    2 -> R.drawable.ic_2_from_10
                    3 -> R.drawable.ic_3_from_10
                    4 -> R.drawable.ic_4_from_10
                    5 -> R.drawable.ic_5_from_10
                    6 -> R.drawable.ic_6_from_10
                    7 -> R.drawable.ic_7_from_10
                    8 -> R.drawable.ic_8_from_10
                    9 -> R.drawable.ic_9_from_10
                    else -> R.drawable.ic_move
                }
            )
        }

        private fun setIsOpportunityTransferWordToShowCardDetails() = with(bindingOnStudy) {
            if (wordPair.countRightAnswers < 10) return
            animateView(isOpportunityTransferWord, 0F, 0F, -50F, 0F)
            val layoutParams = ConstraintLayout.LayoutParams(17, 60)
            isOpportunityTransferWord.layoutParams = layoutParams
            isOpportunityTransferWord.setImageResource(R.drawable.ic_btn_words_message_long)
        }

        private fun setIsOpportunityTransferWordToHideCardDetails() = with(bindingOnStudy) {
            if (wordPair.countRightAnswers < 10) return
            animateView(isOpportunityTransferWord, 0F, 0F, 50F, 0F)
            val layoutParams = ConstraintLayout.LayoutParams(23, 23)
            isOpportunityTransferWord.layoutParams = layoutParams
            isOpportunityTransferWord.setImageResource(R.drawable.ic_btn_words_message)
        }

        protected fun textChangingHandlerToShowCardDetails(editText: EditText, word: String) = with(bindingOnStudy) {
            val textWatcher = object : TextWatcher {

                private var wasTextChanged: Boolean = false

                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

                override fun afterTextChanged(s: Editable?) {
                    if (wordPair.word == etWord.text.toString() && wordPair.translate == tvTranslate.text.toString()) {
                        if (wasTextChanged) return

                        animateView(btnSound, 65F, 0F, 0F, 0F)

                        btnSave.visibility = View.GONE

                        ConstraintSet().apply {
                            clone(clWordView)
                            clear(btnSound.id, ConstraintSet.START)
                            connect(btnSound.id, ConstraintSet.LEFT, dividingLine.id, ConstraintSet.RIGHT)
                        }.applyTo(clWordView)
                        wasTextChanged = false
                    }
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    if (btnSave.visibility == View.VISIBLE) return

                    if (wordPair.word == etWord.text.toString() && wordPair.translate == tvTranslate.text.toString()) return

                    if (editText.text.toString() == word) return
                    animateView(btnSound, -65F, 0F, 0F, 0F)


                    btnSave.visibility = View.VISIBLE
                    ConstraintSet().apply {
                        clone(clWordView)
                        clear(btnSound.id, ConstraintSet.START)
                        clear(btnSound.id, ConstraintSet.END)
                        connect(btnSound.id, ConstraintSet.LEFT, btnSave.id, ConstraintSet.RIGHT, 12)
                        connect(btnSound.id, ConstraintSet.RIGHT, clWordView.id, ConstraintSet.RIGHT, 12)
                    }.applyTo(clWordView)

                    wasTextChanged = true
                }
            }
            textWatchers.add(textWatcher)
            editText.addTextChangedListener(textWatcher)
        }
    }
}