package com.example.data.storage.repositoriesImpl

import com.example.data.storage.interfaces.LanguageStorage
import com.example.domain.model.Language
import com.example.domain.repositoriesI.LanguageRepository

class LanguageRepository(var languageStorage: LanguageStorage) : LanguageRepository {

    override fun saveAppLanguage(language: Language): Boolean {
        return languageStorage.save(mapToData(language))
    }

    override fun saveLanguageFrom(language: Language): Boolean {
        TODO("Not yet implemented")
    }

    override fun saveLanguageTo(language: Language): Boolean {
        TODO("Not yet implemented")
    }

    override fun getAppLanguage(): Language {
        return mapToDomain(languageStorage.get()) // converts data.Language class to domain once
    }

    override fun getLanguageFrom(): Language {
        TODO("Not yet implemented")
    }

    override fun getLanguageTo(): Language {
        TODO("Not yet implemented")
    }

    private fun mapToData(domainModel: Language): com.example.data.storage.models.Language {
        return com.example.data.storage.models.Language(domainModel.value)
    }

    private fun mapToDomain(dataModel: com.example.data.storage.models.Language): Language {
        return  Language(dataModel.value)
    }
}