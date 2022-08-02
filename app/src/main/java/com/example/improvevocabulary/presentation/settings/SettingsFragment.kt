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
import com.example.improvevocabulary.R
import com.example.improvevocabulary.app.App
import com.example.improvevocabulary.databinding.FragmentSettingsBinding
import com.example.improvevocabulary.presentation.main.MainActivity
import java.util.*
import javax.inject.Inject

class SettingsFragment : Fragment() {

    private lateinit var binding: FragmentSettingsBinding
    private lateinit var viewModel: SettingsViewModel

    @Inject
    lateinit var settingsViewModelFactory: SettingsViewModelFactory

    var wasViewShown = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSettingsBinding.inflate(inflater, container, false)
        (activity?.applicationContext as App).appComponent.inject(this)
        viewModel = ViewModelProvider(this, settingsViewModelFactory)[SettingsViewModel::class.java]

        viewModel.load() // load data from storage to viewModel


        setSpinnerLanguageValue()
        changeAppLanguageHandler()

        viewModel.language.observe(viewLifecycleOwner) {
            viewModel.saveLanguage((it))
        }
        return binding.root
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        wasViewShown = false
        setSpinnerLanguageValue()
    }

    private fun setSpinnerLanguageValue() {
        var languages = resources.getStringArray(R.array.app_languages)
        var lang = LanguageConverter.convertCodeToLang(viewModel.language.value.toString())
        binding.spLanguage.setSelection(languages.indexOf(lang.language))
    }

    private fun changeAppLanguageHandler() {
        binding.spLanguage.onItemSelectedListener = object : AdapterView.OnItemClickListener,
            AdapterView.OnItemSelectedListener {

            override fun onItemSelected(adapterView: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if (!wasViewShown) { wasViewShown = true; return }

                var languages = resources.getStringArray(R.array.app_languages)
                var lang = languages[position]
                viewModel.language.value = LanguageConverter.convertLangToCode(Language(lang)).language
                setLanguageInAppConfiguration()
            }

            override fun onItemClick(adapterView: AdapterView<*>?, view: View?, position: Int, id: Long) {}

            override fun onNothingSelected(p0: AdapterView<*>?) {}
        }
    }

    private fun setLanguageInAppConfiguration() {
        val res = resources
        val dm = res.displayMetrics
        val conf = res.configuration
        conf.setLocale(Locale(viewModel.language.value))
        res.updateConfiguration(conf, dm)
        recreateApp()
    }

    private fun recreateApp() {
        val intent = Intent(activity?.applicationContext, MainActivity::class.java)
        activity?.finish()
        startActivity(intent)
    }

}