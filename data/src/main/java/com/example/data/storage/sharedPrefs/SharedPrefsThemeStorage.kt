package com.example.data.storage.sharedPrefs

import android.content.Context
import android.content.SharedPreferences
import com.example.data.storage.ThemeStorage
import com.example.data.storage.models.Theme

private const val SHARED_PREFS_THEME = "SHARED_PREFS_THEME"
private const val THEME = "THEME"
private const val NO_DATA = "NO_DATA"

class SharedPrefsThemeStorage(private val context: Context) : ThemeStorage {

    private var sharedPreferences: SharedPreferences =
        context.getSharedPreferences(SHARED_PREFS_THEME, Context.MODE_PRIVATE)

    override fun save(theme: Theme): Boolean {
        sharedPreferences.edit().putString(THEME, theme.value).apply()
        return true
    }

    override fun get(): Theme {
        return Theme(sharedPreferences.getString(THEME, NO_DATA) ?: NO_DATA)
    }

}