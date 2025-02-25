package com.example.improvevocabulary.utlis

import java.util.*
import java.util.regex.Pattern

abstract class DataConverter {
    companion object {
        private val pattern = Pattern.compile("\\s{2,}")

        fun validateSpaces(data: String): String{
            return pattern.matcher(data).replaceAll(" ").trim()
        }

        fun convertToLowerCaseAndNoSymbols(data: String): String {
            val res = data.lowercase().replace(",", "").replace("(", "").replace(")", "")
            return res
        }

        fun capitalize(data: String): String {
            return data.lowercase()
                .replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString() }
        }
    }
}