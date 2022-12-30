package com.example.data.storage.sharedPrefs

import android.content.Context
import android.content.SharedPreferences
import com.example.data.storage.interfaces.LanguageFromLearningStorage
import com.example.data.storage.models.Language
import com.example.domain.model.Languages

private const val SHARED_PREFS_LANGUAGE_FROM_LEARNING = "SHARED_PREFS_LANGUAGE_FROM_LEARNING"
private const val LANGUAGE_FROM_LEARNING = "LANGUAGE_FROM_LEARNING"

class SharedPrefsLanguageFromLearning(context: Context) : LanguageFromLearningStorage {

    private var sharedPreferences: SharedPreferences =
        context.getSharedPreferences(SHARED_PREFS_LANGUAGE_FROM_LEARNING, Context.MODE_PRIVATE)

    override fun save(data: Language): Boolean {
        sharedPreferences.edit().putString(LANGUAGE_FROM_LEARNING, data.value.toString()).apply()
        return true
    }

    override fun get(): Language {
        return Language(mapStringLanguageToEnum(sharedPreferences.getString(LANGUAGE_FROM_LEARNING, "English")?: "English"))

    }

    private fun mapStringLanguageToEnum(value: String): Languages {
        return Languages.valueOf(value.uppercase())
    }
}