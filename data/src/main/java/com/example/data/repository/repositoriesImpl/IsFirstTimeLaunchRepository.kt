package com.example.data.repository.repositoriesImpl

import com.example.data.storage.interfaces.IsFirstTimeLaunchStorage
import com.example.domain.repositoriesI.IsFirstTimeLaunchRepository

class IsFirstTimeLaunchRepository(var isFirstTimeLaunchStorage: IsFirstTimeLaunchStorage):
    IsFirstTimeLaunchRepository {
    override fun launch() {
        isFirstTimeLaunchStorage.launch()
    }

    override fun get(): Boolean {
        return isFirstTimeLaunchStorage.get()
    }
}