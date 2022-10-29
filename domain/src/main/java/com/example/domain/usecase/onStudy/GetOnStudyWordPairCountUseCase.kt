package com.example.domain.usecase.onStudy

import com.example.domain.repositoriesI.WordPairRepository

class GetOnStudyWordPairCountUseCase(private val wordPairRepository: WordPairRepository) {
    suspend fun execute(): Int {
        return wordPairRepository.getOnStudyCount()
    }
}