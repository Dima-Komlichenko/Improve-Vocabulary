package com.example.domain

import com.example.domain.models.Theme

class ThemeConverter {
    companion object {
        fun convertThemeNameToEnglish(theme: Theme): Theme {
            return Theme(
                when (theme.value) {
                    "System", "Системная", "Системна" -> "System"
                    "Dark", "Темная", "Темна" -> "Dark"
                    "Light", "Светлая", "Світла" -> "Light"
                    else -> "System"
                }
            )
        }

        fun convertThemeNameToCustomLang(theme: Theme, language: String): Theme {
            return Theme(
                when (theme.value) {
                    "System", "Системная", "Системна" -> {
                        if (language == "english") "System"
                        else if (language == "ru") "Системная"
                        else if (language == "uk") "Системна"
                        else "System"
                    }
                    "Dark", "Темная", "Темна" -> {
                        if (language == "english") "Dark"
                        else if (language == "ru") "Темная"
                        else if (language == "uk") "Темна"
                        else "Dark"
                    }
                    "Light", "Светлая", "Світла" -> {
                        if (language == "english") "Light"
                        else if (language == "ru") "Светлая"
                        else if (language == "uk") "Світла"
                        else "Light"
                    }
                    else -> "System"
                }
            )
        }
    }
}