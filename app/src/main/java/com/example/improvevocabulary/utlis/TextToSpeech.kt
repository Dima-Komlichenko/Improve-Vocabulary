package com.example.improvevocabulary.utlis

import android.content.Context
import android.speech.tts.TextToSpeech
import android.speech.tts.TextToSpeech.*
import android.util.Log
import com.example.data.storage.sharedPrefs.SharedPrefsLanguageOfLearning
import com.example.domain.utils.Language
import java.util.*


class TextToSpeech(context: Context) : OnInitListener {

    private var tts: TextToSpeech
    private var _text: String? = null

    init {
        tts = TextToSpeech(context, this@TextToSpeech)
        setVolume(0.8F)
        setLanguage(SharedPrefsLanguageOfLearning(context).get())
    }


    fun setText(text: String) {
        _text = text
    }

    fun destroy() {
        tts.stop()
        tts.shutdown()
    }

    fun setLanguage(language: Language) {
        val result = tts.setLanguage(getLocale(language))
        if (result == LANG_MISSING_DATA
            || result == LANG_NOT_SUPPORTED
        ) {
            Log.e("TTS", "This Language is not supported")
        }
    }

    private fun getLocale(language: Language): Locale {
        return when (language) {
            Language.ENGLISH -> Locale("en")
            Language.SPANISH -> Locale("es")
            Language.UKRAINIAN -> Locale("uk")
            Language.RUSSIAN -> Locale("ru")
        }
    }

    fun setVolume(volumeLevel: Float) { // 1 default
        tts.setSpeechRate(volumeLevel);
    }

    override fun onInit(status: Int) {
        if (status == SUCCESS) {
            tts.speak(_text, QUEUE_ADD, null, null)
        }
    }
}
