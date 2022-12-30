package com.example.domain.repositoriesI

interface IsFirstTimeLaunchRepository {
    fun launch()
    fun get(): Boolean
}