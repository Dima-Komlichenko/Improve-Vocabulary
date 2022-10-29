package com.example.improvevocabulary.presentation.main

import android.os.Bundle
import android.os.PersistableBundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.Spinner
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.data.storage.sharedPrefs.*
import com.example.domain.model.Language
import com.example.domain.utils.LanguageConverter
import com.example.improvevocabulary.R
import com.example.improvevocabulary.app.App
import com.example.improvevocabulary.databinding.ActivityMainBinding
import com.example.improvevocabulary.presentation.filter.FilterViewModel
import com.example.improvevocabulary.presentation.filter.FilterViewModelFactory
import com.example.improvevocabulary.presentation.lists.baseList.WordListViewModel
import com.example.improvevocabulary.presentation.lists.baseList.WordListViewModelFactory
import com.example.improvevocabulary.presentation.wordsFragment.WordsFragmentViewModel
import com.example.improvevocabulary.presentation.wordsFragment.WordsFragmentViewModelFactory
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.snackbar.Snackbar
import java.util.*
import javax.inject.Inject

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

        var builder = AlertDialog.Builder(this)
        var view = LayoutInflater.from(this).inflate(R.layout.alert_choose_languages, findViewById<ConstraintLayout>(R.id.alertChooseLanguage))
        builder.setView(view)
        var alertDialog = builder.create()

        var shapredPrefsIsFirstTimeLaunch = SharedPrefsIsFirstTimeLaunch(applicationContext)

        if(!shapredPrefsIsFirstTimeLaunch.get()) return

        alertDialog.show()


        //получить язык системы и сохранить его в from

        var defLang = LanguageConverter.convertCodeToLang(Language(Locale.getDefault().language))
        SharedPrefsLanguageFromLearning(applicationContext).save(getLanguage(defLang.value))

        view.findViewById<Button>(R.id.btn_choose).setOnClickListener {
            //TODO: запретить учить тот язык с которого мы учим
            var strLang =  view.findViewById<Spinner>(R.id.sp_language_of_learning).selectedItem.toString()


            if(SharedPrefsLanguageFromLearning(applicationContext).get() == getLanguage(strLang)) {
                Snackbar.make(
                    binding.root,
                    "You cannot choose this language",
                    Snackbar.LENGTH_SHORT or Snackbar.LENGTH_INDEFINITE
                ).show()
            }
            else {
                SharedPrefsLanguageOfLearning(applicationContext).save(getLanguage(strLang))
                shapredPrefsIsFirstTimeLaunch.launch()
                alertDialog.dismiss()
            }
        }

    }

    private fun getLanguage(strLang: String): com.example.domain.utils.Language {
        return when (strLang) {
            "English" -> com.example.domain.utils.Language.ENGLISH
            "Spanish" -> com.example.domain.utils.Language.SPANISH
            "Ukrainian" -> com.example.domain.utils.Language.UKRAINIAN
            "Russian" -> com.example.domain.utils.Language.RUSSIAN
            else -> com.example.domain.utils.Language.ENGLISH
        }
    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)
        outState.putInt("SelectedItemId", binding.navView.selectedItemId)
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