package com.example.data.storage.interfaces

interface IsFirstTimeLaunchStorage {
    fun launch()
    fun get(): Boolean
}