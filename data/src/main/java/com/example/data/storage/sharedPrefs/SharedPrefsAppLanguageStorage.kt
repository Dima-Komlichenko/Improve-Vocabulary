package com.example.data.storage.sharedPrefs

import android.content.Context
import android.content.SharedPreferences
import com.example.data.storage.interfaces.AppLanguageStorage
import com.example.data.storage.models.Language
import com.example.domain.model.Languages

private const val SHARED_PREFS_LANGUAGE = "SHARED_PREFS_LANGUAGE"
private const val LANGUAGE = "LANGUAGE"
private const val NO_DATA = "NO_DATA"

class SharedPrefsAppLanguageStorage(context: Context) : AppLanguageStorage {

    private var sharedPreferences: SharedPreferences =
        context.getSharedPreferences(SHARED_PREFS_LANGUAGE, Context.MODE_PRIVATE)

    override fun save(data: Language): Boolean {
        sharedPreferences.edit().putString(LANGUAGE, data.value.toString()).apply()
        return true
    }

    override fun get(): Language {
        return Language(mapStringLanguageToEnum(sharedPreferences.getString(LANGUAGE, "English") ?: "English"))
    }

    private fun mapStringLanguageToEnum(value: String): Languages {
        return Languages.valueOf(value.uppercase())
    }
}