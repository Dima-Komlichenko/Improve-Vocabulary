package com.example.domain

import com.example.domain.models.Language
import java.util.*

class LanguageConverter {
    companion object {
        fun convertLangToCode(language: Language): Language {
            return when (language.value) {
                "English", "Анлийский", "Англійська" -> Language("Default")
                "Russian", "Русский", "Російська" -> Language("RU")
                "Ukrainian", "Украинский", "Українська" -> Language("UK")
                else -> Language("Default")
            }
        }

        fun convertCodeToLang(langCode: Language): Language {
            return when (langCode.value) {
                "English" -> Language("Default")
                "RU" -> Language("Русский")
                "UK" -> Language("Українська")
                else -> Language("Default")
            }
        }

    }
}