package com.example.data.storage.repositoriesImpl

import com.example.data.storage.interfaces.AppLanguageStorage
import com.example.domain.model.Language
import com.example.domain.repositoriesI.AppLanguageRepository

class AppLanguageRepository(var appLanguageStorage: AppLanguageStorage) :
    AppLanguageRepository {

    override fun saveLanguage(language: Language): Boolean {
        return appLanguageStorage.save(mapToData(language))
    }

    override fun getLanguage(): Language {
        return mapToDomain(appLanguageStorage.get())
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