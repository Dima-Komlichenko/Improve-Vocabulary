package com.example.domain.usecase.languages.repetitionWasOffered

import com.example.domain.repositoriesI.RepetitionWasOfferedRepository

class GetRepetitionWasOfferedUseCase(private val repetitionWasOfferedRepository: RepetitionWasOfferedRepository) {
    fun execute(): String {
        return repetitionWasOfferedRepository.get()
    }
}
