package com.example.improvevocabulary.presentation.main

import android.app.ActivityOptions
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Pair
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
import com.example.data.storage.sharedPrefs.SharedPrefsIsFirstTimeLaunch
import com.example.data.storage.sharedPrefs.SharedPrefsThemeStorage
import com.example.domain.model.Language
import com.example.domain.utils.LanguageConverter
import com.example.improvevocabulary.R
import com.example.improvevocabulary.app.App
import com.example.improvevocabulary.databinding.ActivityMainBinding
import com.example.improvevocabulary.presentation.test.TestActivity
import com.example.improvevocabulary.presentation.tests.TypeOfTestInfo
import com.example.improvevocabulary.presentation.tests.TypeOfTestInfoConst
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.snackbar.Snackbar
import java.util.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    @Inject
    lateinit var mainViewModelFactory: MainViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (applicationContext as App).appComponent.inject(this)
        viewModel =
            ViewModelProvider(this, mainViewModelFactory)[MainViewModel::class.java]
        viewModel.init()
        setLanguageInAppConfiguration()
        setThemeInApp()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navView: BottomNavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        navView.setupWithNavController(navController)

        var builder = AlertDialog.Builder(this)


        viewModel.studiedCount.observe(this) {
            if (viewModel.studiedCount.value!! >= 5) {
                val dialogStartRepetition = LayoutInflater.from(this).inflate(
                    R.layout.dialog_start_repetition,
                    findViewById<ConstraintLayout>(R.id.dialog_choose_language)
                )
                builder.setView(dialogStartRepetition)
                    .setPositiveButton(R.string.start) { _, _ ->
                        //TODO: start repetition
                        startRepetition()
                    }
                    .setNegativeButton(R.string.back) { _, _ -> }
                builder.create().show()
            }
        }


        //если первый запуск:
        var shapredPrefsIsFirstTimeLaunch = SharedPrefsIsFirstTimeLaunch(applicationContext)
        if (!shapredPrefsIsFirstTimeLaunch.get()) return

        var dialogChooseLanguage = LayoutInflater.from(this).inflate(
            R.layout.dialog_choose_languages,
            findViewById<ConstraintLayout>(R.id.dialog_start_repetition)
        )
        builder.setView(dialogChooseLanguage)
        var alertDialog = builder.create()

        alertDialog.show()

        dialogChooseLanguage.findViewById<Button>(R.id.btn_choose).setOnClickListener {

            var langFromLearning =
                dialogChooseLanguage.findViewById<Spinner>(R.id.sp_language_from_learning).selectedItem.toString()

            var langOfLearning =
                dialogChooseLanguage.findViewById<Spinner>(R.id.sp_language_of_learning).selectedItem.toString()

            if (langFromLearning == langOfLearning) {
                Snackbar.make(
                    binding.container,
                    resources.getString(R.string.cannot_choose_language),
                    Snackbar.LENGTH_SHORT or Snackbar.LENGTH_INDEFINITE
                ).setAction(R.string.ok) {}
                    .show()
            } else {
                viewModel.saveLanguages(
                    mapToLanguage(langFromLearning),
                    mapToLanguage(langOfLearning)
                )
                shapredPrefsIsFirstTimeLaunch.launch()
                alertDialog.dismiss()
            }
        }

    }

    private fun startRepetition() {
        val intent = Intent(this, TestActivity::class.java)
        val options = ActivityOptions.makeSceneTransitionAnimation(
            this,
            Pair(binding.root.rootView.findViewById(R.id.logo), "logo")
        )
        intent.putExtra(TypeOfTestInfoConst, TypeOfTestInfo.Repetition)
        startActivity(intent, options.toBundle())
    }


    private fun mapToLanguage(strLang: String): Language {
        return when (strLang) {
            "English" -> Language.ENGLISH
            "Spanish" -> Language.SPANISH
            "Ukrainian" -> Language.UKRAINIAN
            "Russian" -> Language.RUSSIAN
            else -> Language.ENGLISH
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
        val langVm = viewModel.appLanguage.value!!
        var locale = Locale(LanguageConverter.convertLangToCode(langVm))
        conf.setLocale(locale)
        res.updateConfiguration(conf, dm)
    }

    private fun setThemeInApp() {
        var theme = SharedPrefsThemeStorage(context = applicationContext).get()
        when (theme.value) {
            "System", "Системная", "Системна" -> AppCompatDelegate.setDefaultNightMode(
                AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
            )
            "Light", "Светлая", "Світла" -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            "Dark", "Темная", "Темна" -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }
    }
}