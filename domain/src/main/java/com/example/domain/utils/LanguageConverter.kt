package com.example.domain.utils

import com.example.domain.model.Language

object LanguageConverter {

    fun convertLangToCode(language: Language): Language {
        return when (language.value) {
            "English", "Анлийский", "Англійська" -> Language("Default")
            "Russian", "Русский", "Російська" -> Language("RU")
            "Ukrainian", "Украинский", "Українська" -> Language("UK")
            "Spanish", "Испанский", "Іспанська" -> Language("SP") //TODO: sp
            else -> Language("Default")
        }
    }

    fun convertCodeToLang(langCode: Language): Language {
        return when (langCode.value) {
            "English" -> Language("Default")
            "RU" -> Language("Русский")
            "UK" -> Language("Українська")
            "SP" -> Language("Spanish") //TODOL: Spanish
            else -> Language("Default")
        }
    }
    fun convertLangToIndex(language: com.example.domain.utils.Language): Int {
        return when(language) {
            com.example.domain.utils.Language.ENGLISH -> 0
            com.example.domain.utils.Language.SPANISH -> 1
            com.example.domain.utils.Language.UKRAINIAN -> 2
            com.example.domain.utils.Language.RUSSIAN -> 3
            else -> 0
        }
    }
}