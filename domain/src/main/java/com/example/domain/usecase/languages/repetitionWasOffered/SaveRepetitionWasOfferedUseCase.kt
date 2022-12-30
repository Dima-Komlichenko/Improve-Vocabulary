package com.example.domain.usecase.languages.repetitionWasOffered

import com.example.domain.repositoriesI.RepetitionWasOfferedRepository

class UpdateRepetitionWasOfferedUseCase(private val repetitionWasOfferedRepository: RepetitionWasOfferedRepository) {
    fun execute(day: String) {
        repetitionWasOfferedRepository.update(day)
    }
}