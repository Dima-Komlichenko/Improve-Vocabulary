package com.example.improvevocabulary.di

import com.example.improvevocabulary.presentation.add.AddFragment
import com.example.improvevocabulary.presentation.main.MainActivity
import com.example.improvevocabulary.presentation.settings.SettingsFragment
import com.example.improvevocabulary.presentation.test.TestActivity
import com.example.improvevocabulary.presentation.tests.TestsFragment
import com.example.improvevocabulary.presentation.wordList.WordsActivity
import com.example.improvevocabulary.presentation.wordsFragment.WordsFragment
import dagger.Component


@Component(modules = [AppModule::class, DomainModule::class, DataModule::class])
interface AppComponent {
    fun inject(settingsFragment: SettingsFragment)
    fun inject(wordsActivity: WordsActivity)
    fun inject(testActivity: TestActivity)
    fun inject(wordsFragment: WordsFragment)
    fun inject(testsFragment: TestsFragment)
    fun inject(addFragment: AddFragment)
}