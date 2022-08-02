package com.example.improvevocabulary.di

import com.example.improvevocabulary.presentation.settings.SettingsFragment
import dagger.Component

@Component(modules = [AppModule::class, DomainModule::class, DataModule::class])
interface AppComponent {
    fun inject(settingsFragment: SettingsFragment)
    //переопределяем методы inject для каждой активити (и возможно фрагмента)
}