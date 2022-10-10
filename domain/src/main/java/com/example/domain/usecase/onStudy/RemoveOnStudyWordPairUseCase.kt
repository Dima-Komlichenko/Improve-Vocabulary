package com.example.domain.usecase.onStudy

import com.example.domain.model.OnStudyWordPair
import com.example.domain.repositoriesI.WordPairRepository

class RemoveOnStudyWordPairUseCase(private val wordPairRepository: WordPairRepository) {
    fun execute(wordPair: OnStudyWordPair) {
        wordPairRepository.delete(wordPair)
    }
}