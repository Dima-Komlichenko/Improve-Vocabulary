package com.example.domain.repositoriesI

import com.example.domain.model.FilterBy

interface FilterByRepository {
    fun save(filterBy: FilterBy): Boolean
    fun get(): FilterBy
}