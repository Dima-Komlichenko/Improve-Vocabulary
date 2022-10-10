package com.example.domain.usecase.onStudy

import com.example.domain.model.OnStudyWordPair
import com.example.domain.repositoriesI.WordPairRepository

class GetOnStudyWordPairsUseCase(private val wordPairRepository: WordPairRepository) {
    suspend fun execute(): List<OnStudyWordPair> {
        return wordPairRepository.getOnStudy()
    }
}
