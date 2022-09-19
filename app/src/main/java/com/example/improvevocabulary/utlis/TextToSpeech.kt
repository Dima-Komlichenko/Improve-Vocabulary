package com.example.improvevocabulary.utlis

import android.content.Context
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.speech.tts.TextToSpeech.OnInitListener
import android.speech.tts.TextToSpeech.SUCCESS
import android.util.Log
import android.view.View
import android.widget.EditText
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.*


class TextToSpeech(context: Context) : OnInitListener {

    private lateinit var tts: TextToSpeech
    private var _text: String? = null
    init {
        GlobalScope.launch {
            tts = TextToSpeech(context, this@TextToSpeech)
        }

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
        if (status == TextToSpeech.SUCCESS) {
            val result = tts.setLanguage(Locale.US)
            if (result == TextToSpeech.LANG_MISSING_DATA
                || result == TextToSpeech.LANG_NOT_SUPPORTED
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
        tts.speak(_text, TextToSpeech.QUEUE_FLUSH, null)
    }
}