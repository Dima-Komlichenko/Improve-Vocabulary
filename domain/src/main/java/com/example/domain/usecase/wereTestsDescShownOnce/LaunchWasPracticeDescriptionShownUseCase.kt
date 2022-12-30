package com.example.domain.usecase.wereTestsDescShownOnce

import com.example.domain.repositoriesI.WasPracticeDescriptionShownOnceRepository

class LaunchWasPracticeDescriptionShownUseCase(private val wasPracticeDescriptionShownOnceRepository: WasPracticeDescriptionShownOnceRepository) {
    fun execute() {
        wasPracticeDescriptionShownOnceRepository.launch()
    }
}