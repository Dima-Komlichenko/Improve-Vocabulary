package com.example.data.storage.sharedPrefs

import android.content.Context
import android.content.SharedPreferences
import com.example.data.storage.interfaces.IsFirstTimeLaunchStorage

private const val SHARED_PREFS_IS_FIRST_TIME_LAUNCH = "SHARED_PREFS_IS_FIRST_TIME_LAUNCH"
private const val IS_FIRST_TIME_LAUNCH = "IS_FIRST_TIME_LAUNCH"

class SharedPrefsIsFirstTimeLaunch(context: Context): IsFirstTimeLaunchStorage {

    private var sharedPreferences: SharedPreferences =
        context.getSharedPreferences(SHARED_PREFS_IS_FIRST_TIME_LAUNCH, Context.MODE_PRIVATE)

    override fun launch() {
        sharedPreferences.edit().putBoolean(IS_FIRST_TIME_LAUNCH, false).apply()
    }

    override fun get(): Boolean {
        return sharedPreferences.getBoolean(IS_FIRST_TIME_LAUNCH, true)
    }
}