package com.example.data.storage.room.dao

import androidx.room.*
import com.example.data.storage.models.OnStudyWordPair

@Dao
interface OnStudyWordPairDao {
    @Insert
    suspend fun addWordPair(wordPair: OnStudyWordPair)

    @Query("SELECT * FROM on_study_word_pair_table ORDER BY id ASC")
    suspend fun readAll() : List<OnStudyWordPair>

    @Query("SELECT COUNT(id) FROM on_study_word_pair_table")
    suspend fun getCount() : Int

    @Update
    suspend fun updateWordPair(wordPair: OnStudyWordPair)

    @Delete
    suspend fun deleteWordPair(wordPair: OnStudyWordPair)

    @Query("DELETE FROM on_study_word_pair_table")
    suspend fun deleteAll()
}