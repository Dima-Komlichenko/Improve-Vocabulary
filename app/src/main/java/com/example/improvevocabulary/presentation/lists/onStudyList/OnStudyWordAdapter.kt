package com.example.improvevocabulary.presentation.lists.onStudyList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.model.OnStudyWordPair
import com.example.domain.model.StudiedWordPair
import com.example.improvevocabulary.R
import com.example.improvevocabulary.databinding.OnStudyWordItemBinding
import com.example.improvevocabulary.models.WordPair
import com.example.improvevocabulary.presentation.add.AddViewModel
import com.example.improvevocabulary.presentation.lists.baseEditableList.EditableWordAdapter
import com.example.improvevocabulary.utlis.TextToSpeech
import com.google.android.material.snackbar.Snackbar

class   OnStudyWordAdapter(
    private val tts: TextToSpeech,
    val addViewModel: AddViewModel,
    val viewModel: OnStudyListViewModel,
) : EditableWordAdapter(tts, viewModel.languageFromLearning.value!!, viewModel.languageOfLearning.value!!) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnStudyWordHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.on_study_word_item, parent, false)
        context = parent.context
        return OnStudyWordHolder(view, tts)
    }

    inner class OnStudyWordHolder(override var item: View, tts: TextToSpeech) :
        EditableWordHolder(item, tts) {

        private var bindingOnStudy = OnStudyWordItemBinding.bind(item)

        override fun bind(word: WordPair) = with(bindingOnStudy) {
            super.bind(word)
            if (!word.areItemDetailsShown && areItemDetailsShown) {
                setIsOpportunityTransferWordToHideCardDetails()
            }


            if (areItemDetailsShown && word.areItemDetailsShown && word.countRightAnswers > 9) {
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


            if (wordPair.countRightAnswers > 9) {
                isOpportunityTransferWord.visibility = View.VISIBLE
                btnMove.setOnClickListener {
                    btnMoveHandler()
                    viewModel.removeOnStudyWordPairUseCase.execute(mapToOnStudy(wordPair))
                    viewModel.saveStudied(wordPair)
                }
            } else {
                isOpportunityTransferWord.visibility = View.GONE
                btnMove.setOnClickListener { }
            }

            btnSave.setOnClickListener {
                if (!btnSaveHandler()) return@setOnClickListener
                viewModel.updateOnStudyWordPairUseCase.execute(mapToOnStudy(wordPair))
            }

            btnRemove.setOnClickListener {
                btnRemoveHandler()
                viewModel.removeOnStudyWordPairUseCase.execute(mapToOnStudy(wordPair))
                addViewModel.updateOnStudyCount()
            }
        }

        override fun btnMoveHandler(): Int {
            val index = super.btnMoveHandler()

            Snackbar.make(
                item.parent as RecyclerView,
                context!!.resources.getString(R.string.word_is_moved) + " \"" + wordPair.word + "\" "
                        + context!!.resources.getString(R.string.into_studied_ist),
                Snackbar.LENGTH_SHORT or Snackbar.LENGTH_INDEFINITE
            )
                .setAction(context!!.resources.getString(R.string.undo)) {
                    undoMoveHandler(index)
                }
                .show()
            return index
        }

        override fun undoMoveHandler(index: Int) {
            super.undoMoveHandler(index)
            viewModel.undoMoveWordIntoStudiedList(wordPair)
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
        }

        override fun hideCardDetailsAnimated() {
            if (wordPair.countRightAnswers > 9)
                animateView(bindingOnStudy.isOpportunityTransferWord, 0F, 0F, 50F, 0F)
            super.hideCardDetailsAnimated()
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


        private fun setIsOpportunityTransferWordToShowCardDetails() = with(bindingOnStudy) {
            if (wordPair.countRightAnswers < 10) return
            animateView(isOpportunityTransferWord, 0F, 0F, -50F, 0F)
            val layoutParams = ConstraintLayout.LayoutParams(17, 60)
            isOpportunityTransferWord.layoutParams = layoutParams
            isOpportunityTransferWord.setImageResource(R.drawable.ic_btn_words_message_long)
        }

        private fun setIsOpportunityTransferWordToHideCardDetails() = with(bindingOnStudy) {
            if (wordPair.countRightAnswers < 10) return

            val layoutParams = ConstraintLayout.LayoutParams(23, 23)
            isOpportunityTransferWord.layoutParams = layoutParams
            isOpportunityTransferWord.setImageResource(R.drawable.ic_btn_words_message)
        }

    }

    override fun addWordAtPosition(word: WordPair, index: Int) {
        super.addWordAtPosition(word, index)
        viewModel.save(word)
    }

    private fun mapToOnStudy(wordPair: WordPair): OnStudyWordPair {
        return OnStudyWordPair(wordPair.id, wordPair.word, wordPair.translate, 0)
    }

    private fun mapToStudied(wordPair: WordPair): StudiedWordPair {
        return StudiedWordPair(wordPair.id, wordPair.word, wordPair.translate)
    }
}