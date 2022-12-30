package com.example.improvevocabulary.di

import android.app.Application
import android.content.Context
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
import com.example.improvevocabulary.presentation.add.AddViewModelFactory
import com.example.improvevocabulary.presentation.filter.FilterViewModelFactory
import com.example.improvevocabulary.presentation.lists.baseList.WordListViewModelFactory
import com.example.improvevocabulary.presentation.lists.onStudyList.OnStudyListViewModelFactory
import com.example.improvevocabulary.presentation.lists.pendingList.PendingListViewModelFactory
import com.example.improvevocabulary.presentation.lists.studiedList.StudiedListViewModelFactory
import com.example.improvevocabulary.presentation.main.MainViewModelFactory
import com.example.improvevocabulary.presentation.settings.SettingsViewModelFactory
import com.example.improvevocabulary.presentation.test.TestViewModelFactory
import com.example.improvevocabulary.presentation.tests.TestsViewModelFactory
import com.example.improvevocabulary.presentation.wordsFragment.WordsFragmentViewModelFactory
import com.example.improvevocabulary.utlis.TextToSpeech
import dagger.Module
import dagger.Provides
import org.w3c.dom.Text

@Module
class AppModule(val context: Context) {

    @Provides
    fun provideContext(): Context {
        return context
    }

    @Provides
    fun provideApplication(): Application {
        return Application()
    }

    @Provides
    fun provideSettingsViewModelFactory(
        getAppLanguageUseCase: GetAppLanguageUseCase,
        saveAppLanguageUseCase: SaveAppLanguageUseCase,
        getThemeUseCase: GetThemeUseCase,
        saveThemeUseCase: SaveThemeUseCase,
        getLanguageFromLearningUseCase: GetLanguageFromLearningUseCase,
        getLanguageOfLearningUseCase: GetLanguageOfLearningUseCase,
        saveLanguageFromLearningUseCase: SaveLanguageFromLearningUseCase,
        saveLanguageOfLearningUseCase: SaveLanguageOfLearningUseCase,
        clearOnStudyWordPairsUseCase: ClearOnStudyWordPairsUseCase,
        clearStudiedWordPairsUseCase: ClearStudiedWordPairsUseCase,
        clearPendingWordPairsUseCase: ClearPendingWordPairUseCase,
    ): SettingsViewModelFactory {
        return SettingsViewModelFactory(
            getAppLanguageUseCase = getAppLanguageUseCase,
            saveAppLanguageUseCase = saveAppLanguageUseCase,
            getThemeUseCase = getThemeUseCase,
            saveThemeUseCase = saveThemeUseCase,
            getLanguageFromLearningUseCase = getLanguageFromLearningUseCase,
            getLanguageOfLearningUseCase = getLanguageOfLearningUseCase,
            saveLanguageFromLearningUseCase = saveLanguageFromLearningUseCase,
            saveLanguageOfLearningUseCase = saveLanguageOfLearningUseCase,
            clearOnStudyWordPairsUseCase = clearOnStudyWordPairsUseCase,
            clearStudiedWordPairsUseCase = clearStudiedWordPairsUseCase,
            clearPendingWordPairsUseCase = clearPendingWordPairsUseCase,
        )
    }

    @Provides
    fun provideFilterByViewModelFactory(
        getFilterByUseCase: GetFilterByUseCase,
        saveFilterByUseCase: SaveFilterByUseCase,
    ): FilterViewModelFactory {
        return FilterViewModelFactory(
            getFilterByUseCase = getFilterByUseCase,
            saveFilterByUseCase = saveFilterByUseCase,
        )
    }

