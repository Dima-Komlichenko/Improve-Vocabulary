package com.example.data.repository.repositoriesImpl

import com.example.data.storage.interfaces.RepetitionWasOfferedStorage
import com.example.domain.repositoriesI.RepetitionWasOfferedRepository

class RepetitionWasOfferedRepository(var repetitionWasOfferedStorage: RepetitionWasOfferedStorage): RepetitionWasOfferedRepository {

    override fun update(day: String) {
        repetitionWasOfferedStorage.update(day)
    }

    override fun get(): String {
        return repetitionWasOfferedStorage.get()
    }
}