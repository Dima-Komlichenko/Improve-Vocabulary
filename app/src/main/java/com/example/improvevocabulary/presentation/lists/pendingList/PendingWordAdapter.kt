package com.example.improvevocabulary.presentation.lists.pendingList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.improvevocabulary.R
import com.example.improvevocabulary.databinding.WordItemBinding
import com.example.improvevocabulary.models.WordPair
import com.example.improvevocabulary.presentation.lists.baseList.WordAdapter
import com.example.improvevocabulary.presentation.lists.onStudyList.OnStudyWordAdapter
import com.example.improvevocabulary.utlis.TextToSpeech

class PendingWordAdapter(private val tts: TextToSpeech) : WordAdapter(tts) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PendingWordAdapter.PendingWordHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.word_item, parent, false)
        context = parent.context
        return PendingWordHolder(view, tts)
    }

    inner class PendingWordHolder(override var item: View, tts: TextToSpeech) : WordHolder(item, tts) {

        override var binding = WordItemBinding.bind(item)

        override fun bind(word: WordPair) {
            super.bind(word)

            binding.tvTranslate.text = word.translate
        }
    }
}