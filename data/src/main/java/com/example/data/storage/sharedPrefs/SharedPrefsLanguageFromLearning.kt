package com.example.data.storage.sharedPrefs

import android.content.Context
import android.content.SharedPreferences
import com.example.data.storage.interfaces.LanguageFromLearningStorage

private const val SHARED_PREFS_LANGUAGE_FROM_LEARNING = "SHARED_PREFS_LANGUAGE_FROM_LEARNING"
private const val LANGUAGE_FROM_LEARNING = "LANGUAGE_FROM_LEARNING"

class SharedPrefsLanguageFromLearning(context: Context) : LanguageFromLearningStorage {

    private var sharedPreferences: SharedPreferences =
        context.getSharedPreferences(SHARED_PREFS_LANGUAGE_FROM_LEARNING, Context.MODE_PRIVATE)

    override fun save(data: com.example.data.storage.models.Language): Boolean {
        sharedPreferences.edit().putString(LANGUAGE_FROM_LEARNING, data.value).apply()
        return true
    }

    override fun get(): com.example.data.storage.models.Language {
        return com.example.data.storage.models.Language(sharedPreferences.getString(LANGUAGE_FROM_LEARNING, "NO_VALUE")!!)
    }
}