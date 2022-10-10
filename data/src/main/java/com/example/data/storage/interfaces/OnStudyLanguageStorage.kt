package com.example.data.storage.interfaces

import com.example.data.storage.models.FilterBy
import com.example.domain.utils.Language

interface OnStudyLanguageStorage {
    fun save(data: Language): Boolean
    fun get(): Language
}