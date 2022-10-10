package com.example.data.storage.sharedPrefs

import android.content.Context
import android.content.SharedPreferences
import com.example.data.storage.interfaces.OnStudyLanguageStorage
import com.example.domain.utils.Language

private const val SHARED_PREFS_LANGUAGE_FROM_LEARNING = "SHARED_PREFS_LANGUAGE_FROM_LEARNING"
private const val LANGUAGE_FROM_LEARNING = "LANGUAGE_FROM_LEARNING"

class SharedPrefsLanguageFromLearning(private val context: Context) : OnStudyLanguageStorage {

    private var sharedPreferences: SharedPreferences =
        context.getSharedPreferences(SHARED_PREFS_LANGUAGE_FROM_LEARNING, Context.MODE_PRIVATE)

    override fun save(data: Language): Boolean {
        sharedPreferences.edit().putString(LANGUAGE_FROM_LEARNING, data.toString()).apply()
        return true
    }

    override fun get(): Language {
        return when(sharedPreferences.getString(LANGUAGE_FROM_LEARNING, "NO_VALUE")) {
            "ENGLISH" -> Language.ENGLISH
            "SPANISH" -> Language.SPANISH
            "UKRAINIAN" -> Language.UKRAINIAN
            "RUSSIAN" -> Language.RUSSIAN
            else -> Language.ENGLISH
        }
    }
}