package com.example.improvevocabulary.app

import android.app.Application
import android.content.res.Configuration
import android.content.res.Resources
import android.util.DisplayMetrics
import com.example.data.storage.SharedPrefsLanguageStorage
import com.example.improvevocabulary.di.AppComponent
import com.example.improvevocabulary.di.AppModule
import com.example.improvevocabulary.di.DaggerAppComponent
import java.util.*


class App: Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder().appModule(AppModule(context = this)).build()

    }
}