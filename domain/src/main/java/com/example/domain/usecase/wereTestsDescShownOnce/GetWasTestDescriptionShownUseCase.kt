package com.example.domain.usecase.wereTestsDescShownOnce

import com.example.domain.repositoriesI.WasTestDescriptionShownOnceRepository

class GetWasTestDescriptionShownUseCase(private val wasTestDescriptionShownOnceRepository: WasTestDescriptionShownOnceRepository) {
    fun execute(): Boolean {
        return wasTestDescriptionShownOnceRepository.get()
    }
}