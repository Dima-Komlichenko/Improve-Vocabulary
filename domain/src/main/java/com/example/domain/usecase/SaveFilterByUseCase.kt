package com.example.domain.usecase

import com.example.domain.models.FilterBy
import com.example.domain.repositoriesI.FilterByRepository

class SaveFilterByUseCase(private val filterByRepository: FilterByRepository) {
    fun execute(filterBy: FilterBy) {
        filterByRepository.save(filterBy)
    }
}