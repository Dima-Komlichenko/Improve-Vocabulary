package com.example.domain.repositoriesI

import com.example.domain.model.OnStudyWordPair
import com.example.domain.model.PendingWordPair
import com.example.domain.model.StudiedWordPair


interface WordPairRepository {
    suspend fun save(wordPair: OnStudyWordPair)
    suspend fun save(wordPair: PendingWordPair)
    suspend fun save(wordPair: StudiedWordPair)

    suspend fun getOnStudy(): List<OnStudyWordPair>
    suspend fun getPending(): List<PendingWordPair>
    suspend fun getStudied(): List<StudiedWordPair>

    suspend fun isOnStudyListContainsStudiedWords(): Boolean

    suspend fun getOnStudyCount(): Int
    suspend fun getPendingCount(): Int
    suspend fun getStudiedCount(): Int

    fun update(wordPair: OnStudyWordPair)
    fun update(wordPair: PendingWordPair)
    fun update(wordPair: StudiedWordPair)

    fun delete(wordPair: OnStudyWordPair)
    fun delete(wordPair: PendingWordPair)
    fun delete(wordPair: StudiedWordPair)

    suspend fun getPendingMaxId(): Int
    suspend fun getOnStudyMaxId(): Int
    suspend fun getStudiedMaxId(): Int

    fun deleteAll()
}