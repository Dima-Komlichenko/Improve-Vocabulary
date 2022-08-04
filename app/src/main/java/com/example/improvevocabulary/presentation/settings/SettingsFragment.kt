package com.example.improvevocabulary.presentation.settings

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.domain.LanguageConverter
import com.example.domain.models.Language
import com.example.domain.models.Theme
import com.example.improvevocabulary.R
import com.example.improvevocabulary.app.App
import com.example.improvevocabulary.databinding.FragmentSettingsBinding
import com.example.improvevocabulary.presentation.main.MainActivity
import javax.inject.Inject

class SettingsFragment : Fragment() {

    private lateinit var binding: FragmentSettingsBinding
    private lateinit var viewModel: SettingsViewModel

    @Inject
    lateinit var settingsViewModelFactory: SettingsViewModelFactory

    var langSpinnerWasShown = false // allows to change spinners value
    var themeSpinnerWasShown = false // allows to change spinners value

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSettingsBinding.inflate(inflater, container, false)
        (activity?.applicationContext as App).appComponent.inject(this)
        viewModel = ViewModelProvider(this, settingsViewModelFactory)[SettingsViewModel::class.java]

        viewModel.load() // load data from storage to viewModel

        changeAppThemeHandler()
        changeAppLanguageHandler()

        viewModel.language.observe(viewLifecycleOwner) {
            viewModel.saveLanguage((it))
        }

        viewModel.theme.observe(viewLifecycleOwner) { viewModel.saveTheme(it) }
        return binding.root
    }


    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        langSpinnerWasShown = false
        themeSpinnerWasShown = false
        setSpinnerLanguageValue()
        setSpinnerThemeValue()
    }

    private fun setSpinnerThemeValue() {
        var themes = resources.getStringArray(R.array.app_themes)
        var theme = viewModel.theme.value
        binding.spAppTheme.setSelection(themes.indexOf(theme?.value))
    }

    private fun setSpinnerLanguageValue() {
        var languages = resources.getStringArray(R.array.app_languages)
        var lang = LanguageConverter.convertCodeToLang(viewModel.language.value!!)
        binding.spLanguage.setSelection(languages.indexOf(lang.value))
    }

    private fun changeAppThemeHandler() {
        binding.spAppTheme.onItemSelectedListener = object : AdapterView.OnItemClickListener,
            AdapterView.OnItemSelectedListener {

            override fun onItemSelected(adapterView: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if (!themeSpinnerWasShown) { themeSpinnerWasShown = true; return }

                var themes = resources.getStringArray(R.array.app_themes)
                var theme = Theme(themes[position])
                viewModel.theme.value = theme
                recreateApp()
            }
            override fun onItemClick(adapterView: AdapterView<*>?, view: View?, position: Int, id: Long) {}
            override fun onNothingSelected(p0: AdapterView<*>?) {}
        }
    }

    private fun changeAppLanguageHandler() {
        binding.spLanguage.onItemSelectedListener = object : AdapterView.OnItemClickListener,
            AdapterView.OnItemSelectedListener {

            override fun onItemSelected(adapterView: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if (!langSpinnerWasShown) { langSpinnerWasShown = true; return }

                var languages = resources.getStringArray(R.array.app_languages)
                var lang = languages[position]
                viewModel.language.value = LanguageConverter.convertLangToCode(Language(lang))
                recreateApp()
            }
            override fun onItemClick(adapterView: AdapterView<*>?, view: View?, position: Int, id: Long) {}
            override fun onNothingSelected(p0: AdapterView<*>?) {}
        }
    }

    private fun recreateApp() {
        val intent = Intent(activity?.applicationContext, MainActivity::class.java)
        activity?.finish()
        startActivity(intent)
    }

}