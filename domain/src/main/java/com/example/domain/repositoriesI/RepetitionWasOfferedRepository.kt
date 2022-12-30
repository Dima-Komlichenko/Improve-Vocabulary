package com.example.domain.repositoriesI

interface RepetitionWasOfferedRepository {
    fun get(): String
    fun update(day: String)
}