package com.example.improvevocabulary.presentation.lists.onStudyList

import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import com.example.improvevocabulary.R
import com.example.improvevocabulary.databinding.OnStudyWordItemBinding
import com.example.improvevocabulary.databinding.WordItemBinding
import com.example.improvevocabulary.presentation.lists.baseList.WordAdapter
import com.example.improvevocabulary.presentation.lists.baseList.WordPair
import com.example.improvevocabulary.presentation.wordsFragment.WordListInfo
import com.example.improvevocabulary.utlis.TextToSpeech

class OnStudyWordAdapter(): WordAdapter() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnStudyWordHolder {
         val view = LayoutInflater.from(parent.context).inflate(R.layout.on_study_word_item, parent, false)
         context = parent.context
         return  OnStudyWordHolder(view)
    }

    override fun onBindViewHolder(holder: WordHolder, position: Int) { // вызывает bind холдера
        (holder as OnStudyWordHolder).bind(words[position])
    }

    inner class OnStudyWordHolder(override var item: View) : WordHolder(item) {

         override var binding = WordItemBinding.bind(item)
         var bindingOnStudy = OnStudyWordItemBinding.bind(item)

        override fun bind(word: WordPair) = with(bindingOnStudy) {
            super.bind(word)

            if (word.countRightAnswers > 9)  isOpportunityTransferWord.visibility = View.VISIBLE

            btnSave.setOnClickListener {
                editWord(word, tvWord.text.toString(), tvTranslate.text.toString())
                tvWord.text = word.word
                etWord.setText(word.word)
                tvTranslate.setText(word.translate)
            }
        }

        override fun showCardDetails() = with(bindingOnStudy) {
            tvTranslate.setText(wordPair.translate)
            tvTranslate.visibility = View.VISIBLE
            dividingLine.visibility = View.VISIBLE
            btnRemove.visibility = View.VISIBLE
            btnMove.visibility = View.VISIBLE
            animateView(btnSound, 65F, 0F, 0F, 0F)

            etWord.setText(wordPair.word)
            textChangingHandlerToShowCardDetails(etWord, wordPair.word)
            textChangingHandlerToShowCardDetails(tvTranslate, wordPair.translate)
            tvWord.visibility = View.GONE
            etWord.visibility = View.VISIBLE
            setImageBtnMove()
            setIsOpportunityTransferWordToShowCardDetails()
            setConstraintsToShowCardDetails()
        }

        override fun setConstraintsToShowCardDetails() = with(bindingOnStudy) {
            super.setConstraintsToShowCardDetails()
            ConstraintSet().apply {
                clone(clWordView)

                //etWord
                clear(etWord.id, ConstraintSet.BOTTOM)
                connect(etWord.id, ConstraintSet.BOTTOM, dividingLine.id, ConstraintSet.TOP)

                //isOpportunityTransferWord
                clear(isOpportunityTransferWord.id, ConstraintSet.TOP)
                clear(isOpportunityTransferWord.id, ConstraintSet.BOTTOM)
                clear(isOpportunityTransferWord.id, ConstraintSet.LEFT)
                clear(isOpportunityTransferWord.id, ConstraintSet.RIGHT)
                connect(isOpportunityTransferWord.id, ConstraintSet.TOP, dividingLine.id, ConstraintSet.TOP)
                connect(isOpportunityTransferWord.id, ConstraintSet.BOTTOM, dividingLine.id, ConstraintSet.BOTTOM)
                connect(isOpportunityTransferWord.id, ConstraintSet.LEFT, clWordView.id, ConstraintSet.LEFT)
                connect(isOpportunityTransferWord.id, ConstraintSet.RIGHT, dividingLine.id, ConstraintSet.LEFT)
            }.applyTo(clWordView)
        }

        override fun hideCardDetails() = with(binding) {
            tvTranslate.visibility = View.GONE
            dividingLine.visibility = View.GONE
            btnRemove.visibility = View.GONE
            btnMove.visibility = View.GONE
            btnSave.visibility = View.GONE
            animateView(btnSound, -65F, 0F, 0F, 0F)


            tvWord.visibility = View.VISIBLE
            bindingOnStudy.etWord.visibility = View.GONE
            setIsOpportunityTransferWordToHideCardDetails()
            setConstraintsToHideCardDetails()
        }

        override fun setConstraintsToHideCardDetails() = with(bindingOnStudy) {
            super.setConstraintsToHideCardDetails()
            ConstraintSet().apply {
                clone(clWordView)

                //etWord
                clear(tvWord.id, ConstraintSet.TOP)
                clear(tvWord.id, ConstraintSet.BOTTOM)
                connect(tvWord.id, ConstraintSet.TOP, binding.clWordView.id, ConstraintSet.TOP)
                connect(tvWord.id, ConstraintSet.BOTTOM,  binding.clWordView.id, ConstraintSet.BOTTOM)

                //isOpportunityTransferWord
                clear(isOpportunityTransferWord.id, ConstraintSet.TOP)
                clear(isOpportunityTransferWord.id, ConstraintSet.BOTTOM)
                clear(isOpportunityTransferWord.id, ConstraintSet.LEFT)
                clear(isOpportunityTransferWord.id, ConstraintSet.RIGHT)
                connect(isOpportunityTransferWord.id, ConstraintSet.TOP, clWordView.id, ConstraintSet.TOP)
                connect(isOpportunityTransferWord.id, ConstraintSet.BOTTOM, clWordView.id, ConstraintSet.BOTTOM)
                connect(isOpportunityTransferWord.id, ConstraintSet.LEFT, clWordView.id, ConstraintSet.LEFT)
                connect(isOpportunityTransferWord.id, ConstraintSet.RIGHT, tvWord.id, ConstraintSet.LEFT)
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
            animateView(isOpportunityTransferWord, 0F, 0F, -50F, 0F)
            val layoutParams = ConstraintLayout.LayoutParams(15, 60)
            isOpportunityTransferWord.layoutParams = layoutParams
            isOpportunityTransferWord.setImageResource(R.drawable.ic_btn_words_message_long)
        }

        private fun setIsOpportunityTransferWordToHideCardDetails() = with(bindingOnStudy) {
            animateView(isOpportunityTransferWord, 0F, 0F, 50F, 0F)
            val layoutParams = ConstraintLayout.LayoutParams(20, 20)
            isOpportunityTransferWord.layoutParams = layoutParams
            isOpportunityTransferWord.setImageResource(R.drawable.ic_btn_words_message)
        }

        protected fun textChangingHandlerToShowCardDetails(editText: EditText, word: String) =
            with(bindingOnStudy) {
                editText.addTextChangedListener(object : TextWatcher {
                    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

                    override fun afterTextChanged(s: Editable?) {
                        if (wordPair.word == etWord.text.toString() && wordPair.translate == tvTranslate.text.toString()) {
                            animateView(btnSound, 65F, 0F, 0F, 0F)

                            btnSave.visibility = View.GONE

                            ConstraintSet().apply {
                                clone(clWordView)
                                clear(btnSound.id, ConstraintSet.START)
                                connect(btnSound.id, ConstraintSet.LEFT, dividingLine.id, ConstraintSet.RIGHT)
                            }.applyTo(clWordView)
                        }
                    }

                    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                        if (btnSave.visibility == View.VISIBLE) return
                        animateView(btnSound, -65F, 0F, 0F, 0F)

                        if(editText.text.toString() == word) return
                        btnSave.visibility = View.VISIBLE
                        ConstraintSet().apply {
                            clone(clWordView)
                            clear(btnSound.id, ConstraintSet.START)
                            clear(btnSound.id, ConstraintSet.END)
                            connect(btnSound.id, ConstraintSet.LEFT, btnSave.id, ConstraintSet.RIGHT, 12)
                            connect(btnSound.id, ConstraintSet.RIGHT, clWordView.id, ConstraintSet.RIGHT, 12)
                        }.applyTo(clWordView)
                    }
                })
            }
    }
}