package com.example.improvevocabulary.presentation.lists.studiedList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.improvevocabulary.R
import com.example.improvevocabulary.databinding.WordItemBinding
import com.example.improvevocabulary.presentation.lists.baseList.WordAdapter
import com.example.improvevocabulary.presentation.lists.baseList.WordPair
import com.example.improvevocabulary.utlis.TextToSpeech

class StudiedWordAdapter(private val tts: TextToSpeech): WordAdapter(tts) {

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
        }
    }
}