package com.example.data.storage.interfaces

interface WasTestDescriptionShownOnceStorage {
    fun launch()
    fun get(): Boolean
}