    @Provides
    fun provideMainViewModelFactory(
        getLanguageFromLearningUseCase: GetLanguageFromLearningUseCase,
        getLanguageOfLearningUseCase: GetLanguageOfLearningUseCase,
        getAppLanguageUseCase: GetAppLanguageUseCase,
        saveLanguageFromLearningUseCase: SaveLanguageFromLearningUseCase,
        saveLanguageOfLearningUseCase: SaveLanguageOfLearningUseCase,
        getStudiedWordPairCountUseCase: GetStudiedWordPairCountUseCase,
        getRepetitionWasOfferedUseCase: GetRepetitionWasOfferedUseCase,
        updateRepetitionWasOfferedUseCase: UpdateRepetitionWasOfferedUseCase,
        getIsFirstTimeLaunchUseCase: GetIsFirstTimeLaunchUseCase,
        launchFirstTimeUseCase: LaunchFirstTimeUseCase,
        isOnStudyListContainsStudiedWordsUseCase: IsOnStudyListContainsStudiedWordsUseCase,
    ): MainViewModelFactory {
        return MainViewModelFactory(
            getLanguageFromLearningUseCase = getLanguageFromLearningUseCase,
            getLanguageOfLearningUseCase = getLanguageOfLearningUseCase,
            getAppLanguageUseCase = getAppLanguageUseCase,
            saveLanguageFromLearningUseCase = saveLanguageFromLearningUseCase,
            saveLanguageOfLearningUseCase = saveLanguageOfLearningUseCase,
            getStudiedWordPairCountUseCase = getStudiedWordPairCountUseCase,
            getRepetitionWasOfferedUseCase = getRepetitionWasOfferedUseCase,
            updateRepetitionWasOfferedUseCase = updateRepetitionWasOfferedUseCase,
            getIsFirstTimeLaunchUseCase = getIsFirstTimeLaunchUseCase,
            launchFirstTimeUseCase = launchFirstTimeUseCase,
            isOnStudyListContainsStudiedWordsUseCase = isOnStudyListContainsStudiedWordsUseCase,
        )
    }

    @Provides
    fun provideWordListViewModelFactory(
        getPendingWordPairsUseCase: GetPendingWordPairsUseCase,
        getOnStudyWordPairsUseCase: GetOnStudyWordPairsUseCase,
        getStudiedWordPairsUseCase: GetStudiedWordPairsUseCase,
        getLanguageFromLearningUseCase: GetLanguageFromLearningUseCase,
        getLanguageOfLearningUseCase: GetLanguageOfLearningUseCase,
        tts: TextToSpeech
    ): WordListViewModelFactory {
        return WordListViewModelFactory(
            getPendingWordPairsUseCase = getPendingWordPairsUseCase,
            getOnStudyWordPairsUseCase = getOnStudyWordPairsUseCase,
            getStudiedWordPairsUseCase = getStudiedWordPairsUseCase,
            getLanguageFromLearningUseCase = getLanguageFromLearningUseCase,
            getLanguageOfLearningUseCase = getLanguageOfLearningUseCase,
            tts = tts
        )
    }

    @Provides
    fun provideOnStudyListViewModelFactory(
        updateOnStudyWordPairUseCase: UpdateOnStudyWordPairUseCase,
        removeOnStudyWordPairUseCase: RemoveOnStudyWordPairUseCase,
        saveOnStudyWordPairUseCase: SaveOnStudyWordPairUseCase,
        saveStudiedWordPairUseCase: SaveStudiedWordPairUseCase,
        removeStudiedWordPairUseCase: RemoveStudiedWordPairUseCase,
        getLanguageFromLearningUseCase: GetLanguageFromLearningUseCase,
        getLanguageOfLearningUseCase: GetLanguageOfLearningUseCase,
        getPendingMaxIdUseCase: GetPendingMaxIdUseCase,
        getOnStudyMaxIdUseCase: GetOnStudyMaxIdUseCase,
        getStudMaxIdUseCase: GetPendingMaxIdUseCase,
    ): OnStudyListViewModelFactory {
        return OnStudyListViewModelFactory(
            updateOnStudyWordPairUseCase = updateOnStudyWordPairUseCase,
            removeOnStudyWordPairUseCase = removeOnStudyWordPairUseCase,
            saveOnStudyWordPairUseCase = saveOnStudyWordPairUseCase,
            saveStudiedWordPairUseCase = saveStudiedWordPairUseCase,
            removeStudiedWordPairUseCase = removeStudiedWordPairUseCase,
            getLanguageFromLearningUseCase = getLanguageFromLearningUseCase,
            getLanguageOfLearningUseCase = getLanguageOfLearningUseCase,
            getPendingMaxIdUseCase = getPendingMaxIdUseCase,
            getOnStudyMaxIdUseCase = getOnStudyMaxIdUseCase,
            getStudMaxIdUseCase = getStudMaxIdUseCase,
        )
    }

