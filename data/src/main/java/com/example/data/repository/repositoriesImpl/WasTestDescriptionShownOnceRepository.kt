package com.example.data.repository.repositoriesImpl

import com.example.data.storage.interfaces.WasTestDescriptionShownOnceStorage
import com.example.domain.repositoriesI.WasTestDescriptionShownOnceRepository

class WasTestDescriptionShownOnceRepository(
    private val wasTestDescriptionShownOnceStorage: WasTestDescriptionShownOnceStorage
): WasTestDescriptionShownOnceRepository {

    override fun launch() {
        wasTestDescriptionShownOnceStorage.launch()
    }

    override fun get(): Boolean {
        return wasTestDescriptionShownOnceStorage.get()
    }
}