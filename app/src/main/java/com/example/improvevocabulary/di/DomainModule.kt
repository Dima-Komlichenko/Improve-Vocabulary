package com.example.improvevocabulary.di

import com.example.data.storage.interfaces.LanguageFromLearningStorage
import com.example.data.storage.interfaces.LanguageOfLearningStorage
import com.example.domain.model.Language
import com.example.domain.repositoriesI.*
import com.example.domain.usecase.filter.GetFilterByUseCase
import com.example.domain.usecase.filter.SaveFilterByUseCase
import com.example.domain.usecase.appLanguage.GetAppLanguageUseCase
import com.example.domain.usecase.appLanguage.SaveAppLanguageUseCase
import com.example.domain.usecase.languages.GetLanguageFromLearningUseCase
import com.example.domain.usecase.languages.GetLanguageOfLearningUseCase
import com.example.domain.usecase.languages.SaveLanguageFromLearningUseCase
import com.example.domain.usecase.languages.SaveLanguageOfLearningUseCase
import com.example.domain.usecase.onStudy.*
import com.example.domain.usecase.pending.*
import com.example.domain.usecase.studied.*
import com.example.domain.usecase.theme.GetThemeUseCase
import com.example.domain.usecase.theme.SaveThemeUseCase
import dagger.Module
import dagger.Provides

@Module
class DomainModule {

    @Provides
    fun provideGetLanguageUseCase(languageRepository: AppLanguageRepository): GetAppLanguageUseCase {
        return GetAppLanguageUseCase(languageRepository = languageRepository)
    }

    @Provides
    fun provideLanguageFromRepository(appLanguageStorage: LanguageFromLearningStorage): com.example.data.storage.repositoriesImpl.LanguageFromLearningRepository {
        return com.example.data.storage.repositoriesImpl.LanguageFromLearningRepository(
            languageFromLearningStorage = appLanguageStorage
        )
    }

    @Provides
    fun provideGetLanguageFromLearningUseCase(languageRepository: LanguageFromLearningRepository): GetLanguageFromLearningUseCase {
        return GetLanguageFromLearningUseCase(languageRepository = languageRepository)
    }

    @Provides
    fun provideGetLanguageOfLearningUseCase(languageRepository: LanguageOfLearningRepository): GetLanguageOfLearningUseCase {
        return GetLanguageOfLearningUseCase(languageRepository = languageRepository)
    }

    @Provides
    fun provideSaveLanguageUseCase(languageRepository: AppLanguageRepository): SaveAppLanguageUseCase {
        return SaveAppLanguageUseCase(languageRepository = languageRepository)
    }

    @Provides
    fun provideSaveLanguageFromLearningUseCase(languageRepository: LanguageFromLearningRepository): SaveLanguageFromLearningUseCase {
        return SaveLanguageFromLearningUseCase(languageRepository = languageRepository)
    }

    @Provides
    fun provideSaveLanguageOfLearningUseCase(languageRepository: LanguageOfLearningRepository): SaveLanguageOfLearningUseCase {
        return SaveLanguageOfLearningUseCase(languageRepository = languageRepository)
    }

    @Provides
    fun provideGetThemeUseCase(themeRepository: ThemeRepository): GetThemeUseCase {
        return GetThemeUseCase(themeRepository = themeRepository)
    }

    @Provides
    fun provideSaveThemeUseCase(themeRepository: ThemeRepository): SaveThemeUseCase {
        return SaveThemeUseCase(themeRepository = themeRepository)
    }

    @Provides
    fun provideGetFilterByUseCase(filterByRepository: FilterByRepository): GetFilterByUseCase {
        return GetFilterByUseCase(filterByRepository = filterByRepository)
    }

    @Provides
    fun provideSaveFilterByUseCase(filterByRepository: FilterByRepository): SaveFilterByUseCase {
        return SaveFilterByUseCase(filterByRepository = filterByRepository)
    }

    @Provides
    fun provideGetOnStudyWordPairsUseCase(wordPairRepository: com.example.data.storage.repositoriesImpl.WordPairRepository): GetOnStudyWordPairsUseCase {
        return GetOnStudyWordPairsUseCase(wordPairRepository)
    }

    @Provides
    fun provideGetPendingWordPairsUseCase(wordPairRepository: com.example.data.storage.repositoriesImpl.WordPairRepository): GetPendingWordPairsUseCase {
        return GetPendingWordPairsUseCase(wordPairRepository)
    }

    @Provides
    fun provideSavePendingWordPairUseCase(wordPairRepository: com.example.data.storage.repositoriesImpl.WordPairRepository): SavePendingWordPairUseCase {
        return SavePendingWordPairUseCase(wordPairRepository)
    }

