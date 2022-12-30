package com.example.data.repository.repositoriesImpl

import com.example.data.storage.interfaces.WasPracticeDescriptionShownOnceStorage

class WasPracticeDescriptionShownOnceRepository(
    private val wasPracticeDescriptionShownOnceStorage: WasPracticeDescriptionShownOnceStorage
) : com.example.domain.repositoriesI.WasPracticeDescriptionShownOnceRepository {

    override fun launch() {
        wasPracticeDescriptionShownOnceStorage.launch()
    }

    override fun get(): Boolean {
        return wasPracticeDescriptionShownOnceStorage.get()
    }
}