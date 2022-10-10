package com.example.data.storage.room.dao

import androidx.room.*
import com.example.data.storage.models.PendingWordPair

@Dao
interface PendingWordPairDao {
    @Insert
    suspend fun addWordPair(wordPair: PendingWordPair)

     @Query("SELECT * FROM pending_word_pair_table ORDER BY id ASC")
     suspend fun readAll() : List<PendingWordPair>

    @Update
    suspend fun updateWordPair(wordPair: PendingWordPair)

    @Delete
    suspend fun deleteWordPair(wordPair: PendingWordPair)

    @Query("DELETE FROM pending_word_pair_table")
    suspend fun deleteAll()
}
