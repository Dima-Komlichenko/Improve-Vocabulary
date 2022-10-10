package com.example.domain.usecase.studied

import com.example.domain.model.StudiedWordPair
import com.example.domain.repositoriesI.WordPairRepository

class UpdateStudiedWordPairUseCase(private val wordPairRepository: WordPairRepository) {
    fun execute(wordPair: StudiedWordPair) {
        wordPairRepository.update(wordPair)
    }
}