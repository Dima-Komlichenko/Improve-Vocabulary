package com.example.data.storage.sharedPrefs

import android.content.Context
import android.content.SharedPreferences
import com.example.data.storage.interfaces.ThemeStorage
import com.example.data.storage.models.Theme
import com.example.domain.model.Themes

private const val SHARED_PREFS_THEME = "SHARED_PREFS_THEME"
private const val THEME = "THEME"
private const val NO_DATA = "NO_DATA"

class SharedPrefsThemeStorage(private val context: Context) : ThemeStorage {

    private var sharedPreferences: SharedPreferences =
        context.getSharedPreferences(SHARED_PREFS_THEME, Context.MODE_PRIVATE)

    override fun save(data: Theme): Boolean {
        sharedPreferences.edit().putString(THEME, data.value.name).apply()
        return true
    }

    override fun get(): Theme {
        return Theme(Themes.values().find {
            it.name == (sharedPreferences.getString(THEME, NO_DATA) ?: NO_DATA)
        }?: Themes.SYSTEM)

    }
}