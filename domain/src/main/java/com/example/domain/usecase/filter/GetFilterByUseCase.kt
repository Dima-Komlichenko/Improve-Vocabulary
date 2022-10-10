package com.example.domain.usecase.filter

import com.example.domain.model.FilterBy
import com.example.domain.repositoriesI.FilterByRepository

class GetFilterByUseCase(private val filterByRepository: FilterByRepository) {
    fun execute(): FilterBy {
        return filterByRepository.get()
    }
}