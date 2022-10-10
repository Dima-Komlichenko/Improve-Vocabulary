package com.example.data.storage.room.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.data.storage.models.OnStudyWordPair
import kotlinx.coroutines.flow.Flow

@Dao
interface OnStudyWordPairDao {
    @Insert
    suspend fun addWordPair(wordPair: OnStudyWordPair)

    @Query("SELECT * FROM on_study_word_pair_table ORDER BY id ASC")
    suspend fun readAll() : List<OnStudyWordPair>

    @Update
    suspend fun updateWordPair(wordPair: OnStudyWordPair)

    @Delete
    suspend fun deleteWordPair(wordPair: OnStudyWordPair)

    @Query("DELETE FROM on_study_word_pair_table")
    suspend fun deleteAll()
}