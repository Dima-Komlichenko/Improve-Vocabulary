package com.example.improvevocabulary.presentation.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.domain.model.Theme
import com.example.domain.utils.LanguageConverter
import com.example.domain.utils.ThemeConverter
import com.example.improvevocabulary.R
import com.example.improvevocabulary.app.App
import com.example.improvevocabulary.databinding.FragmentSettingsBinding
import com.example.improvevocabulary.utlis.DataConverter
import com.google.android.material.snackbar.Snackbar
import javax.inject.Inject


class SettingsFragment : Fragment() {
    private lateinit var binding: FragmentSettingsBinding
    private lateinit var viewModel: SettingsViewModel

    @Inject
    lateinit var settingsViewModelFactory: SettingsViewModelFactory

    private var wasScreenShown = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentSettingsBinding.inflate(inflater, container, false)
        (activity?.applicationContext as App).appComponent.inject(this)
        viewModel = ViewModelProvider(this, settingsViewModelFactory)[SettingsViewModel::class.java]
        viewModel.init()
        setLanguagesValue()
        setAppLanguageSpinner()
        changeAppThemeHandler()
        changeAppLanguageHandler()


        viewModel.language.observe(viewLifecycleOwner) { viewModel.saveLanguage((it)) }
        viewModel.theme.observe(viewLifecycleOwner) { viewModel.saveTheme(it) }

