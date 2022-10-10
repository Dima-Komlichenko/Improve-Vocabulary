package com.example.domain.usecase.pending

import com.example.domain.model.PendingWordPair
import com.example.domain.repositoriesI.WordPairRepository

class GetPendingWordPairsUseCase(private val wordPairRepository: WordPairRepository) {
    suspend fun execute(): List<PendingWordPair> {
        return wordPairRepository.getPending()
    }
}