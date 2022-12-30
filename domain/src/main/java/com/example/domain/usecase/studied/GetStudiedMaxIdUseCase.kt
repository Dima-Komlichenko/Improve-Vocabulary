package com.example.domain.usecase.studied

import com.example.domain.repositoriesI.WordPairRepository

class GetStudiedMaxIdUseCase(private val wordPairRepository: WordPairRepository) {
    suspend fun execute(): Int {
        return wordPairRepository.getStudiedMaxId()
    }
}