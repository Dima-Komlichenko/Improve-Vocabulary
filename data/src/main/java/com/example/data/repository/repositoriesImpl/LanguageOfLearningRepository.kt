package com.example.data.repository.repositoriesImpl

import com.example.data.storage.interfaces.LanguageOfLearningStorage
import com.example.domain.model.Language

class LanguageOfLearningRepository(var languageOfLearningStorage: LanguageOfLearningStorage)
    : com.example.domain.repositoriesI.LanguageOfLearningRepository{

    override fun saveLanguage(language: Language): Boolean {
        return languageOfLearningStorage.save(mapToData(language))
    }

    override fun getLanguage(): Language {
        return mapToDomain(languageOfLearningStorage.get()) // converts data.Language class to domain once
    }

    private fun mapToData(domainModel: Language): com.example.data.storage.models.Language {
        return com.example.data.storage.models.Language(domainModel.value)
    }

    private fun mapToDomain(dataModel: com.example.data.storage.models.Language): Language {
        return Language(dataModel.value)
    }
}