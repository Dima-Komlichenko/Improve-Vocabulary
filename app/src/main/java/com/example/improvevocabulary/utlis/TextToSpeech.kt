package com.example.improvevocabulary.utlis

import android.content.Context
import android.util.Log
import java.util.*
import android.speech.tts.TextToSpeech
import android.speech.tts.TextToSpeech.*


class TextToSpeech(context: Context) : TextToSpeech.OnInitListener {

    private var tts: TextToSpeech
    private var _text: String? = null
    init {
        tts = TextToSpeech(context, this@TextToSpeech)
        setVolume(0.8F)
        //getFirstLanguage from repository
    }


    fun setText(text: String) {
        _text = text
    }

    fun destroy() {
        tts.stop()
        tts.shutdown()
    }

    fun setLanguage(locale: Locale) {
        tts.language = locale /*Locale.CHINESE*/
    }

    fun setVolume(volumeLevel: Float) { // 1 default
        tts.setSpeechRate(volumeLevel);
    }

    override fun onInit(status: Int) {
        if (status == SUCCESS) {
            val result = tts.setLanguage(Locale.US)
            if (result == LANG_MISSING_DATA
                || result == LANG_NOT_SUPPORTED
            ) {
                Log.e("TTS", "This Language is not supported")
            } else {
                speakOut()
            }
        } else {
            Log.e("TTS", "Initilization Failed!")
        }
    }

    private fun speakOut() {
        tts.speak(_text, QUEUE_FLUSH, null)
    }
}