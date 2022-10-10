package com.example.domain.usecase.onStudy

import com.example.domain.repositoriesI.WordPairRepository

class ClearOnStudyWordPairsUseCase(private val wordPairRepository: WordPairRepository) {
    fun execute() {
        wordPairRepository.deleteAll()
    }
}