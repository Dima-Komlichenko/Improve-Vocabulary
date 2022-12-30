package com.example.data.storage.sharedPrefs

import android.content.Context
import android.content.SharedPreferences
import com.example.data.storage.interfaces.RepetitionWasOfferedStorage

private const val SHARED_PREFS_REPETITION_WAS_OFFERED_TODAY = "SHARED_PREFS_REPETITION_WAS_OFFERED_TODAY"
private const val REPETITION_WAS_OFFERED_TODAY = "REPETITION_WAS_OFFERED_TODAY"

class SharedPrefsRepetitionWasOfferedToday(context: Context): RepetitionWasOfferedStorage {

    private var sharedPreferences: SharedPreferences =
        context.getSharedPreferences(SHARED_PREFS_REPETITION_WAS_OFFERED_TODAY, Context.MODE_PRIVATE)

    override fun update(day: String) {
        sharedPreferences.edit().putString(REPETITION_WAS_OFFERED_TODAY, day).apply()
    }

    override fun get(): String {
       return sharedPreferences.getString(REPETITION_WAS_OFFERED_TODAY, "0")?: "0"
    }
}