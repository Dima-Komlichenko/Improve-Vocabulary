package com.example.improvevocabulary.models

data class WordPair(
    var id: Int,
    var word: String,
    var translate: String,
    var countRightAnswers: Int = 0,
    var areItemDetailsShown: Boolean = false
)