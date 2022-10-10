package com.example.improvevocabulary.presentation.settings

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.data.storage.sharedPrefs.SharedPrefsLanguageFromLearning
import com.example.data.storage.sharedPrefs.SharedPrefsLanguageOfLearning
import com.example.domain.model.Language
import com.example.domain.model.Theme
import com.example.domain.usecase.onStudy.ClearOnStudyWordPairsUseCase
import com.example.domain.usecase.pending.ClearPendingWordPairUseCase
import com.example.domain.usecase.studied.ClearStudiedWordPairsUseCase
import com.example.domain.utils.LanguageConverter
import com.example.domain.utils.ThemeConverter
import com.example.improvevocabulary.R
import com.example.improvevocabulary.app.App
import com.example.improvevocabulary.databinding.FragmentSettingsBinding
import com.google.android.material.snackbar.Snackbar
import javax.inject.Inject


class SettingsFragment : Fragment() {
    private lateinit var binding: FragmentSettingsBinding
    private lateinit var viewModel: SettingsViewModel

    private lateinit var clearOnStudyWordPairsUseCase: ClearOnStudyWordPairsUseCase
    private lateinit var clearStudiedWordPairsUseCase: ClearStudiedWordPairsUseCase
    private lateinit var clearPendingWordPairsUseCase: ClearPendingWordPairUseCase

    private lateinit var repository: com.example.data.storage.repositoriesImpl.WordPairRepository

    @Inject
    lateinit var settingsViewModelFactory: SettingsViewModelFactory

    private var wasScreenShown = 0

    override fun onAttach(context: Context) {
        super.onAttach(context)
        repository =
            com.example.data.storage.repositoriesImpl.WordPairRepository(activity?.applicationContext as App)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSettingsBinding.inflate(inflater, container, false)
        (activity?.applicationContext as App).appComponent.inject(this)
        viewModel = ViewModelProvider(this, settingsViewModelFactory)[SettingsViewModel::class.java]

        //setLanguagesValue()

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

        clearPendingWordPairsUseCase = ClearPendingWordPairUseCase(repository)
        clearOnStudyWordPairsUseCase = ClearOnStudyWordPairsUseCase(repository)
        clearStudiedWordPairsUseCase = ClearStudiedWordPairsUseCase(repository)


        return binding.root
    }

    override fun onResume() {
        super.onResume()

    }

    private fun setLanguageSpinnersHandler() {
        binding.spLanguageFromLearning?.onItemSelectedListener =
            object : AdapterView.OnItemClickListener,
                AdapterView.OnItemSelectedListener {

                override fun onItemSelected(adapterView: AdapterView<*>?, view: View?, position: Int, id: Long) {

                    if (wasScreenShown < 2) {
                        ++wasScreenShown
                        return
                    }

                    var strLang = binding.spLanguageFromLearning?.selectedItem.toString()

                    var sharedPrefsFrom = SharedPrefsLanguageFromLearning(context!!).get()
                    var indexSharedPrefsFrom = LanguageConverter.convertLangToIndex(sharedPrefsFrom)
                    var indexStrLang = LanguageConverter.convertLangToIndex(getLanguage(strLang))
                    if (indexSharedPrefsFrom == indexStrLang) return

                    AlertDialog.Builder(context)
                        .setTitle(R.string.attention)
                        .setMessage(R.string.after_changing_language)
                        .setPositiveButton(R.string.confirm) { _, _ ->

                            var sharedPrefsOf = SharedPrefsLanguageOfLearning(context!!).get()

                            if (sharedPrefsOf.toString() == getLanguage(strLang).toString()
                                    .uppercase()
                            ) {
                                Snackbar.make(
                                    binding.root,
                                    R.string.impossible_choose,
                                    Snackbar.LENGTH_SHORT or Snackbar.LENGTH_INDEFINITE
                                ).show()
                                return@setPositiveButton
                            }
                            clearOnStudyWordPairsUseCase.execute()
                            clearPendingWordPairsUseCase.execute()
                            clearStudiedWordPairsUseCase.execute()
                            SharedPrefsLanguageFromLearning(requireContext()).save(
                                getLanguage(strLang)
                            )
                            Snackbar.make(
                                binding.root,
                                R.string.language_replaced,
                                Snackbar.LENGTH_SHORT or Snackbar.LENGTH_INDEFINITE
                            ).show()
                        }
                        .setNegativeButton(R.string.cancel) { _, _ -> }
                        .show()
                }

                override fun onItemClick(adapterView: AdapterView<*>?, view: View?, position: Int, id: Long) {}

                override fun onNothingSelected(p0: AdapterView<*>?) {}
            }


        binding.spLanguageOfLearning?.onItemSelectedListener =
            object : AdapterView.OnItemClickListener,
                AdapterView.OnItemSelectedListener {

                override fun onItemSelected(adapterView: AdapterView<*>?, view: View?, position: Int, id: Long) {

                    if (wasScreenShown < 2) {
                        ++wasScreenShown
                        return
                    }

                    var strLang = binding.spLanguageOfLearning?.selectedItem.toString()

                    var sharedPrefsOf = SharedPrefsLanguageOfLearning(context!!).get()
                    var indexSharedPrefsOf = LanguageConverter.convertLangToIndex(sharedPrefsOf)
                    var indexStrLang = LanguageConverter.convertLangToIndex(getLanguage(strLang))
                    if (indexSharedPrefsOf == indexStrLang) return

                    AlertDialog.Builder(context)
                        .setTitle(R.string.attention)
                        .setMessage(R.string.after_changing_language)
                        .setPositiveButton(R.string.confirm) { _, _ ->

                            var a = SharedPrefsLanguageFromLearning(context!!).get()

                            if (a.toString() == getLanguage(strLang).toString().uppercase()) {
                                Snackbar.make(
                                    binding.root,
                                    R.string.impossible_choose,
                                    Snackbar.LENGTH_SHORT or Snackbar.LENGTH_INDEFINITE
                                ).show()
                                return@setPositiveButton
                            }

                            clearStudiedWordPairsUseCase.execute()


                            SharedPrefsLanguageOfLearning(requireContext()).save(
                                getLanguage(strLang)
                            )
                            Snackbar.make(
                                binding.root,
                                R.string.language_replaced,
                                Snackbar.LENGTH_SHORT or Snackbar.LENGTH_INDEFINITE
                            ).show()
                        }
                        .setNegativeButton(R.string.cancel) { _, _ -> }
                        .show()
                }

                override fun onItemClick(adapterView: AdapterView<*>?, view: View?, position: Int, id: Long) {}

                override fun onNothingSelected(p0: AdapterView<*>?) {}
            }
    }


