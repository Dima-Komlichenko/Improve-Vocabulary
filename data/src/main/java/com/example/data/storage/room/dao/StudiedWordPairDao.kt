package com.example.data.storage.room.dao

import androidx.room.*
import com.example.data.storage.models.StudiedWordPair

@Dao
interface StudiedWordPairDao {
    @Insert
    suspend fun addWordPair(wordPair: StudiedWordPair)

    @Query("SELECT * FROM studied_word_pair_table ORDER BY id ASC")
    suspend fun readAll() :  List<StudiedWordPair>

    @Query("SELECT COUNT(id) FROM studied_word_pair_table")
    suspend fun getCount() : Int

    @Update
    suspend fun updateWordPair(wordPair: StudiedWordPair)

    @Delete
    suspend fun deleteWordPair(wordPair: StudiedWordPair)

    @Query("DELETE FROM studied_word_pair_table")
    suspend fun deleteAll()

    @Query("Select max(id) from studied_word_pair_table")
    suspend fun getMaxId(): Int
}
