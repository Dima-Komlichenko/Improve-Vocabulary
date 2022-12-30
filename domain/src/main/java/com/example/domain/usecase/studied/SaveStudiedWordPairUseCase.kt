package com.example.domain.usecase.studied

import com.example.domain.model.StudiedWordPair
import com.example.domain.repositoriesI.WordPairRepository

class SaveStudiedWordPairUseCase(private val wordPairRepository: WordPairRepository) {
    suspend fun execute(wordPair: StudiedWordPair) {
        wordPairRepository.save(wordPair)
    }
}


