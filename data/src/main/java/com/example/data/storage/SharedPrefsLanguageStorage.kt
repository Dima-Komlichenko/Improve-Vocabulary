package com.example.data.storage

import android.content.Context
import android.content.SharedPreferences

private const val SHARED_PREFS_LANGUAGE = "SHARED_PREFS_LANGUAGE"
private const val LANGUAGE = "LANGUAGE"
private const val NO_DATA = "NO_DATA"

class SharedPrefsLanguageStorage(private val context: Context) : LanguageStorage {

    private var sharedPreferences: SharedPreferences =
        context.getSharedPreferences(SHARED_PREFS_LANGUAGE, Context.MODE_PRIVATE)

    override fun save(lang: Language): Boolean {
        sharedPreferences.edit().putString(LANGUAGE, lang.language).apply()
        return true
    }

    override fun get(): Language {
        return Language(sharedPreferences.getString(LANGUAGE, NO_DATA) ?: NO_DATA)
    }

}