package com.example.data.storage.repositoriesImpl

import com.example.data.storage.interfaces.LanguageFromLearningStorage
import com.example.domain.model.Language

class LanguageFromLearningRepository(var languageFromLearningStorage: LanguageFromLearningStorage)
: com.example.domain.repositoriesI.LanguageFromLearningRepository{

    override fun saveLanguage(language: Language): Boolean {
        return languageFromLearningStorage.save(mapToData(language))
    }

    override fun getLanguage(): Language {
        return mapToDomain(languageFromLearningStorage.get()) // converts data.Language class to domain once
    }

    private fun mapToData(domainModel: Language): com.example.data.storage.models.Language {
        return com.example.data.storage.models.Language(domainModel.toString())
    }

    private fun mapToDomain(dataModel: com.example.data.storage.models.Language): Language {
        return when (dataModel.value) {
            "ENGLISH" -> Language.ENGLISH
            "RUSSIAN" -> Language.RUSSIAN
            "UKRAINIAN" -> Language.UKRAINIAN
            "SPANISH" -> Language.SPANISH
            else -> Language.ENGLISH
        }
    }
}