package com.example.domain.usecase.isFirstTimeLaunch

import com.example.domain.repositoriesI.IsFirstTimeLaunchRepository

class LaunchFirstTimeUseCase(private val isFirstTimeLaunchRepository: IsFirstTimeLaunchRepository) {
    fun execute() {
        isFirstTimeLaunchRepository.launch()
    }
}