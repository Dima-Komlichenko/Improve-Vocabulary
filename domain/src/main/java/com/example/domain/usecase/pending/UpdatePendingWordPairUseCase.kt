package com.example.domain.usecase.pending

import com.example.domain.model.PendingWordPair
import com.example.domain.repositoriesI.WordPairRepository

class UpdatePendingWordPairUseCase(private val wordPairRepository: WordPairRepository) {
    fun execute(wordPair: PendingWordPair) {
        wordPairRepository.update(wordPair)
    }
}