        binding.swSuggestRepetitionOfWords.setOnCheckedChangeListener { _, isChecked ->
            val message =
                if (isChecked) R.string.switch_suggest_repetition_of_words_on
                else R.string.switch_suggest_repetition_of_words_off
            Snackbar.make(binding.clSettings, message, Snackbar.LENGTH_LONG).setAction("Ok") {}
                .show()
        }
        return binding.root
    }

    private fun setLanguageSpinnersHandler() {
        binding.spLanguageFromLearning?.onItemSelectedListener =
            object : AdapterView.OnItemClickListener, AdapterView.OnItemSelectedListener {

                override fun onItemSelected(adapterView: AdapterView<*>?, view: View?, position: Int, id: Long) {

                    if (wasScreenShown < 2) {
                        ++wasScreenShown
                        return
                    }

                    var langRes = binding.spLanguageFromLearning?.selectedItem.toString()

                    var sharedPrefsFrom = viewModel.languageFromLearning.value!!
                    var indexSharedPrefsFrom = LanguageConverter.convertLangToIndex(sharedPrefsFrom)
                    var indexStrLang = LanguageConverter.convertLangToIndex(LanguageConverter.convertCodeToLang(langRes))
                    if (indexSharedPrefsFrom == indexStrLang) return

                    AlertDialog.Builder(context!!)
                        .setTitle(R.string.attention)
                        .setMessage(R.string.after_changing_language)
                        .setPositiveButton(R.string.confirm) { _, _ ->

                            val langVm = DataConverter.capitalize(viewModel.language.value!!.toString())

                            if (LanguageConverter.convertCodeToLang(langVm) == LanguageConverter.convertCodeToLang(langRes)) {
                                Snackbar.make(
                                    binding.root,
                                    R.string.impossible_choose,
                                    Snackbar.LENGTH_SHORT or Snackbar.LENGTH_INDEFINITE
                                ).setAction(R.string.ok) {}
                                    .show()
                                return@setPositiveButton
                            }
                            viewModel.clearOnStudy()
                            viewModel.clearPending()
                            viewModel.clearStudied()
                            viewModel.saveLanguageFromLearning(LanguageConverter.convertCodeToLang(langRes))
                            Snackbar.make(
                                binding.root,
                                R.string.language_replaced,
                                Snackbar.LENGTH_SHORT or Snackbar.LENGTH_INDEFINITE
                            ).setAction(R.string.ok) {}
                                .show()
                        }
                        .setNegativeButton(R.string.cancel) { _, _ -> }
                        .show()
                }

                override fun onItemClick(adapterView: AdapterView<*>?, view: View?, position: Int, id: Long) {}
                override fun onNothingSelected(p0: AdapterView<*>?) {}
            }


        binding.spLanguageOfLearning?.onItemSelectedListener =
            object : AdapterView.OnItemClickListener, AdapterView.OnItemSelectedListener {

                override fun onItemSelected(adapterView: AdapterView<*>?, view: View?, position: Int, id: Long) {

                    if (wasScreenShown < 2) {
                        ++wasScreenShown
                        return
                    }

                    var langRes = binding.spLanguageOfLearning?.selectedItem.toString()

                    var sharedPrefsOf = viewModel.languageOfLearning.value!!
                    var indexSharedPrefsOf = LanguageConverter.convertLangToIndex(sharedPrefsOf)
                    var indexStrLang = LanguageConverter.convertLangToIndex(LanguageConverter.convertCodeToLang(langRes))
                    if (indexSharedPrefsOf == indexStrLang) return

                    AlertDialog.Builder(context!!)
                        .setTitle(R.string.attention)
                        .setMessage(R.string.after_changing_language)
                        .setPositiveButton(R.string.confirm) { _, _ ->

                            val langVm = DataConverter.capitalize(viewModel.language.value!!.toString())

                            if (LanguageConverter.convertCodeToLang(langVm) == LanguageConverter.convertCodeToLang(langRes)) {
                                Snackbar.make(
                                    binding.root,
                                    R.string.impossible_choose,
                                    Snackbar.LENGTH_SHORT or Snackbar.LENGTH_INDEFINITE
                                ).setAction(R.string.ok) {}
                                    .show()
                                return@setPositiveButton
                            }

                            viewModel.clearStudied()

                            viewModel.saveLanguageOfLearning(LanguageConverter.convertCodeToLang(langRes))
                            Snackbar.make(
                                binding.root,
                                R.string.language_replaced,
                                Snackbar.LENGTH_SHORT or Snackbar.LENGTH_INDEFINITE
                            ).setAction(R.string.ok) {}
                                .show()
                        }
                        .setNegativeButton(R.string.cancel) { _, _ -> }
                        .show()
                }
                override fun onItemClick(adapterView: AdapterView<*>?, view: View?, position: Int, id: Long) {}
                override fun onNothingSelected(p0: AdapterView<*>?) {}
            }
    }


    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        viewModel.init()
        setAppLanguageSpinner()
        setSpinnerThemeValue()

        setLanguagesValue()
        setLanguageSpinnersHandler()
    }

    private fun setAppLanguageSpinner(): Int {
        val lang =
            LanguageConverter.convertCodeToLang(DataConverter.capitalize(viewModel.language.value!!.toString()))
        binding.spLanguage.setSelection(LanguageConverter.convertLangToIndex(lang))
        return LanguageConverter.convertLangToIndex(lang)
    }


    private fun setSpinnerThemeValue() {
        val themes = resources.getStringArray(R.array.app_themes)
        val themeEN = viewModel.theme.value
        val lang = resources.configuration.locale.language
        val theme = ThemeConverter.convertThemeNameToCustomLang(themeEN!!, lang)
        binding.spAppTheme.setSelection(themes.indexOf(theme.value))
    }

    private fun changeAppThemeHandler() {
        binding.spAppTheme.onItemSelectedListener = object : AdapterView.OnItemClickListener,
            AdapterView.OnItemSelectedListener {

            override fun onItemSelected(adapterView: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val themes = resources.getStringArray(R.array.app_themes)
                val theme = ThemeConverter.convertThemeNameToEnglish(Theme(themes[position]))

                if (viewModel.theme.value?.value == theme.value) return

                viewModel.theme.value = theme
                recreateApp()
            }

            override fun onItemClick(adapterView: AdapterView<*>?, view: View?, position: Int, id: Long) {}
            override fun onNothingSelected(p0: AdapterView<*>?) {}
        }
    }

    private fun setLanguagesValue() {

        var languageFromLearning = viewModel.languageFromLearning.value!!
        var languageOfLearning = viewModel.languageOfLearning.value!!

        binding.spLanguageFromLearning?.setSelection(
            LanguageConverter.convertLangToIndex(
                languageFromLearning
            )
        )
        binding.spLanguageOfLearning?.setSelection(
            LanguageConverter.convertLangToIndex(
                languageOfLearning
            )
        )
    }


    private fun changeAppLanguageHandler() {
        binding.spLanguage.onItemSelectedListener = object : AdapterView.OnItemClickListener,
            AdapterView.OnItemSelectedListener {

            override fun onItemSelected(adapterView: AdapterView<*>?, view: View?, position: Int, id: Long) {
                viewModel.init()
                val languages = resources.getStringArray(R.array.languages)

                val langRes = languages[position]
                val langVm = DataConverter.capitalize(viewModel.language.value!!.toString())

                if (LanguageConverter.convertCodeToLang(langVm) == LanguageConverter.convertCodeToLang(langRes)) return

                viewModel.language.value = LanguageConverter.convertCodeToLang(langRes)
                recreateApp()
            }

            override fun onItemClick(adapterView: AdapterView<*>?, view: View?, position: Int, id: Long) {}
            override fun onNothingSelected(p0: AdapterView<*>?) {}
        }
    }

    private fun recreateApp() {
        activity?.recreate()
    }

}