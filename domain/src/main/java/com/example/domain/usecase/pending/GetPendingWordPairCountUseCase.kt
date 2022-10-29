package com.example.domain.usecase.pending

import com.example.domain.repositoriesI.WordPairRepository

class GetPendingWordPairCountUseCase (private val wordPairRepository: WordPairRepository) {
    suspend fun execute(): Int {
        return wordPairRepository.getPendingCount()
    }
}
