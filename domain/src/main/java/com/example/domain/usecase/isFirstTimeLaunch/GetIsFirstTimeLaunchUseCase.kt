package com.example.domain.usecase.isFirstTimeLaunch

import com.example.domain.repositoriesI.IsFirstTimeLaunchRepository

class GetIsFirstTimeLaunchUseCase(private val isFirstTimeLaunchRepository: IsFirstTimeLaunchRepository) {
    fun execute(): Boolean {
        return isFirstTimeLaunchRepository.get()
    }
}