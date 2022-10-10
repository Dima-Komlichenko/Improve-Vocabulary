package com.example.domain.usecase.pending

import com.example.domain.repositoriesI.WordPairRepository

class ClearPendingWordPairUseCase(private val wordPairRepository: WordPairRepository) {
    fun execute() {
        wordPairRepository.deleteAll()
    }
}