package com.example.improvevocabulary.di

import android.app.Application
import android.content.Context
import com.example.domain.usecase.appLanguage.GetAppLanguageUseCase
import com.example.domain.usecase.appLanguage.SaveAppLanguageUseCase
import com.example.domain.usecase.filter.GetFilterByUseCase
import com.example.domain.usecase.filter.SaveFilterByUseCase
import com.example.domain.usecase.onStudy.GetOnStudyWordPairCountUseCase
import com.example.domain.usecase.pending.GetPendingWordPairCountUseCase
import com.example.domain.usecase.studied.GetStudiedWordPairCountUseCase
import com.example.domain.usecase.theme.GetThemeUseCase
import com.example.domain.usecase.theme.SaveThemeUseCase
import com.example.improvevocabulary.presentation.add.AddViewModelFactory
import com.example.improvevocabulary.presentation.filter.FilterViewModelFactory
import com.example.improvevocabulary.presentation.lists.baseList.WordListViewModelFactory
import com.example.improvevocabulary.presentation.settings.SettingsViewModelFactory
import com.example.improvevocabulary.presentation.test.TestViewModelFactory
import com.example.improvevocabulary.presentation.tests.TestsViewModelFactory
import com.example.improvevocabulary.presentation.wordsFragment.WordsFragmentViewModelFactory
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
        saveThemeUseCase: SaveThemeUseCase
    ): SettingsViewModelFactory {
        return SettingsViewModelFactory(
            getAppLanguageUseCase = getAppLanguageUseCase,
            saveAppLanguageUseCase = saveAppLanguageUseCase,
            getThemeUseCase = getThemeUseCase,
            saveThemeUseCase = saveThemeUseCase
        )
    }

    @Provides
    fun provideFilterByViewModelFactory(
        getFilterByUseCase: GetFilterByUseCase,
        saveFilterByUseCase: SaveFilterByUseCase
    ): FilterViewModelFactory {
        return FilterViewModelFactory(
            getFilterByUseCase = getFilterByUseCase,
            saveFilterByUseCase = saveFilterByUseCase
        )
    }

    @Provides
    fun provideWordListViewModelFactory(
        application: Application
    ): WordListViewModelFactory {
        return WordListViewModelFactory(
            application = application
        )
    }

    @Provides
    fun provideTestViewModelFactory(
        application: Application
    ): TestViewModelFactory {
        return TestViewModelFactory(
            application = application
        )
    }

    @Provides
    fun provideWordsFragmentViewModelFactory(
        getPendingWordPairCountUseCase: GetPendingWordPairCountUseCase,
        getOnStudyWordPairCountUseCase: GetOnStudyWordPairCountUseCase,
        getStudiedWordPairCountUseCase: GetStudiedWordPairCountUseCase
    ): WordsFragmentViewModelFactory {
        return WordsFragmentViewModelFactory(
            getPendingWordPairCountUseCase = getPendingWordPairCountUseCase,
            getOnStudyWordPairCountUseCase = getOnStudyWordPairCountUseCase,
            getStudiedWordPairCountUseCase = getStudiedWordPairCountUseCase
        )
    }

    @Provides
    fun provideTestsViewModelFactory(
        getOnStudyWordPairCountUseCase: GetOnStudyWordPairCountUseCase
    ): TestsViewModelFactory {
        return TestsViewModelFactory(
            getOnStudyWordPairCountUseCase = getOnStudyWordPairCountUseCase
        )
    }

    @Provides
    fun provideAddViewModelFactory(
        getOnStudyWordPairCountUseCase: GetOnStudyWordPairCountUseCase
    ): AddViewModelFactory {
        return AddViewModelFactory(
            getOnStudyWordPairCountUseCase = getOnStudyWordPairCountUseCase
        )
    }
}