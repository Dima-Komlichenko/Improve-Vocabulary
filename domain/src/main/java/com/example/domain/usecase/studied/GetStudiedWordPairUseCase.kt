package com.example.domain.usecase.studied

import com.example.domain.model.StudiedWordPair
import com.example.domain.repositoriesI.WordPairRepository

class GetStudiedWordPairsUseCase(private val wordPairRepository: WordPairRepository) {
    suspend fun execute(): List<StudiedWordPair> {
        return wordPairRepository.getStudied()
    }
}