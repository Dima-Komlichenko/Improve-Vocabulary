package com.example.improvevocabulary.presentation.lists.pendingList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.model.OnStudyWordPair
import com.example.domain.model.PendingWordPair
import com.example.domain.usecase.onStudy.RemoveOnStudyWordPairUseCase
import com.example.domain.usecase.onStudy.SaveOnStudyWordPairUseCase
import com.example.domain.usecase.pending.RemovePendingWordPairUseCase
import com.example.domain.usecase.pending.SavePendingWordPairUseCase
import com.example.domain.usecase.pending.UpdatePendingWordPairUseCase
import com.example.domain.usecase.studied.SaveStudiedWordPairUseCase
import com.example.improvevocabulary.R
import com.example.improvevocabulary.databinding.EditableWordItemBinding
import com.example.improvevocabulary.databinding.WordItemBinding
import com.example.improvevocabulary.models.WordPair
import com.example.improvevocabulary.presentation.lists.baseEditableList.EditableWordAdapter
import com.example.improvevocabulary.presentation.lists.baseList.WordAdapter
import com.example.improvevocabulary.utlis.TextToSpeech
import com.google.android.material.snackbar.Snackbar

class PendingWordAdapter(private val tts: TextToSpeech,
                         val updatePendingWordPairUseCase: UpdatePendingWordPairUseCase,
                         val removePendingWordPairUseCase: RemovePendingWordPairUseCase,
                         val savePendingWordPairUseCase: SavePendingWordPairUseCase,
                         val saveOnStudyWordPairUseCase: SaveOnStudyWordPairUseCase,
                         val removeOnStudyWordPairUseCase: RemoveOnStudyWordPairUseCase,
                         ) : EditableWordAdapter(tts) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PendingWordHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.editable_word_item, parent, false)
        context = parent.context
        return PendingWordHolder(view, tts)
    }

    inner class PendingWordHolder(override var item: View, tts: TextToSpeech) : EditableWordHolder(item, tts) {

        private lateinit var bindingPending: EditableWordItemBinding

        override fun bind(word: WordPair) {
            super.bind(word)

            bindingPending = EditableWordItemBinding.bind(item)


            //TODO: btnSave
            binding.btnSave.setOnClickListener {
                if(!btnSaveHandler()) return@setOnClickListener
                updatePendingWordPairUseCase.execute(mapToPending(wordPair))
            }

            binding.btnRemove.setOnClickListener {
                btnRemoveHandler()
                removePendingWordPairUseCase.execute(mapToPending(wordPair))
            }

            binding.btnMove.setOnClickListener {
                btnMoveHandler()
                removePendingWordPairUseCase.execute(mapToPending(wordPair))
                saveOnStudyWordPairUseCase.execute(mapToOnStudy(wordPair))
            }
        }

        override fun undoMoveHandler(index: Int) {
            super.undoMoveHandler(index)
            removeOnStudyWordPairUseCase.execute(mapToOnStudy(wordPair))
        }

        private fun mapToPending(wordPair: WordPair): PendingWordPair {
            return PendingWordPair(wordPair.id, wordPair.word, wordPair.translate)
        }
    }

    override fun addWordAtPosition(word: WordPair, index: Int) {
        super.addWordAtPosition(word, index)
        savePendingWordPairUseCase.execute(mapToPending(word))
    }

    private fun mapToPending(wordPair: WordPair): PendingWordPair {
        return PendingWordPair(wordPair.id, wordPair.word, wordPair.translate)
    }

    private fun mapToOnStudy(wordPair: WordPair): OnStudyWordPair{
        return OnStudyWordPair(wordPair.id, wordPair.word, wordPair.translate, wordPair.countRightAnswers)
    }
}