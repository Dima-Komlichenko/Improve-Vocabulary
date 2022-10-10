package com.example.domain.model

class OnStudyWordPair(
    var id: Int,
    var word: String,
    var translate: String,
    var countRightAnswers: Int = 0
)