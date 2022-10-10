package com.example.data.storage.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pending_word_pair_table")
data class PendingWordPair(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var word: String,
    var translate: String,
)
