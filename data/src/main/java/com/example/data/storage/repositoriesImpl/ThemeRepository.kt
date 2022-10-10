package com.example.data.storage.repositoriesImpl

import com.example.data.storage.interfaces.ThemeStorage
import com.example.domain.model.Theme
import com.example.domain.repositoriesI.ThemeRepository

class ThemeRepository(var themeStorage: ThemeStorage): ThemeRepository {
    override fun save(theme: Theme): Boolean {
        return themeStorage.save(mapToData(theme))
    }

    override fun get(): Theme {
        return mapToDomain(themeStorage.get())
    }

    private fun mapToData(domainModel: Theme): com.example.data.storage.models.Theme {
        return com.example.data.storage.models.Theme(domainModel.value)
    }

    private fun mapToDomain(dataModel: com.example.data.storage.models.Theme): Theme {
        return  Theme(dataModel.value)
    }
}