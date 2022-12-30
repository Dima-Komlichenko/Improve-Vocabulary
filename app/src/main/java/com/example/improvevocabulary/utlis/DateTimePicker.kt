package com.example.improvevocabulary.utlis

import android.util.Log
import com.example.domain.usecase.languages.repetitionWasOffered.GetRepetitionWasOfferedUseCase
import com.example.domain.usecase.languages.repetitionWasOffered.UpdateRepetitionWasOfferedUseCase
import java.text.SimpleDateFormat
import java.util.*

class DateTimePicker(day: String) {

    private val dayFormat = "d"
    private var lastOfferedDay = "0"

    init {
        lastOfferedDay = day
    }

    fun wasRepetitionOfferedToday(): Boolean {
        val currentDay = getCurrentDay()
        if (currentDay != lastOfferedDay) {
            lastOfferedDay = currentDay
            return false
        }
        return true
    }

    fun getCurrentDay(): String {
        Log.i("dtp", lastOfferedDay)
        Log.i("dtp", SimpleDateFormat(dayFormat).format(Date()))
        return SimpleDateFormat(dayFormat).format(Date())
    }
}