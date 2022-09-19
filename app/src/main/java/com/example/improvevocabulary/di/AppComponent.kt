package com.example.improvevocabulary.di

import com.example.improvevocabulary.presentation.settings.SettingsFragment
import com.example.improvevocabulary.presentation.wordList.WordsActivity
import dagger.Component


@Component(modules = [AppModule::class, DomainModule::class, DataModule::class])
interface AppComponent {
    fun inject(settingsFragment: SettingsFragment)
    //fun inject(filterFragment: FilterFragment)
    //fun inject(wordListFragment: WordListFragment)
    fun inject(wordsActivity: WordsActivity)
}