package com.example.data.storage.interfaces

import com.example.data.storage.models.FilterBy

interface FilterByStorage {
    fun save(data: FilterBy): Boolean
    fun get(): FilterBy
}