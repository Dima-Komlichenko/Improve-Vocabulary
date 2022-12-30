package com.example.improvevocabulary.presentation.lists.studiedList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
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
import com.google.android.material.snackbar.Snackbar

class StudiedWordAdapter(private val tts: TextToSpeech,
                         /*val removeStudiedWordPairUseCase: RemoveStudiedWordPairUseCase,
                         val saveOnStudyWordPairUseCase: SaveOnStudyWordPairUseCase,
                         val saveStudiedWordPairUseCase: SaveStudiedWordPairUseCase,
                         val removeOnStudyWordPairUseCase: RemoveOnStudyWordPairUseCase,*/
                         val viewModel: StudiedListViewModel,
                         ): WordAdapter(tts) {

    var onStudyCount = 0

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
                viewModel.removeStudiedWordPairUseCase.execute(mapToStudied(wordPair))
            }

            binding.btnMove.setOnClickListener {
                btnMoveHandler()
                viewModel.removeStudiedWordPairUseCase.execute(mapToStudied(wordPair))
                viewModel.saveOnStudy(wordPair)
            }
        }

        override fun btnMoveHandler(): Int {
            if (onStudyCount >= 20) {
                Snackbar.make(
                    binding.root,
                    R.string.limit_20,
                    Snackbar.LENGTH_SHORT or Snackbar.LENGTH_INDEFINITE
                ).show()
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
                }.show()
            return index
        }

        override fun undoMoveHandler(index: Int) {
            super.undoMoveHandler(index)
            viewModel.removeOnStudyWordPairUseCase.execute(mapToOnStudy(wordPair))
        }

        private fun mapToStudied(wordPair: WordPair): StudiedWordPair {
            return StudiedWordPair(wordPair.id, wordPair.word, wordPair.translate)
        }

        private fun mapToOnStudy(wordPair: WordPair): OnStudyWordPair {
            return OnStudyWordPair(wordPair.id, wordPair.word, wordPair.translate, wordPair.countRightAnswers)
        }
    }


    private fun mapToOnStudy(wordPair: WordPair): OnStudyWordPair{
        return OnStudyWordPair(wordPair.id, wordPair.word, wordPair.translate, wordPair.countRightAnswers)
    }

    fun getOnStudyCount(value: Int) {
        onStudyCount = value
    }
}