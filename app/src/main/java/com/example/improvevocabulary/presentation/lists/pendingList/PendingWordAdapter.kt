package com.example.improvevocabulary.presentation.lists.pendingList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.model.Language
import com.example.domain.model.OnStudyWordPair
import com.example.domain.model.PendingWordPair
import com.example.domain.usecase.onStudy.RemoveOnStudyWordPairUseCase
import com.example.domain.usecase.onStudy.SaveOnStudyWordPairUseCase
import com.example.domain.usecase.pending.RemovePendingWordPairUseCase
import com.example.domain.usecase.pending.SavePendingWordPairUseCase
import com.example.domain.usecase.pending.UpdatePendingWordPairUseCase
import com.example.improvevocabulary.R
import com.example.improvevocabulary.databinding.EditableWordItemBinding
import com.example.improvevocabulary.models.WordPair
import com.example.improvevocabulary.presentation.lists.baseEditableList.EditableWordAdapter
import com.example.improvevocabulary.utlis.TextToSpeech
import com.google.android.material.snackbar.Snackbar

class PendingWordAdapter(
    private val tts: TextToSpeech,
    val viewModel: PendingListViewModel,
) : EditableWordAdapter(tts, viewModel.languageFromLearning.value!!, viewModel.languageOfLearning.value!!) {

    private var onStudyCount = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PendingWordHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.editable_word_item, parent, false)
        context = parent.context
        return PendingWordHolder(view, tts)
    }

    inner class PendingWordHolder(override var item: View, tts: TextToSpeech) :
        EditableWordHolder(item, tts) {

        private lateinit var bindingPending: EditableWordItemBinding

        override fun bind(word: WordPair) {
            super.bind(word)

            bindingPending = EditableWordItemBinding.bind(item)


            //TODO: btnSave
            binding.btnSave.setOnClickListener {
                if (!btnSaveHandler()) return@setOnClickListener
                viewModel.updatePendingWordPairUseCase.execute(mapToPending(wordPair))
            }

            binding.btnRemove.setOnClickListener {
                btnRemoveHandler()
                viewModel.removePendingWordPairUseCase.execute(mapToPending(wordPair))
            }

            binding.btnMove.setOnClickListener {
                btnMoveHandler()
                viewModel.removePendingWordPairUseCase.execute(mapToPending(wordPair))
                viewModel.saveOnStudy(wordPair)
            }
        }

        override fun btnMoveHandler(): Int {
            if (onStudyCount >= 20) {
                Snackbar.make(
                    binding.root,
                    R.string.limit_20,
                    Snackbar.LENGTH_SHORT or Snackbar.LENGTH_INDEFINITE
                )
                    .show()
                return -1
            }
            val index = super.btnMoveHandler()

            Snackbar.make(
                item.parent as RecyclerView,
                context!!.resources.getString(R.string.word_is_moved) + " \"" + wordPair.word + "\" "
                        + context!!.resources.getString(R.string.into_on_study_list),
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
            viewModel.removeOnStudyWordPairUseCase.execute(mapToOnStudy(wordPair))
        }

        private fun mapToPending(wordPair: WordPair): PendingWordPair {
            return PendingWordPair(wordPair.id, wordPair.word, wordPair.translate)
        }
    }

    fun getOnStudyCount(value: Int) {
        onStudyCount = value
    }

    override fun addWordAtPosition(word: WordPair, index: Int) {
        super.addWordAtPosition(word, index)
        viewModel.save(word)
    }

    private fun mapToOnStudy(wordPair: WordPair): OnStudyWordPair {
        return OnStudyWordPair(
            wordPair.id,
            wordPair.word,
            wordPair.translate,
            wordPair.countRightAnswers
        )
    }
}