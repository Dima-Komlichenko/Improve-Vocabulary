package com.example.data.storage.sharedPrefs

import android.content.Context
import android.content.SharedPreferences
import com.example.data.storage.interfaces.WasPracticeDescriptionShownOnceStorage

private const val SHARED_PREFS_WAS_PRACTICE_DESC_SHOWN_ONCE = "SHARED_PREFS_WAS_PRACTICE_DESC_SHOWN_ONCE"
private const val WAS_PRACTICE_DESC_SHOWN_ONCE = "WAS_PRACTICE_DESC_SHOWN_ONCE"

class SharedPrefsWasPracticeDescriptionShownOnce(context: Context): WasPracticeDescriptionShownOnceStorage {

    private var sharedPreferences: SharedPreferences =
        context.getSharedPreferences(SHARED_PREFS_WAS_PRACTICE_DESC_SHOWN_ONCE, Context.MODE_PRIVATE)

    override fun launch() {
        sharedPreferences.edit().putBoolean(WAS_PRACTICE_DESC_SHOWN_ONCE, true).apply()
    }

    override fun get(): Boolean {
        return sharedPreferences.getBoolean(WAS_PRACTICE_DESC_SHOWN_ONCE, false)
    }
}