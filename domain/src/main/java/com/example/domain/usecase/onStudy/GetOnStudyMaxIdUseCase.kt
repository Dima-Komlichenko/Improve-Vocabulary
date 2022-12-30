package com.example.domain.usecase.onStudy

import com.example.domain.repositoriesI.WordPairRepository

class GetOnStudyMaxIdUseCase(private val wordPairRepository: WordPairRepository) {
    suspend fun execute(): Int {
       return wordPairRepository.getOnStudyMaxId()
    }
}