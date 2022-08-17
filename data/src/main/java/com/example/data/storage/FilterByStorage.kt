package com.example.data.storage

import com.example.data.storage.models.FilterBy

interface FilterByStorage {
    fun save(data: FilterBy): Boolean
    fun get(): FilterBy
}