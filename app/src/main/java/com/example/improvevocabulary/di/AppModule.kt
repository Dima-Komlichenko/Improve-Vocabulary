package com.example.improvevocabulary.di

import android.app.Application
import android.content.Context
import com.example.domain.model.Language
import com.example.domain.usecase.appLanguage.GetAppLanguageUseCase
import com.example.domain.usecase.appLanguage.SaveAppLanguageUseCase
import com.example.domain.usecase.filter.GetFilterByUseCase
import com.example.domain.usecase.filter.SaveFilterByUseCase
import com.example.domain.usecase.languages.GetLanguageFromLearningUseCase
import com.example.domain.usecase.languages.GetLanguageOfLearningUseCase
import com.example.domain.usecase.languages.SaveLanguageFromLearningUseCase
import com.example.domain.usecase.languages.SaveLanguageOfLearningUseCase
import com.example.domain.usecase.onStudy.*
import com.example.domain.usecase.pending.*
import com.example.domain.usecase.studied.*
import com.example.domain.usecase.theme.GetThemeUseCase
import com.example.domain.usecase.theme.SaveThemeUseCase
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
        getStudiedWordPairCountUseCase: GetStudiedWordPairCountUseCase
    ): MainViewModelFactory {
        return MainViewModelFactory(
            getLanguageFromLearningUseCase = getLanguageFromLearningUseCase,
            getLanguageOfLearningUseCase = getLanguageOfLearningUseCase,
            getAppLanguageUseCase = getAppLanguageUseCase,
            saveLanguageFromLearningUseCase = saveLanguageFromLearningUseCase,
            saveLanguageOfLearningUseCase = saveLanguageOfLearningUseCase,
            getStudiedWordPairCountUseCase = getStudiedWordPairCountUseCase,
        )
    }

    @Provides
    fun provideWordListViewModelFactory(
        getPendingWordPairsUseCase: GetPendingWordPairsUseCase,
        getOnStudyWordPairsUseCase: GetOnStudyWordPairsUseCase,
        getStudiedWordPairsUseCase: GetStudiedWordPairsUseCase,
        getLanguageFromLearningUseCase: GetLanguageFromLearningUseCase,
    ): WordListViewModelFactory {
        return WordListViewModelFactory(
            getPendingWordPairsUseCase = getPendingWordPairsUseCase,
            getOnStudyWordPairsUseCase = getOnStudyWordPairsUseCase,
            getStudiedWordPairsUseCase = getStudiedWordPairsUseCase,
            getLanguageFromLearningUseCase = getLanguageFromLearningUseCase,
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
        getLanguageOfLearningUseCase: GetLanguageOfLearningUseCase
    ): OnStudyListViewModelFactory {
        return OnStudyListViewModelFactory(
            updateOnStudyWordPairUseCase = updateOnStudyWordPairUseCase,
            removeOnStudyWordPairUseCase = removeOnStudyWordPairUseCase,
            saveOnStudyWordPairUseCase = saveOnStudyWordPairUseCase,
            saveStudiedWordPairUseCase = saveStudiedWordPairUseCase,
            removeStudiedWordPairUseCase = removeStudiedWordPairUseCase,
            getLanguageFromLearningUseCase = getLanguageFromLearningUseCase,
            getLanguageOfLearningUseCase = getLanguageOfLearningUseCase
        )
    }

    @Provides
    fun providePendingListViewModelFactory(
        updatePendingWordPairUseCase: UpdatePendingWordPairUseCase,
        removePendingWordPairUseCase: RemovePendingWordPairUseCase,
        savePendingWordPairUseCase: SavePendingWordPairUseCase,
        saveOnStudyWordPairUseCase: SaveOnStudyWordPairUseCase,
        removeOnStudyWordPairUseCase: RemoveOnStudyWordPairUseCase,
        getLanguageFromLearningUseCase: GetLanguageFromLearningUseCase,
        getLanguageOfLearningUseCase: GetLanguageOfLearningUseCase,
    ): PendingListViewModelFactory {
        return PendingListViewModelFactory(
            updatePendingWordPairUseCase = updatePendingWordPairUseCase,
            removePendingWordPairUseCase = removePendingWordPairUseCase,
            savePendingWordPairUseCase = savePendingWordPairUseCase,
            saveOnStudyWordPairUseCase = saveOnStudyWordPairUseCase,
            removeOnStudyWordPairUseCase = removeOnStudyWordPairUseCase,
            getLanguageFromLearningUseCase = getLanguageFromLearningUseCase,
            getLanguageOfLearningUseCase = getLanguageOfLearningUseCase,
        )
    }

    @Provides
    fun provideStudiedListViewModelFactory(
        removeStudiedWordPairUseCase: RemoveStudiedWordPairUseCase,
        saveOnStudyWordPairUseCase: SaveOnStudyWordPairUseCase,
        saveStudiedWordPairUseCase: SaveStudiedWordPairUseCase,
        removeOnStudyWordPairUseCase: RemoveOnStudyWordPairUseCase,
    ): StudiedListViewModelFactory {
        return StudiedListViewModelFactory(
            removeStudiedWordPairUseCase = removeStudiedWordPairUseCase,
            saveOnStudyWordPairUseCase = saveOnStudyWordPairUseCase,
            saveStudiedWordPairUseCase = saveStudiedWordPairUseCase,
            removeOnStudyWordPairUseCase = removeOnStudyWordPairUseCase,
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
            tts = tts
        )
    }

    @Provides
    fun provideTextToSpeech(context: Context, language: Language): TextToSpeech {
        return TextToSpeech(
            context = context,
            language = language,
        )
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