    private fun getLanguage(strLang: String): com.example.domain.utils.Language {
        return when (strLang) {
            "English" -> com.example.domain.utils.Language.ENGLISH
            "Английский" -> com.example.domain.utils.Language.ENGLISH
            "Англійська" -> com.example.domain.utils.Language.ENGLISH

            "Spanish" -> com.example.domain.utils.Language.SPANISH
            "Испанский" -> com.example.domain.utils.Language.SPANISH
            "Іспанська" -> com.example.domain.utils.Language.SPANISH

            "Ukrainian" -> com.example.domain.utils.Language.UKRAINIAN
            "Украинский" -> com.example.domain.utils.Language.UKRAINIAN
            "Українська" -> com.example.domain.utils.Language.UKRAINIAN

            "Russian" -> com.example.domain.utils.Language.RUSSIAN
            "Русский" -> com.example.domain.utils.Language.RUSSIAN
            "Російськиа" -> com.example.domain.utils.Language.RUSSIAN

            else -> com.example.domain.utils.Language.ENGLISH
        }
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        setSpinnersLanguageValue()
        setSpinnerThemeValue()
        //
        setLanguagesValue()
        //
        setLanguageSpinnersHandler()
    }

    private fun setSpinnerThemeValue() {
        val themes = resources.getStringArray(R.array.app_themes)
        val themeEN = viewModel.theme.value
        val lang = resources.configuration.locale.language
        val theme = ThemeConverter.convertThemeNameToCustomLang(themeEN!!, lang)
        binding.spAppTheme.setSelection(themes.indexOf(theme.value))
    }

    private fun setSpinnersLanguageValue(): Int {
        val languages = resources.getStringArray(R.array.languages)
        val lang = LanguageConverter.convertCodeToLang(viewModel.language.value!!)
        //TODO: возможно нужно поменять местами нижние строчки
        binding.spLanguage.setSelection(languages.indexOf(lang.value))
        return languages.indexOf(lang.value)
    }

    private fun setLanguagesValue() {
        var languageFromLearning = SharedPrefsLanguageFromLearning(requireContext()).get()
        var languageOfLearning = SharedPrefsLanguageOfLearning(requireContext()).get()

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

    private fun changeAppThemeHandler() {
        binding.spAppTheme.onItemSelectedListener = object : AdapterView.OnItemClickListener,
            AdapterView.OnItemSelectedListener {

            override fun onItemSelected(
                adapterView: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val themes = resources.getStringArray(R.array.app_themes)
                val theme = ThemeConverter.convertThemeNameToEnglish(Theme(themes[position]))

                if (viewModel.theme.value?.value == theme.value) return

                viewModel.theme.value = theme
                recreateApp()
            }

            override fun onItemClick(
                adapterView: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {}
        }
    }

    private fun changeAppLanguageHandler() {
        binding.spLanguage.onItemSelectedListener = object : AdapterView.OnItemClickListener,
            AdapterView.OnItemSelectedListener {

            override fun onItemSelected(
                adapterView: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val languages = resources.getStringArray(R.array.languages)
                val lang = LanguageConverter.convertLangToCode((Language(languages[position])))

                if (viewModel.language.value?.value == lang.value) return

                viewModel.language.value = lang
                recreateApp()
            }

            override fun onItemClick(
                adapterView: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {}
        }
    }

    private fun recreateApp() {
        activity?.recreate()
    }

}