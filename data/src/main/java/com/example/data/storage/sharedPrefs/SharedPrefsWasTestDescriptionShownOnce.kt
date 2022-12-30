package com.example.data.storage.sharedPrefs

import android.content.Context
import android.content.SharedPreferences
import com.example.data.storage.interfaces.WasTestDescriptionShownOnceStorage

private const val SHARED_PREFS_WAS_TEST_DESC_SHOWN_ONCE = "SHARED_PREFS_WAS_TEST_DESC_SHOWN_ONCE"
private const val WAS_TEST_DESC_SHOWN_ONCE = "WAS_TEST_DESC_SHOWN_ONCE"

class SharedPrefsWasTestDescriptionShownOnce(context: Context): WasTestDescriptionShownOnceStorage {

    private var sharedPreferences: SharedPreferences =
        context.getSharedPreferences(SHARED_PREFS_WAS_TEST_DESC_SHOWN_ONCE, Context.MODE_PRIVATE)

    override fun launch() {
        sharedPreferences.edit().putBoolean(WAS_TEST_DESC_SHOWN_ONCE, true).apply()
    }

    override fun get(): Boolean {
        return sharedPreferences.getBoolean(WAS_TEST_DESC_SHOWN_ONCE, false)
    }
}