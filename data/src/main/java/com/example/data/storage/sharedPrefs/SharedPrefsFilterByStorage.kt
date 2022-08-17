package com.example.data.storage.sharedPrefs

import android.content.Context
import android.content.SharedPreferences
import com.example.data.storage.FilterByStorage
import com.example.data.storage.models.FilterBy
import com.example.data.storage.models.PressedSortButton


private const val SHARED_PREFS_FILTER_BY = "SHARED_PREFS_FILTER_BY"
private const val FILTER_BY = "FILTER_BY"

class SharedPrefsFilterByStorage(private val context: Context) : FilterByStorage {

    private var sharedPreferences: SharedPreferences =
        context.getSharedPreferences(SHARED_PREFS_FILTER_BY, Context.MODE_PRIVATE)

    override fun save(data: FilterBy): Boolean {
        sharedPreferences.edit().putString(FILTER_BY, data.value.toString()).apply()
        return true
    }

    override fun get(): FilterBy {
        return FilterBy(
            PressedSortButton.valueOf(
                sharedPreferences.getString(FILTER_BY, PressedSortButton.NEWER.toString()) ?: PressedSortButton.NEWER.toString()
            )
        )
    }

}