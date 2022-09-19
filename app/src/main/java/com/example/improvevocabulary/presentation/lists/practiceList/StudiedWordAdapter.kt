package com.example.improvevocabulary.presentation.lists.practiceList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.improvevocabulary.R
import com.example.improvevocabulary.databinding.WordItemBinding
import com.example.improvevocabulary.presentation.lists.baseList.WordAdapter
import com.example.improvevocabulary.presentation.lists.baseList.WordPair
import com.example.improvevocabulary.presentation.wordsFragment.WordListInfo
import com.example.improvevocabulary.utlis.TextToSpeech
import org.w3c.dom.Text

class StudiedWordAdapter(): WordAdapter() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudiedWordHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.word_item, parent, false)
        context = parent.context
        //tts = TextToSpeech(context!!)
        return  StudiedWordHolder(view)
    }

    override fun onBindViewHolder(holder: WordHolder, position: Int) { // вызывает bind холдера
        (holder as StudiedWordHolder).bind(words[position])
    }

    inner class StudiedWordHolder(override var item: View) : WordHolder(item) {

        override var binding = WordItemBinding.bind(item)

        override fun bind(word: WordPair) = with(binding) {
            super.bind(word)

            btnSave.setOnClickListener {
                editWord(word, tvWord.text.toString(), tvTranslate.text.toString())
                tvWord.text = word.word
                tvTranslate.text = word.translate
            }
        }
    }
}