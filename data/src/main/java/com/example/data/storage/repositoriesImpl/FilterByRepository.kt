package com.example.data.storage.repositoriesImpl

import com.example.data.storage.interfaces.FilterByStorage
import com.example.data.storage.models.PressedSortButton
import com.example.domain.model.FilterBy
import com.example.domain.repositoriesI.FilterByRepository

class FilterByRepository(var filterByStorage: FilterByStorage) : FilterByRepository {

    override fun save(filterBy: FilterBy): Boolean {
        return filterByStorage.save(mapToData(filterBy))
    }

    override fun get(): FilterBy {
        return mapToDomain(filterByStorage.get()) // converts data.Language class to domain once
    }

    private fun mapToData(domainModel: FilterBy): com.example.data.storage.models.FilterBy {
        return com.example.data.storage.models.FilterBy(PressedSortButton.valueOf(domainModel.value.toString()))
    }

    private fun mapToDomain(dataModel: com.example.data.storage.models.FilterBy): FilterBy {
        return FilterBy(com.example.domain.model.PressedSortButton.valueOf(dataModel.value.toString()))
    }
}