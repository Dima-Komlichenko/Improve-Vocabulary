package com.example.domain.usecase.wereTestsDescShownOnce

import com.example.domain.repositoriesI.WasPracticeDescriptionShownOnceRepository

class GetWasPracticeDescriptionShownUseCase(private val wasPracticeDescriptionShownOnceRepository: WasPracticeDescriptionShownOnceRepository) {
    fun execute(): Boolean {
        return wasPracticeDescriptionShownOnceRepository.get()
    }
}