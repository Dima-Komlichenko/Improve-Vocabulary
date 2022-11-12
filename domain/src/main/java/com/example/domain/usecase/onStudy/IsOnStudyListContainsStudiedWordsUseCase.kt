package com.example.domain.usecase.onStudy

import com.example.domain.repositoriesI.WordPairRepository

class IsOnStudyListContainsStudiedWordsUseCase(private val wordPairRepository: WordPairRepository) {
    suspend fun execute(): Boolean {
        return wordPairRepository.IsOnStudyListContainsStudiedWords()
    }
}