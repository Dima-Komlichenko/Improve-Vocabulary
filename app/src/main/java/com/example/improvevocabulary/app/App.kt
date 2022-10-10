package com.example.improvevocabulary.app

import android.app.Application
import android.content.Context
import com.example.improvevocabulary.di.AppComponent
import com.example.improvevocabulary.di.AppModule
import com.example.improvevocabulary.di.DaggerAppComponent


class App: Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder().appModule(AppModule(context = this)).build()
    }
}