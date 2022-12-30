package com.example.domain.utils

import com.example.domain.model.Language
import com.example.domain.model.Languages

object LanguageConverter {

    fun convertLangToCode(language: Language): String {
        return when (language.value) {
            Languages.ENGLISH -> "en"
            Languages.ARABIC -> "ar"
            Languages.CZECH -> "cs"
            Languages.GERMAN -> "de"
            Languages.SPANISH -> "es"
            Languages.FRENCH -> "fr"
            Languages.HINDI -> "hi"
            Languages.ITALIAN -> "it"
            Languages.HEBREW -> "iw"
            Languages.JAPANESE -> "ja"
            Languages.KAZAKH -> "kk"
            Languages.KOREAN -> "ko"
            Languages.POLISH -> "pl"
            Languages.PORTUGUESE -> "pt"
            Languages.RUSSIAN -> "ru"
            Languages.TURKISH -> "tr"
            Languages.UKRAINIAN -> "uk"
            Languages.CHINESE -> "zh"
        }
    }
}