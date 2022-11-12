package com.example.domain.usecase.pending

import com.example.domain.model.PendingWordPair
import com.example.domain.repositoriesI.WordPairRepository

class RemovePendingWordPairUseCase(private val wordPairRepository: WordPairRepository) {
    fun execute(wordPair: PendingWordPair) {
        wordPairRepository.delete(wordPair)
    }
}