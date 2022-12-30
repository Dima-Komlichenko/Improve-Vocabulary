package com.example.improvevocabulary.di

import com.example.data.repository.repositoriesImpl.WordPairRepository
import com.example.data.storage.interfaces.LanguageFromLearningStorage
import com.example.data.storage.interfaces.LanguageOfLearningStorage
import com.example.data.storage.interfaces.WasPracticeDescriptionShownOnceStorage
import com.example.data.storage.interfaces.WasTestDescriptionShownOnceStorage
import com.example.domain.model.Language
import com.example.domain.model.Languages
import com.example.domain.repositoriesI.*
import com.example.domain.usecase.appLanguage.GetAppLanguageUseCase
import com.example.domain.usecase.appLanguage.SaveAppLanguageUseCase
import com.example.domain.usecase.filter.GetFilterByUseCase
import com.example.domain.usecase.filter.SaveFilterByUseCase
import com.example.domain.usecase.isFirstTimeLaunch.GetIsFirstTimeLaunchUseCase
import com.example.domain.usecase.isFirstTimeLaunch.LaunchFirstTimeUseCase
import com.example.domain.usecase.languages.GetLanguageFromLearningUseCase
import com.example.domain.usecase.languages.GetLanguageOfLearningUseCase
import com.example.domain.usecase.languages.SaveLanguageFromLearningUseCase
import com.example.domain.usecase.languages.SaveLanguageOfLearningUseCase
import com.example.domain.usecase.onStudy.*
import com.example.domain.usecase.pending.*
import com.example.domain.usecase.languages.repetitionWasOffered.GetRepetitionWasOfferedUseCase
import com.example.domain.usecase.languages.repetitionWasOffered.UpdateRepetitionWasOfferedUseCase
import com.example.domain.usecase.studied.*
import com.example.domain.usecase.theme.GetThemeUseCase
import com.example.domain.usecase.theme.SaveThemeUseCase
import com.example.domain.usecase.wereTestsDescShownOnce.GetWasPracticeDescriptionShownUseCase
import com.example.domain.usecase.wereTestsDescShownOnce.GetWasTestDescriptionShownUseCase
import com.example.domain.usecase.wereTestsDescShownOnce.LaunchWasPracticeDescriptionShownUseCase
import com.example.domain.usecase.wereTestsDescShownOnce.LaunchWasTestDescriptionShownUseCase
import dagger.Module
import dagger.Provides

@Module
class DomainModule {

    @Provides
    fun provideGetWasTestDescriptionShownUseCase(wasTestDescriptionShownOnceRepository: WasTestDescriptionShownOnceRepository)
            : GetWasTestDescriptionShownUseCase {
        return GetWasTestDescriptionShownUseCase(wasTestDescriptionShownOnceRepository = wasTestDescriptionShownOnceRepository)
    }

    @Provides
    fun provideLaunchWasTestDescriptionShownUseCase(wasTestDescriptionShownOnceRepository: WasTestDescriptionShownOnceRepository)
            : LaunchWasTestDescriptionShownUseCase {
        return LaunchWasTestDescriptionShownUseCase(wasTestDescriptionShownOnceRepository = wasTestDescriptionShownOnceRepository)
    }

    @Provides
    fun provideWasTestDescriptionShownOnceRepository(wasTestDescriptionShownOnceStorage: WasTestDescriptionShownOnceStorage)
            :  com.example.data.repository.repositoriesImpl.WasTestDescriptionShownOnceRepository{
        return com.example.data.repository.repositoriesImpl.WasTestDescriptionShownOnceRepository(wasTestDescriptionShownOnceStorage)
    }

    @Provides
    fun provideGetWasPracticeDescriptionShownUseCase(wasPracticeDescriptionShownOnceRepository: WasPracticeDescriptionShownOnceRepository)
            : GetWasPracticeDescriptionShownUseCase {
        return GetWasPracticeDescriptionShownUseCase(wasPracticeDescriptionShownOnceRepository = wasPracticeDescriptionShownOnceRepository)
    }

