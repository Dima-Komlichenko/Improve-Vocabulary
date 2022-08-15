package com.example.improvevocabulary.presentation.main

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.data.storage.SharedPrefsLanguageStorage
import com.example.data.storage.SharedPrefsThemeStorage
import com.example.improvevocabulary.R
import com.example.improvevocabulary.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.util.*

val STARTFRAGMENT = "STARTFRAGMENR"

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        setThemeInApp()
        super.onCreate(savedInstanceState)
        setLanguageInAppConfiguration()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navView: BottomNavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        navView.setupWithNavController(navController)
    }

    override fun onSaveInstanceState(savedInstanceState: Bundle) {
        super.onSaveInstanceState(savedInstanceState)
        savedInstanceState.putInt("SelectedItemId", binding.navView.selectedItemId)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        val selectedItemId = savedInstanceState.getInt("SelectedItemId")
        binding.navView.selectedItemId = selectedItemId
    }

    private fun setLanguageInAppConfiguration() {
        val res = resources
        val dm = res.displayMetrics
        val conf = res.configuration
        //I should get data by di
        conf.setLocale(Locale(SharedPrefsLanguageStorage(context = applicationContext).get().value))
        res.updateConfiguration(conf, dm)
    }

    private fun setThemeInApp() {
        var theme = SharedPrefsThemeStorage(context = applicationContext).get()
        when (theme.value) {
            "System", "Системная", "Системна" -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
            "Light", "Светлая", "Світла" -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            "Dark", "Темная", "Темна" -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }
    }
}