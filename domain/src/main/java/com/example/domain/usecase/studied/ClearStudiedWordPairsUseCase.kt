package com.example.domain.usecase.studied

import com.example.domain.repositoriesI.WordPairRepository

class ClearStudiedWordPairsUseCase(private val wordPairRepository: WordPairRepository) {
    fun execute() {
        wordPairRepository.deleteAll()
    }
}