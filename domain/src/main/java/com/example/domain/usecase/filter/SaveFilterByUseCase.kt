package com.example.domain.usecase.filter

import com.example.domain.model.FilterBy
import com.example.domain.repositoriesI.FilterByRepository

class SaveFilterByUseCase(private val filterByRepository: FilterByRepository) {
    fun execute(filterBy: FilterBy) {
        filterByRepository.save(filterBy)
    }
}