    @Provides
    fun provideLaunchWasPracticeDescriptionShownUseCase(wasPracticeDescriptionShownOnceRepository: WasPracticeDescriptionShownOnceRepository)
            : LaunchWasPracticeDescriptionShownUseCase {
        return LaunchWasPracticeDescriptionShownUseCase(wasPracticeDescriptionShownOnceRepository = wasPracticeDescriptionShownOnceRepository)
    }

    @Provides
    fun provideWasPracticeDescriptionShownOnceRepository(wasPracticeDescriptionShownOnceStorage: WasPracticeDescriptionShownOnceStorage)
            :  com.example.data.repository.repositoriesImpl.WasPracticeDescriptionShownOnceRepository{
        return com.example.data.repository.repositoriesImpl.WasPracticeDescriptionShownOnceRepository(wasPracticeDescriptionShownOnceStorage)
    }


    @Provides
    fun provideGetLanguageUseCase(languageRepository: AppLanguageRepository): GetAppLanguageUseCase {
        return GetAppLanguageUseCase(languageRepository = languageRepository)
    }

    @Provides
    fun provideGetIsFirstTimeLaunchUseCase(isFirstTimeLaunchRepository: IsFirstTimeLaunchRepository): GetIsFirstTimeLaunchUseCase {
        return GetIsFirstTimeLaunchUseCase(isFirstTimeLaunchRepository = isFirstTimeLaunchRepository)
    }

    @Provides
    fun provideLaunchIsFirstTimeUseCase(isFirstTimeLaunchRepository: IsFirstTimeLaunchRepository): LaunchFirstTimeUseCase {
        return LaunchFirstTimeUseCase(isFirstTimeLaunchRepository = isFirstTimeLaunchRepository)
    }



    @Provides
    fun provideLanguageFromRepository(appLanguageStorage: LanguageFromLearningStorage): com.example.data.repository.repositoriesImpl.LanguageFromLearningRepository {
        return com.example.data.repository.repositoriesImpl.LanguageFromLearningRepository(
            languageFromLearningStorage = appLanguageStorage
        )
    }

    @Provides
    fun provideLanguageFromLearningRepository(languageFromLearningStorage: LanguageFromLearningStorage): LanguageFromLearningRepository {
        return com.example.data.repository.repositoriesImpl.LanguageFromLearningRepository(
            languageFromLearningStorage = languageFromLearningStorage
        )
    }


    @Provides
    fun provideGetRepetitionWasOfferedUseCase(repetitionWasOfferedRepository: RepetitionWasOfferedRepository): GetRepetitionWasOfferedUseCase {
        return GetRepetitionWasOfferedUseCase(
            repetitionWasOfferedRepository = repetitionWasOfferedRepository
        )
    }