    @Provides
    fun provideRemoveOnStudyWordPairUseCase(wordPairRepository: com.example.data.storage.repositoriesImpl.WordPairRepository): RemoveOnStudyWordPairUseCase {
        return RemoveOnStudyWordPairUseCase(wordPairRepository)
    }

    @Provides
    fun provideIsOnStudyListContainsStudiedWordsUseCase(wordPairRepository: com.example.data.storage.repositoriesImpl.WordPairRepository): IsOnStudyListContainsStudiedWordsUseCase {
        return IsOnStudyListContainsStudiedWordsUseCase(wordPairRepository)
    }

    @Provides
    fun provideRemovePendingWordPairUseCase(wordPairRepository: com.example.data.storage.repositoriesImpl.WordPairRepository): RemovePendingWordPairUseCase {
        return RemovePendingWordPairUseCase(wordPairRepository)
    }

    @Provides
    fun provideClearOnStudyWordPairsUseCase(wordPairRepository: com.example.data.storage.repositoriesImpl.WordPairRepository): ClearOnStudyWordPairsUseCase {
        return ClearOnStudyWordPairsUseCase(wordPairRepository)
    }

    @Provides
    fun provideClearStudiedWordPairsUseCase(wordPairRepository: com.example.data.storage.repositoriesImpl.WordPairRepository): ClearStudiedWordPairsUseCase {
        return ClearStudiedWordPairsUseCase(wordPairRepository)
    }

    @Provides
    fun provideClearPendingWordPairUseCase(wordPairRepository: com.example.data.storage.repositoriesImpl.WordPairRepository): ClearPendingWordPairUseCase {
        return ClearPendingWordPairUseCase(wordPairRepository)
    }

    @Provides
    fun provideUpdatePendingWordPairUseCase(wordPairRepository: com.example.data.storage.repositoriesImpl.WordPairRepository): UpdatePendingWordPairUseCase {
        return UpdatePendingWordPairUseCase(wordPairRepository)
    }

    @Provides
    fun provideLanguage(): Language {
        return Language.ENGLISH
    }

    @Provides
    fun provideSaveStudiedWordPairUseCase(wordPairRepository: com.example.data.storage.repositoriesImpl.WordPairRepository): SaveStudiedWordPairUseCase {
        return SaveStudiedWordPairUseCase(wordPairRepository)
    }
    @Provides
    fun provideUpdateOnStudyWordPairUseCase(wordPairRepository: com.example.data.storage.repositoriesImpl.WordPairRepository): UpdateOnStudyWordPairUseCase {
        return UpdateOnStudyWordPairUseCase(wordPairRepository)
    }

    @Provides
    fun provideRemoveStudiedWordPairUseCase(wordPairRepository: com.example.data.storage.repositoriesImpl.WordPairRepository): RemoveStudiedWordPairUseCase {
        return RemoveStudiedWordPairUseCase(wordPairRepository)
    }

    @Provides
    fun provideSaveOnStudyWordPairUseCase(wordPairRepository: com.example.data.storage.repositoriesImpl.WordPairRepository): SaveOnStudyWordPairUseCase {
        return SaveOnStudyWordPairUseCase(wordPairRepository)
    }

    @Provides
    fun provideGetStudiedWordPairsUseCase(wordPairRepository: com.example.data.storage.repositoriesImpl.WordPairRepository): GetStudiedWordPairsUseCase {
        return GetStudiedWordPairsUseCase(wordPairRepository)
    }

    @Provides
    fun provideGetPendingWordPairCountUseCase(wordPairRepository: com.example.data.storage.repositoriesImpl.WordPairRepository): GetPendingWordPairCountUseCase {
        return GetPendingWordPairCountUseCase(wordPairRepository = wordPairRepository)
    }

    @Provides
    fun provideOnStudyWordPairCountUseCase(wordPairRepository: com.example.data.storage.repositoriesImpl.WordPairRepository): GetOnStudyWordPairCountUseCase {
        return GetOnStudyWordPairCountUseCase(wordPairRepository = wordPairRepository)
    }

    @Provides
    fun provideStudiedWordPairCountUseCase(wordPairRepository: com.example.data.storage.repositoriesImpl.WordPairRepository): GetStudiedWordPairCountUseCase {
        return GetStudiedWordPairCountUseCase(wordPairRepository = wordPairRepository)
    }

    @Provides
    fun provideLanguageFromLearningRepository(languageFromLearningStorage: LanguageFromLearningStorage): LanguageFromLearningRepository {
        return com.example.data.storage.repositoriesImpl.LanguageFromLearningRepository(languageFromLearningStorage = languageFromLearningStorage)
    }

    @Provides
    fun provideLanguageOfLearningRepository(languageOfLearningStorage: LanguageOfLearningStorage): LanguageOfLearningRepository {
        return com.example.data.storage.repositoriesImpl.LanguageOfLearningRepository(languageOfLearningStorage = languageOfLearningStorage)
    }
}