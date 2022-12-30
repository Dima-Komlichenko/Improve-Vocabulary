package com.example.improvevocabulary.presentation.main

import android.app.ActivityOptions
import android.content.Intent
import android.content.res.Configuration
import android.os.Build
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.util.Pair
import android.view.LayoutInflater
import android.widget.Button
import android.widget.Spinner
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.view.get
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.data.storage.sharedPrefs.SharedPrefsThemeStorage
import com.example.domain.model.Language
import com.example.domain.model.Languages
import com.example.domain.model.Themes
import com.example.domain.utils.LanguageConverter
import com.example.improvevocabulary.R
import com.example.improvevocabulary.app.App
import com.example.improvevocabulary.databinding.ActivityMainBinding
import com.example.improvevocabulary.presentation.test.TestActivity
import com.example.improvevocabulary.presentation.tests.TypeOfTestInfo
import com.example.improvevocabulary.presentation.tests.TypeOfTestInfoConst
import com.example.improvevocabulary.utlis.DateTimePicker
import com.google.android.gms.ads.MobileAds
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
        (applicationContext as App).appComponent.inject(this)
        viewModel =
            ViewModelProvider(this, mainViewModelFactory)[MainViewModel::class.java]
        viewModel.init()

        setLanguageInAppConfiguration()
        setThemeInApp()
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)
        val navView: BottomNavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        navView.setupWithNavController(navController)


        viewModel.isOnStudyListContainsStudiedWords.observe(this) {
            if (it)
                navView.menu[0].icon = ContextCompat.getDrawable(this, R.drawable.ic_home_i)
        }

        //повторение в новые сутки
        var builder = AlertDialog.Builder(this)

        val day = viewModel.repetitionWasOffered.value!!
        var dtp = DateTimePicker(day)

        if (!dtp.wasRepetitionOfferedToday()) {

            viewModel.studiedCount.observe(this) {
                if (viewModel.studiedCount.value!! >= 5) {
                    val dialogStartRepetition = LayoutInflater.from(this).inflate(
                        R.layout.dialog_start_repetition,
                        findViewById<ConstraintLayout>(R.id.dialog_choose_language)
                    )
                    builder.setView(dialogStartRepetition)
                        .setPositiveButton(R.string.start) { _, _ ->
                            startRepetition()
                            viewModel.updateRepetitionWasOffered(dtp.getCurrentDay())
                        }
                        .setNegativeButton(R.string.later) { _, _ -> }
                    builder.create().show()
                }
            }
        }

        //если первый запуск:
        if (!viewModel.isFirstTimeLaunch.value!!) return

        var dialogChooseLanguage = LayoutInflater.from(this).inflate(
            R.layout.dialog_choose_languages,
            findViewById<ConstraintLayout>(R.id.dialog_start_repetition)
        )
        builder.setView(dialogChooseLanguage)
        var alertDialog = builder.create()

        alertDialog.show()

        dialogChooseLanguage.findViewById<Button>(R.id.btn_choose).setOnClickListener {

            var langFromLearning: Languages =
                dialogChooseLanguage.findViewById<Spinner>(R.id.sp_language_from_learning).selectedItem as Languages

            var langOfLearning: Languages =
                dialogChooseLanguage.findViewById<Spinner>(R.id.sp_language_of_learning).selectedItem as Languages

            if (langFromLearning == langOfLearning) {
                Snackbar.make(
                    binding.container,
                    resources.getString(R.string.cannot_choose_language),
                    Snackbar.LENGTH_SHORT or Snackbar.LENGTH_INDEFINITE
                )
                    .show()
            } else {
                viewModel.saveLanguages(Language(langFromLearning), Language(langOfLearning))
                viewModel.launchAppFirstTime()
                alertDialog.dismiss()
            }
        }

        MobileAds.initialize(this)
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
        Log.i("appLang",  "MAIN. viewModel.appLanguage.value!! = " + langVm.value.name)
        var locale = Locale(LanguageConverter.convertLangToCode(langVm))
        conf.setLocale(locale)
        Log.i("appLang",  "MAIN.conf.setLocale(locale)" + locale.language)
        res.updateConfiguration(conf, dm)
    }

    private fun setThemeInApp() {
        var theme = SharedPrefsThemeStorage(context = applicationContext).get()
        when (theme.value) {
            Themes.SYSTEM -> AppCompatDelegate.setDefaultNightMode(
                AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
            )
            Themes.LIGHT -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            Themes.DARK -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }
    }
}