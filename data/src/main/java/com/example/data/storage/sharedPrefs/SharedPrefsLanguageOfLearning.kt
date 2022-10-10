package com.example.data.storage.sharedPrefs

import android.content.Context
import android.content.SharedPreferences
import com.example.data.storage.interfaces.LanguageOfLearning
import com.example.domain.utils.Language


private const val SHARED_PREFS_LANGUAGE_OF_LEARNING = "SHARED_PREFS_LANGUAGE_OF_LEARNING"
private const val LANGUAGE_OF_LEARNING = "LANGUAGE_OF_LEARNING"

class SharedPrefsLanguageOfLearning(private val context: Context) : LanguageOfLearning {

    private var sharedPreferences: SharedPreferences =
        context.getSharedPreferences(SHARED_PREFS_LANGUAGE_OF_LEARNING, Context.MODE_PRIVATE)

    override fun save(data: Language): Boolean {
        sharedPreferences.edit().putString(LANGUAGE_OF_LEARNING, data.toString()).apply()
        return true
    }

    override fun get(): Language {
        return when(sharedPreferences.getString(LANGUAGE_OF_LEARNING, "NO_VALUE")) {
            "ENGLISH" -> Language.ENGLISH
            "SPANISH" -> Language.SPANISH
            "UKRAINIAN" -> Language.UKRAINIAN
            "RUSSIAN" -> Language.RUSSIAN
            else -> Language.ENGLISH
        }
    }
}