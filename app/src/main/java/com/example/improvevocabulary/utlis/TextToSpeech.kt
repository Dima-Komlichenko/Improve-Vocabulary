package com.example.improvevocabulary.utlis

import android.content.Context
import android.speech.tts.TextToSpeech
import android.speech.tts.TextToSpeech.*
import android.util.Log
import com.example.domain.model.Language
import com.example.domain.utils.LanguageConverter
import java.util.*


class TextToSpeech(context: Context) : OnInitListener {

    private var tts: TextToSpeech
    private var _text: String? = null

    init {
        tts = TextToSpeech(context, this)
        setVolume(0.8F)
        Log.i("TTS", "init")
    }

    fun setText(text: String) {
        _text = text
    }

    fun destroy() {
        Log.i("TTS", "destroy")
        tts.stop()
        tts.shutdown()
    }

    fun isLanguageAvailable(language: Language): Int {
        return tts.isLanguageAvailable(Locale(LanguageConverter.convertLangToCode(language)))
    }

    fun setLanguage(language: Language): Boolean {
        tts.language = Locale(LanguageConverter.convertLangToCode(language))
        return true
    }

    fun setVolume(volumeLevel: Float) { // 1 default
        tts.setSpeechRate(volumeLevel);
    }

    override fun onInit(status: Int) {
        if (tts.isSpeaking) tts.stop()
        Log.i("TTS", "onInit " + status)
        if (status == SUCCESS) {
            Log.i("TTS", "onInit SUCCESS " + status)
            tts.speak(_text, QUEUE_ADD, null, null)
        }
    }
}
