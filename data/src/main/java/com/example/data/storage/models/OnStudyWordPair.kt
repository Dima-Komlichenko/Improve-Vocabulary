package com.example.data.storage.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "on_study_word_pair_table")
data class OnStudyWordPair(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var word: String,
    var translate: String,
    var countRightAnswers: Int = 0,
)