package com.example.domain.repositoriesI

import com.example.domain.models.FilterBy

interface FilterByRepository {
    fun save(filterBy: FilterBy): Boolean
    fun get(): FilterBy
}