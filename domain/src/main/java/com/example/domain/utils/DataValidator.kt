package com.example.domain.utils

enum class Language { ENGLISH, SPANISH, UKRAINIAN, RUSSIAN }

class DataValidator {

    companion object {
        fun isDataValid(data: String, language: Language): Boolean {
            return when (language) {
                Language.ENGLISH -> {
                    Regex("[abcdefghijklmnoprqstuvwxyz ,()]+", RegexOption.IGNORE_CASE)
                        .matches(data)
                }
                Language.SPANISH -> {
                    Regex("[,()]+", RegexOption.IGNORE_CASE)
                        .matches(data)
                }
                Language.UKRAINIAN -> {
                    Regex(
                        "[абвгйцуккенгґдеєжзиіїйклмнопрстуфхцчшщьюя ,()]+", RegexOption.IGNORE_CASE)
                        .matches(data)
                }
                Language.RUSSIAN -> {
                    Regex("[абвгдеёжзийклмнопрстуфхцчшщъыьэюя ,()]+", RegexOption.IGNORE_CASE)
                        .matches(data)
                }

                else -> return false
            }
        }
    }
}