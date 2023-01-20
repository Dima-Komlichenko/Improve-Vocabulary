package com.example.improvevocabulary.utlis

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.speech.RecognitionListener
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.MutableLiveData
import java.util.*


class SpeechToText {

    private val PERMISSION_RECORD_AUDIO_REQUEST = 1
    private lateinit var speechRecognizer: SpeechRecognizer
    private lateinit var speechRecognizerIntent: Intent
    var result: MutableLiveData<String?> = MutableLiveData()
    var isRecording = false
    var isResultObserved = false

    fun init(activity: Activity) {
        if (isPermissionGranted(activity.baseContext)) {
            requestPermission(activity)
        }

        speechRecognizer = SpeechRecognizer.createSpeechRecognizer(activity)

        speechRecognizerIntent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        speechRecognizerIntent.putExtra(
            RecognizerIntent.EXTRA_LANGUAGE_MODEL,
            RecognizerIntent.LANGUAGE_MODEL_FREE_FORM
        )

        speechRecognizer.setRecognitionListener(object : RecognitionListener {
            override fun onReadyForSpeech(bundle: Bundle) {}
            override fun onBeginningOfSpeech() {}
            override fun onRmsChanged(v: Float) {}
            override fun onBufferReceived(bytes: ByteArray) {}
            override fun onEndOfSpeech() {
                isRecording = false
                isResultObserved = false
            }
            override fun onPartialResults(bundle: Bundle) {}
            override fun onEvent(i: Int, bundle: Bundle) {}
            override fun onError(i: Int) {
                Log.i("stt", "onError")
            }

            override fun onResults(bundle: Bundle) {
                Log.i("stt", "success")
                val data = bundle.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)
                result.value = data!![0]
            }
        })
    }

    
    fun record() {
        speechRecognizer.startListening(speechRecognizerIntent)
    }

    fun setLanguage(locale: Locale) {
        speechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, locale.toString())
    }


    fun finish() {
        speechRecognizer.stopListening()
    }

    private fun requestPermission(activity: Activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            ActivityCompat.requestPermissions(
                activity,
                arrayOf(Manifest.permission.RECORD_AUDIO),
                PERMISSION_RECORD_AUDIO_REQUEST
            )
        }
    }

    private fun isPermissionGranted(context: Context): Boolean {
        return ContextCompat.checkSelfPermission(context, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED
    }

}