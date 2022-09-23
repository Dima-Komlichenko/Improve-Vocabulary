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

class OnStudyWordAdapter(private val tts: TextToSpeech): WordAdapter(tts) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnStudyWordHolder {
         val view = LayoutInflater.from(parent.context).inflate(R.layout.on_study_word_item, parent, false)
         context = parent.context
         return OnStudyWordHolder(view, tts)
    }

    inner class OnStudyWordHolder(override var item: View, tts: TextToSpeech) : WordHolder(item, tts) {

         override var binding = WordItemBinding.bind(item)
         var bindingOnStudy = OnStudyWordItemBinding.bind(item)
         var textWatchers: ArrayList<TextWatcher> = arrayListOf()

        override fun bind(word: WordPair) = with(bindingOnStudy) {

            if(textWatchers.isNotEmpty()) {
                etWord.removeTextChangedListener(textWatchers[0])
                tvTranslate.removeTextChangedListener(textWatchers[1])
                textWatchers.clear()
            } //TODO: возможно это фигня полная

            super.bind(word)
            //etWord.setText(word.word)
            //tvTranslate.setText(word.translate)
            // TODO: возмжно понадобится если при перерисовке вью текст не будет совпадать
            if (word.countRightAnswers > 9)  isOpportunityTransferWord.visibility = View.VISIBLE

            btnSave.setOnClickListener {
                editWord(word, etWord.text.toString(), tvTranslate.text.toString())
                tvWord.text = word.word
                Snackbar.make(
                    item.parent as RecyclerView,
                    "The word " + " \"" + wordPair.word + "\" "
                            + " is saved",
                    Snackbar.LENGTH_SHORT or Snackbar.LENGTH_INDEFINITE
                ).show()
                //etWord.setText(word.word)
                //tvTranslate.setText(word.translate)
                //TOD: возможно понадобится если текст не будет корректно отображаться после сохранения
            }
        }

        //TODO: метод который будет лазить в бд и обновлять список, вызываем его при поиске
        // (возможно, а возможно после всех изменений слов просто их сохранять в бд сразу и обновлять списки)

        override fun showCardDetails() = with(bindingOnStudy) {
            //tvWord.visibility = View.GONE
            //etWord.visibility = View.VISIBLE
            //tvTranslate.visibility = View.VISIBLE
            //dividingLine.visibility = View.VISIBLE
            //btnRemove.visibility = View.VISIBLE
            //btnMove.visibility = View.VISIBLE
//
            //etWord.setText(wordPair.word)
            //tvTranslate.setText(wordPair.translate)
//
            //textChangingHandlerToShowCardDetails(etWord, wordPair.word)
            //textChangingHandlerToShowCardDetails(tvTranslate, wordPair.translate)
//
            //
            //setImageBtnMove()
            //setConstraintsToShowCardDetails()

            etWord.setText(wordPair.word)
            tvTranslate.setText(wordPair.translate)
            tvTranslate.visibility = View.VISIBLE
            etWord.visibility = View.VISIBLE
            dividingLine.visibility = View.VISIBLE
            btnRemove.visibility = View.VISIBLE
            btnMove.visibility = View.VISIBLE

            tvTranslate.setOnClickListener {
                hideCardDetailsAnimated()
                wordPair.areItemDetailsShown = false
            }

            setConstraintsToShowCardDetails()

            areItemDetailsShown = true
        }

        override fun showCardDetailsAnimated() {
            setIsOpportunityTransferWordToShowCardDetails()
            showCardDetails()
            animateView(binding.btnSound, 65F, 0F, 0F, 0F)

        }

        override fun setConstraintsToShowCardDetails() = with(bindingOnStudy) {
            super.setConstraintsToShowCardDetails()
            ConstraintSet().apply {
                clone(clWordView)

                //isOpportunityTransferWord
                clear(isOpportunityTransferWord.id, ConstraintSet.TOP)
                clear(isOpportunityTransferWord.id, ConstraintSet.BOTTOM)
                clear(isOpportunityTransferWord.id, ConstraintSet.START)
                clear(isOpportunityTransferWord.id, ConstraintSet.END)
                connect(isOpportunityTransferWord.id, ConstraintSet.TOP, clWordView.id, ConstraintSet.TOP)
                connect(isOpportunityTransferWord.id, ConstraintSet.BOTTOM, clWordView.id, ConstraintSet.BOTTOM)
                connect(isOpportunityTransferWord.id, ConstraintSet.LEFT, clWordView.id, ConstraintSet.LEFT)
                connect(isOpportunityTransferWord.id, ConstraintSet.RIGHT, dividingLine.id, ConstraintSet.LEFT)
            }.applyTo(clWordView)
        }

        override fun hideCardDetails() = with(bindingOnStudy) {
            tvTranslate.visibility = View.GONE
            dividingLine.visibility = View.GONE
            btnRemove.visibility = View.GONE
            btnMove.visibility = View.GONE
            btnSave.visibility = View.GONE
            tvWord.visibility = View.VISIBLE
            etWord.visibility = View.GONE

            setIsOpportunityTransferWordToHideCardDetails()
            setConstraintsToHideCardDetails()
        }



        override fun setConstraintsToHideCardDetails() = with(bindingOnStudy) {
            super.setConstraintsToHideCardDetails()
            ConstraintSet().apply {
                clone(binding.clWordView)

                //etWord
                clear(tvWord.id, ConstraintSet.TOP)
                clear(tvWord.id, ConstraintSet.BOTTOM)
                connect(tvWord.id, ConstraintSet.TOP, clWordView.id, ConstraintSet.TOP)
                connect(tvWord.id, ConstraintSet.BOTTOM,  clWordView.id, ConstraintSet.BOTTOM)

                //isOpportunityTransferWord
                clear(isOpportunityTransferWord.id, ConstraintSet.TOP)
                clear(isOpportunityTransferWord.id, ConstraintSet.BOTTOM)
                clear(isOpportunityTransferWord.id, ConstraintSet.START)
                clear(isOpportunityTransferWord.id, ConstraintSet.END)
                connect(isOpportunityTransferWord.id, ConstraintSet.TOP, clWordView.id, ConstraintSet.TOP)
                connect(isOpportunityTransferWord.id, ConstraintSet.BOTTOM, clWordView.id, ConstraintSet.BOTTOM)
                connect(isOpportunityTransferWord.id, ConstraintSet.START, clWordView.id, ConstraintSet.START)
                connect(isOpportunityTransferWord.id, ConstraintSet.END, tvWord.id, ConstraintSet.START)
            }.applyTo(binding.clWordView)
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
            if(wordPair.countRightAnswers < 10) return
            animateView(isOpportunityTransferWord, 0F, 0F, -50F, 0F)
            val layoutParams = ConstraintLayout.LayoutParams(15, 60)
            isOpportunityTransferWord.layoutParams = layoutParams
            isOpportunityTransferWord.setImageResource(R.drawable.ic_btn_words_message_long)
        }

        private fun setIsOpportunityTransferWordToHideCardDetails() = with(bindingOnStudy) {
            if(wordPair.countRightAnswers < 10) return
            animateView(isOpportunityTransferWord, 0F, 0F, 50F, 0F)
            val layoutParams = ConstraintLayout.LayoutParams(20, 20)
            isOpportunityTransferWord.layoutParams = layoutParams
            isOpportunityTransferWord.setImageResource(R.drawable.ic_btn_words_message)
        }

        protected fun textChangingHandlerToShowCardDetails(editText: EditText, word: String) =
            with(bindingOnStudy) {
                    var textWatcher = object : TextWatcher {

                    private var wasTextChanged: Boolean= false

                    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

                    override fun afterTextChanged(s: Editable?) {
                        if (wordPair.word == etWord.text.toString() && wordPair.translate == tvTranslate.text.toString()) {
                            if(!wasTextChanged) return

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

                        if(editText.text.toString() == word) return
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