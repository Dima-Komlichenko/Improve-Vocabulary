package com.example.domain.usecase.studied

import com.example.domain.model.StudiedWordPair
import com.example.domain.repositoriesI.WordPairRepository

class GetStudiedWordPairCountUseCase(private val wordPairRepository: WordPairRepository) {
    suspend fun execute(): Int {
        return wordPairRepository.getStudiedCount()
    }
}