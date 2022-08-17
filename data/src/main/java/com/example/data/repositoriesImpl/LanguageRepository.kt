package com.example.data.repositoriesImpl

import com.example.data.storage.LanguageStorage
import com.example.domain.models.Language
import com.example.domain.repositoriesI.LanguageRepository

class LanguageRepository(var languageStorage: LanguageStorage) : LanguageRepository {

    override fun save(language: Language): Boolean {
        return languageStorage.save(mapToData(language))
    }

    override fun get(): Language {
        return mapToDomain(languageStorage.get()) // converts data.Language class to domain once
    }

    private fun mapToData(domainModel: Language): com.example.data.storage.models.Language {
        return com.example.data.storage.models.Language(domainModel.value)
    }

    private fun mapToDomain(dataModel: com.example.data.storage.models.Language): Language {
        return  Language(dataModel.value)
    }
}