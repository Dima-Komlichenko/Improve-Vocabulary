package com.example.domain.repositoriesI

import com.example.domain.model.OnStudyWordPair
import com.example.domain.model.PendingWordPair
import com.example.domain.model.StudiedWordPair

interface WordPairRepository {
    fun save(wordPair: OnStudyWordPair)
    fun save(wordPair: PendingWordPair)
    fun save(wordPair: StudiedWordPair)

    suspend fun getOnStudy(): List<OnStudyWordPair>
    suspend fun getPending(): List<PendingWordPair>
    suspend fun getStudied(): List<StudiedWordPair>

    fun update(wordPair: OnStudyWordPair)
    fun update(wordPair: PendingWordPair)
    fun update(wordPair: StudiedWordPair)

    fun delete(wordPair: OnStudyWordPair)
    fun delete(wordPair: PendingWordPair)
    fun delete(wordPair: StudiedWordPair)

    fun deleteAll()
}