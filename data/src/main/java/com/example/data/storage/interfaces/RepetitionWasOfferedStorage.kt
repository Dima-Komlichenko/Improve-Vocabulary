package com.example.data.storage.interfaces

interface RepetitionWasOfferedStorage {
    fun update(day: String)
    fun get(): String
}