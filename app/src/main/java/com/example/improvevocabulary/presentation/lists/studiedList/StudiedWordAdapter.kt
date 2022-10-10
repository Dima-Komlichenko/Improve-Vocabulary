package com.example.improvevocabulary.presentation.lists.studiedList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.domain.model.OnStudyWordPair
import com.example.domain.model.PendingWordPair
import com.example.domain.model.StudiedWordPair
import com.example.domain.usecase.onStudy.RemoveOnStudyWordPairUseCase
import com.example.domain.usecase.onStudy.SaveOnStudyWordPairUseCase
import com.example.domain.usecase.pending.UpdatePendingWordPairUseCase
import com.example.domain.usecase.studied.RemoveStudiedWordPairUseCase
import com.example.domain.usecase.studied.SaveStudiedWordPairUseCase
import com.example.domain.usecase.studied.UpdateStudiedWordPairUseCase
import com.example.improvevocabulary.R
import com.example.improvevocabulary.databinding.WordItemBinding
import com.example.improvevocabulary.models.WordPair
import com.example.improvevocabulary.presentation.lists.baseList.WordAdapter
import com.example.improvevocabulary.utlis.TextToSpeech

class StudiedWordAdapter(private val tts: TextToSpeech,
                         val removeStudiedWordPairUseCase: RemoveStudiedWordPairUseCase,
                         val saveOnStudyWordPairUseCase: SaveOnStudyWordPairUseCase,
                         val saveStudiedWordPairUseCase: SaveStudiedWordPairUseCase,
                         val removeOnStudyWordPairUseCase: RemoveOnStudyWordPairUseCase
                         ): WordAdapter(tts) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudiedWordHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.word_item, parent, false)
        context = parent.context
        return StudiedWordHolder(view, tts)
    }

    inner class StudiedWordHolder(override var item: View, tts: TextToSpeech) : WordHolder(item, tts) {

        override var binding = WordItemBinding.bind(item)

        override fun bind(word: WordPair) {
            super.bind(word)
            binding.tvTranslate.text = word.translate

            binding.btnRemove.setOnClickListener {
                btnRemoveHandler()
                removeStudiedWordPairUseCase.execute(mapToStudied(wordPair))
            }

            binding.btnMove.setOnClickListener {
                btnMoveHandler()
                removeStudiedWordPairUseCase.execute(mapToStudied(wordPair))
                saveOnStudyWordPairUseCase.execute(mapToOnStudy(wordPair))
            }
        }

        override fun undoMoveHandler(index: Int) {
            super.undoMoveHandler(index)
            removeOnStudyWordPairUseCase.execute(mapToOnStudy(wordPair))
        }

        private fun mapToStudied(wordPair: WordPair): StudiedWordPair {
            return StudiedWordPair(wordPair.id, wordPair.word, wordPair.translate)
        }

        private fun mapToOnStudy(wordPair: WordPair): OnStudyWordPair {
            return OnStudyWordPair(wordPair.id, wordPair.word, wordPair.translate, wordPair.countRightAnswers)
        }
    }
}