    @Provides
    fun providePendingListViewModelFactory(
        updatePendingWordPairUseCase: UpdatePendingWordPairUseCase,
        removePendingWordPairUseCase: RemovePendingWordPairUseCase,
        savePendingWordPairUseCase: SavePendingWordPairUseCase,
        saveOnStudyWordPairUseCase: SaveOnStudyWordPairUseCase,
        removeOnStudyWordPairUseCase: RemoveOnStudyWordPairUseCase,
        getOnStudyWordPairCountUseCase: GetOnStudyWordPairCountUseCase,
        getLanguageFromLearningUseCase: GetLanguageFromLearningUseCase,
        getLanguageOfLearningUseCase: GetLanguageOfLearningUseCase,
        getPendingMaxIdUseCase: GetPendingMaxIdUseCase,
        getOnStudyMaxIdUseCase: GetOnStudyMaxIdUseCase,
        getStudMaxIdUseCase: GetPendingMaxIdUseCase,
    ): PendingListViewModelFactory {
        return PendingListViewModelFactory(
            updatePendingWordPairUseCase = updatePendingWordPairUseCase,
            removePendingWordPairUseCase = removePendingWordPairUseCase,
            savePendingWordPairUseCase = savePendingWordPairUseCase,
            saveOnStudyWordPairUseCase = saveOnStudyWordPairUseCase,
            removeOnStudyWordPairUseCase = removeOnStudyWordPairUseCase,
            getOnStudyWordPairCountUseCase = getOnStudyWordPairCountUseCase,
            getLanguageFromLearningUseCase = getLanguageFromLearningUseCase,
            getLanguageOfLearningUseCase = getLanguageOfLearningUseCase,
            getPendingMaxIdUseCase = getPendingMaxIdUseCase,
            getOnStudyMaxIdUseCase = getOnStudyMaxIdUseCase,
            getStudMaxIdUseCase = getStudMaxIdUseCase,
        )
    }

    @Provides
    fun provideStudiedListViewModelFactory(
        removeStudiedWordPairUseCase: RemoveStudiedWordPairUseCase,
        saveOnStudyWordPairUseCase: SaveOnStudyWordPairUseCase,
        saveStudiedWordPairUseCase: SaveStudiedWordPairUseCase,
        removeOnStudyWordPairUseCase: RemoveOnStudyWordPairUseCase,
        getOnStudyWordPairCountUseCase: GetOnStudyWordPairCountUseCase,
    ): StudiedListViewModelFactory {
        return StudiedListViewModelFactory(
            removeStudiedWordPairUseCase = removeStudiedWordPairUseCase,
            saveOnStudyWordPairUseCase = saveOnStudyWordPairUseCase,
            saveStudiedWordPairUseCase = saveStudiedWordPairUseCase,
            removeOnStudyWordPairUseCase = removeOnStudyWordPairUseCase,
            getOnStudyWordPairCountUseCase = getOnStudyWordPairCountUseCase,
        )
    }

