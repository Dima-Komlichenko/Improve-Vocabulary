package com.example.domain.usecase

import com.example.domain.models.FilterBy
import com.example.domain.repositoriesI.FilterByRepository

class GetFilterByUseCase(private val filterByRepository: FilterByRepository) {
    fun execute(): FilterBy {
        return filterByRepository.get()
    }
}