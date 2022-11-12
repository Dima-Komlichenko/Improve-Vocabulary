package com.example.domain.utils

import com.example.domain.model.Language

object LanguageConverter {

    fun convertLangToCode(language: Language): String {
        return when (language) {
            Language.ENGLISH -> "en" //TODO: "en"
            Language.RUSSIAN -> "ru"
            Language.UKRAINIAN -> "uk"
            Language.SPANISH -> "sp"
            else -> "Default"
        }
    }

    fun convertCodeToLang(langCode: String): Language {
        return when (langCode) {
            "English", "Английский", "Англійська" -> Language.ENGLISH
            "Russian", "Русский", "Російська" -> Language.RUSSIAN
            "Ukrainian", "Украинский", "Українська" -> Language.UKRAINIAN
            "Spanish", "Испанский", "Іспанська" -> Language.SPANISH
            else -> Language.ENGLISH
        }
    }

    fun convertLangToIndex(language: Language): Int {
        return when (language) {
            Language.ENGLISH -> 0
            Language.SPANISH -> 1
            Language.RUSSIAN -> 2
            Language.UKRAINIAN -> 3
            else -> 0
        }
    }
}