    @Provides
    fun provideTestViewModelFactory(
        getOnStudyWordPairsUseCase: GetOnStudyWordPairsUseCase,
        updateOnStudyWordPairsUseCase: UpdateOnStudyWordPairUseCase,
        removeStudiedWordPairUseCase: RemoveStudiedWordPairUseCase,
        getStudiedWordPairsUseCase: GetStudiedWordPairsUseCase,
        saveOnStudyWordPairUseCase: SaveOnStudyWordPairUseCase,
        getLanguageFromLearningUseCase: GetLanguageFromLearningUseCase,
        getLanguageOfLearningUseCase: GetLanguageOfLearningUseCase,
        getOnStudyWordPairCountUseCase: GetOnStudyWordPairCountUseCase,
        getWasTestDescriptionShownUseCase: GetWasTestDescriptionShownUseCase,
        launchWasTestDescriptionShownUseCase: LaunchWasTestDescriptionShownUseCase,
        getWasPracticeDescriptionShownUseCase: GetWasPracticeDescriptionShownUseCase,
        launchWasPracticeDescriptionShownUseCase: LaunchWasPracticeDescriptionShownUseCase,
        tts: TextToSpeech
    ): TestViewModelFactory {
        return TestViewModelFactory(
            getOnStudyWordPairsUseCase = getOnStudyWordPairsUseCase,
            updateOnStudyWordPairsUseCase = updateOnStudyWordPairsUseCase,
            removeStudiedWordPairUseCase = removeStudiedWordPairUseCase,
            getStudiedWordPairsUseCase = getStudiedWordPairsUseCase,
            saveOnStudyWordPairUseCase = saveOnStudyWordPairUseCase,
            getLanguageFromLearningUseCase = getLanguageFromLearningUseCase,
            getLanguageOfLearningUseCase = getLanguageOfLearningUseCase,
            getOnStudyWordPairCountUseCase = getOnStudyWordPairCountUseCase,
            getWasTestDescriptionShownUseCase = getWasTestDescriptionShownUseCase,
            launchWasTestDescriptionShownUseCase = launchWasTestDescriptionShownUseCase,
            getWasPracticeDescriptionShownUseCase = getWasPracticeDescriptionShownUseCase,
            launchWasPracticeDescriptionShownUseCase = launchWasPracticeDescriptionShownUseCase,
            tts = tts,
        )
    }

    @Provides
    fun provideTextToSpeech(context: Context): TextToSpeech {
        return TextToSpeech(context = context,)
    }


    @Provides
    fun provideWordsFragmentViewModelFactory(
        getPendingWordPairCountUseCase: GetPendingWordPairCountUseCase,
        getOnStudyWordPairCountUseCase: GetOnStudyWordPairCountUseCase,
        getStudiedWordPairCountUseCase: GetStudiedWordPairCountUseCase,
        isOnStudyListContainsStudiedWordsUseCase: IsOnStudyListContainsStudiedWordsUseCase,
    ): WordsFragmentViewModelFactory {
        return WordsFragmentViewModelFactory(
            getPendingWordPairCountUseCase = getPendingWordPairCountUseCase,
            getOnStudyWordPairCountUseCase = getOnStudyWordPairCountUseCase,
            getStudiedWordPairCountUseCase = getStudiedWordPairCountUseCase,
            isOnStudyListContainsStudiedWordsUseCase = isOnStudyListContainsStudiedWordsUseCase
        )
    }

    @Provides
    fun provideTestsViewModelFactory(
        getOnStudyWordPairCountUseCase: GetOnStudyWordPairCountUseCase,
        getStudiedWordPairCountUseCase: GetStudiedWordPairCountUseCase,
    ): TestsViewModelFactory {
        return TestsViewModelFactory(
            getOnStudyWordPairCountUseCase = getOnStudyWordPairCountUseCase,
            getStudiedWordPairCountUseCase = getStudiedWordPairCountUseCase
        )
    }

    @Provides
    fun provideAddViewModelFactory(
        getOnStudyWordPairCountUseCase: GetOnStudyWordPairCountUseCase,
        getLanguageFromLearningUseCase: GetLanguageFromLearningUseCase,
        getLanguageOfLearningUseCase: GetLanguageOfLearningUseCase,
    ): AddViewModelFactory {
        return AddViewModelFactory(
            getOnStudyWordPairCountUseCase = getOnStudyWordPairCountUseCase,
            getLanguageFromLearningUseCase = getLanguageFromLearningUseCase,
            getLanguageOfLearningUseCase = getLanguageOfLearningUseCase,
        )
    }
}