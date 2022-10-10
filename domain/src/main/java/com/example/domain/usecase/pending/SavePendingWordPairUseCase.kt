package com.example.domain.usecase.pending

import com.example.domain.model.PendingWordPair
import com.example.domain.repositoriesI.WordPairRepository

class SavePendingWordPairUseCase(private val wordPairRepository: WordPairRepository) {
    fun execute(wordPair: PendingWordPair) {
        wordPairRepository.save(wordPair)
    }
}