package com.example.domain.usecase.wereTestsDescShownOnce

import com.example.domain.repositoriesI.WasTestDescriptionShownOnceRepository

class LaunchWasTestDescriptionShownUseCase(private val wasTestDescriptionShownOnceRepository: WasTestDescriptionShownOnceRepository) {
    fun execute() {
        wasTestDescriptionShownOnceRepository.launch()
    }
}