    @Provides
    fun provideUpdateRepetitionWasOfferedUseCase(repetitionWasOfferedRepository: RepetitionWasOfferedRepository): UpdateRepetitionWasOfferedUseCase {
        return UpdateRepetitionWasOfferedUseCase(
            repetitionWasOfferedRepository = repetitionWasOfferedRepository
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
    fun provideGetOnStudyWordPairsUseCase(wordPairRepository: WordPairRepository): GetOnStudyWordPairsUseCase {
        return GetOnStudyWordPairsUseCase(wordPairRepository)
    }

    @Provides
    fun provideGetPendingWordPairsUseCase(wordPairRepository: WordPairRepository): GetPendingWordPairsUseCase {
        return GetPendingWordPairsUseCase(wordPairRepository)
    }

    @Provides
    fun provideSavePendingWordPairUseCase(wordPairRepository: WordPairRepository): SavePendingWordPairUseCase {
        return SavePendingWordPairUseCase(wordPairRepository)
    }

    @Provides
    fun provideRemoveOnStudyWordPairUseCase(wordPairRepository: WordPairRepository): RemoveOnStudyWordPairUseCase {
        return RemoveOnStudyWordPairUseCase(wordPairRepository)
    }

    @Provides
    fun provideIsOnStudyListContainsStudiedWordsUseCase(wordPairRepository: WordPairRepository): IsOnStudyListContainsStudiedWordsUseCase {
        return IsOnStudyListContainsStudiedWordsUseCase(wordPairRepository)
    }

    @Provides
    fun provideRemovePendingWordPairUseCase(wordPairRepository: WordPairRepository): RemovePendingWordPairUseCase {
        return RemovePendingWordPairUseCase(wordPairRepository)
    }

    @Provides
    fun provideClearOnStudyWordPairsUseCase(wordPairRepository: WordPairRepository): ClearOnStudyWordPairsUseCase {
        return ClearOnStudyWordPairsUseCase(wordPairRepository)
    }

    @Provides
    fun provideClearStudiedWordPairsUseCase(wordPairRepository: WordPairRepository): ClearStudiedWordPairsUseCase {
        return ClearStudiedWordPairsUseCase(wordPairRepository)
    }

    @Provides
    fun provideClearPendingWordPairUseCase(wordPairRepository: WordPairRepository): ClearPendingWordPairUseCase {
        return ClearPendingWordPairUseCase(wordPairRepository)
    }

    @Provides
    fun provideUpdatePendingWordPairUseCase(wordPairRepository: WordPairRepository): UpdatePendingWordPairUseCase {
        return UpdatePendingWordPairUseCase(wordPairRepository)
    }

    @Provides
    fun provideGetPendingMaxIdUseCase(wordPairRepository: WordPairRepository): GetPendingMaxIdUseCase {
        return GetPendingMaxIdUseCase(wordPairRepository)
    }

    @Provides
    fun provideGetOnStudyMaxIdUseCase(wordPairRepository: WordPairRepository): GetOnStudyMaxIdUseCase {
        return GetOnStudyMaxIdUseCase(wordPairRepository)
    }

    @Provides
    fun provideGetStudiedMaxIdUseCase(wordPairRepository: WordPairRepository): GetStudiedMaxIdUseCase {
        return GetStudiedMaxIdUseCase(wordPairRepository)
    }

    @Provides
    fun provideLanguage(): Language {
        return Language(Languages.ENGLISH)
    }

    @Provides
    fun provideSaveStudiedWordPairUseCase(wordPairRepository: WordPairRepository): SaveStudiedWordPairUseCase {
        return SaveStudiedWordPairUseCase(wordPairRepository)
    }

    @Provides
    fun provideUpdateOnStudyWordPairUseCase(wordPairRepository: WordPairRepository): UpdateOnStudyWordPairUseCase {
        return UpdateOnStudyWordPairUseCase(wordPairRepository)
    }

    @Provides
    fun provideRemoveStudiedWordPairUseCase(wordPairRepository: WordPairRepository): RemoveStudiedWordPairUseCase {
        return RemoveStudiedWordPairUseCase(wordPairRepository)
    }

    @Provides
    fun provideSaveOnStudyWordPairUseCase(wordPairRepository: WordPairRepository): SaveOnStudyWordPairUseCase {
        return SaveOnStudyWordPairUseCase(wordPairRepository)
    }

    @Provides
    fun provideGetStudiedWordPairsUseCase(wordPairRepository: WordPairRepository): GetStudiedWordPairsUseCase {
        return GetStudiedWordPairsUseCase(wordPairRepository)
    }

    @Provides
    fun provideGetPendingWordPairCountUseCase(wordPairRepository: WordPairRepository): GetPendingWordPairCountUseCase {
        return GetPendingWordPairCountUseCase(wordPairRepository = wordPairRepository)
    }

    @Provides
    fun provideOnStudyWordPairCountUseCase(wordPairRepository: WordPairRepository): GetOnStudyWordPairCountUseCase {
        return GetOnStudyWordPairCountUseCase(wordPairRepository = wordPairRepository)
    }

    @Provides
    fun provideStudiedWordPairCountUseCase(wordPairRepository: WordPairRepository): GetStudiedWordPairCountUseCase {
        return GetStudiedWordPairCountUseCase(wordPairRepository = wordPairRepository)
    }


    @Provides
    fun provideLanguageOfLearningRepository(languageOfLearningStorage: LanguageOfLearningStorage): LanguageOfLearningRepository {
        return com.example.data.repository.repositoriesImpl.LanguageOfLearningRepository(
            languageOfLearningStorage = languageOfLearningStorage
        )
    }
}