package com.example.improvevocabulary.presentation.main

import android.content.res.Configuration
import android.content.res.Resources
import android.os.Bundle
import android.util.DisplayMetrics
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.data.storage.SharedPrefsLanguageStorage
import com.example.improvevocabulary.R
import com.example.improvevocabulary.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setLanguageInAppConfiguration()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navView: BottomNavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        navView.setupWithNavController(navController)
    }

    private fun setLanguageInAppConfiguration() {
        val res = resources
        val dm = res.displayMetrics
        val conf = res.configuration
        //I should get data by di
        conf.setLocale(Locale(SharedPrefsLanguageStorage(context = applicationContext).get().language))
        res.updateConfiguration(conf, dm)
    }
}