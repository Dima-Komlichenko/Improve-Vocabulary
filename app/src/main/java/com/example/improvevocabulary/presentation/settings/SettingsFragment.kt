package com.example.improvevocabulary.presentation.settings

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.domain.model.Language
import com.example.domain.model.Languages
import com.example.domain.model.Theme
import com.example.domain.model.Themes
import com.example.domain.utils.LanguageConverter
import com.example.improvevocabulary.R
import com.example.improvevocabulary.app.App
import com.example.improvevocabulary.databinding.FragmentSettingsBinding
import com.example.improvevocabulary.utlis.LinkOpener
import com.google.android.material.snackbar.Snackbar
import java.util.*
import javax.inject.Inject


class SettingsFragment : Fragment() {
    private lateinit var binding: FragmentSettingsBinding
    private lateinit var viewModel: SettingsViewModel

    @Inject
    lateinit var settingsViewModelFactory: SettingsViewModelFactory

    private var wereSpinnersInitialized = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentSettingsBinding.inflate(inflater, container, false)
        (activity?.applicationContext as App).appComponent.inject(this)
        viewModel = ViewModelProvider(this, settingsViewModelFactory)[SettingsViewModel::class.java]
        viewModel.init()


        setLinksHandler()
        setBtnRemoveAllHandler()

        viewModel.language.observe(viewLifecycleOwner) { viewModel.saveLanguage((it)) }
        viewModel.theme.observe(viewLifecycleOwner) { viewModel.saveTheme(it) }

        return binding.root
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {

        setLanguagesValue()
        setAppLanguageSpinner()
        setSpinnerThemeValue()

        setLanguageSpinnersHandler()
        changeAppThemeHandler()
        changeAppLanguageHandler()
        super.onViewStateRestored(savedInstanceState)
    }

    private fun setBtnRemoveAllHandler() {
        binding.btnDeleteAllWords.setOnClickListener {
            viewModel.clearOnStudy()
            viewModel.clearStudied()
            viewModel.clearPending()
        }
    }

    private fun setLinksHandler() {
       binding.tvGoogleLink.setOnClickListener {
           LinkOpener.startGoogle(requireContext())
       }
    }

    private fun setLanguageSpinnersHandler() {
        binding.spLanguageFromLearning.onItemSelectedListener =
            object : AdapterView.OnItemClickListener, AdapterView.OnItemSelectedListener {

                override fun onItemSelected(adapterView: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    Log.i("wereSpinnersInitialized", "spLanguageFromLearning - " + wereSpinnersInitialized)
                    if (wereSpinnersInitialized < 4) {
                        wereSpinnersInitialized++
                        return
                    }

                    val langRes = Languages.values().find { it.ordinal == position }!!
                    val langVm = viewModel.languageFromLearning.value!!.value

                    if (langVm == langRes) return

                    AlertDialog.Builder(context!!)
                        .setTitle(R.string.attention)
                        .setMessage(R.string.after_changing_language)
                        .setPositiveButton(R.string.confirm) { _, _ ->


                            Log.i("wereSpinnersInitialized", "langVm - " + langVm)
                            Log.i("wereSpinnersInitialized", "langRes - " + langRes)


                            viewModel.clearOnStudy()
                            viewModel.clearPending()
                            viewModel.clearStudied()
                            viewModel.saveLanguageFromLearning(Language(langRes))
                            Snackbar.make(
                                binding.root,
                                R.string.language_replaced,
                                Snackbar.LENGTH_SHORT or Snackbar.LENGTH_INDEFINITE
                            )
                                .show()
                        }
                        .setNegativeButton(R.string.cancel) { _, _ ->
                            binding.spLanguageFromLearning.setSelection(
                                Languages.valueOf(viewModel.languageFromLearning.value!!.value.name).ordinal
                            )
                        }
                        .show()
                }

                override fun onItemClick(adapterView: AdapterView<*>?, view: View?, position: Int, id: Long) {}
                override fun onNothingSelected(p0: AdapterView<*>?) {}
            }


        binding.spLanguageOfLearning.onItemSelectedListener =
            object : AdapterView.OnItemClickListener, AdapterView.OnItemSelectedListener {

                override fun onItemSelected(adapterView: AdapterView<*>?, view: View?, position: Int, id: Long) {

                    Log.i("wereSpinnersInitialized", "spLanguageOfLearning - " + wereSpinnersInitialized)
                    if (wereSpinnersInitialized < 4) {
                        wereSpinnersInitialized++
                        return
                    }

                    val langRes = Languages.values().find { it.ordinal == position }!!
                    val langVm = viewModel.languageOfLearning.value!!.value

                    if (langVm == langRes) return

                    AlertDialog.Builder(context!!)
                        .setTitle(R.string.attention)
                        .setMessage(R.string.after_changing_language)
                        .setPositiveButton(R.string.confirm) { _, _ ->


                            viewModel.clearStudied()
                            viewModel.saveLanguageOfLearning(Language(langRes))
                            Snackbar.make(
                                binding.root,
                                R.string.language_replaced,
                                Snackbar.LENGTH_SHORT or Snackbar.LENGTH_INDEFINITE
                            )
                                .show()
                        }
                        .setNegativeButton(R.string.cancel) { _, _ ->
                            binding.spLanguageOfLearning.setSelection(
                                Languages.valueOf(viewModel.languageFromLearning.value!!.value.name).ordinal

                            )
                        }
                        .show()
                }
                override fun onItemClick(adapterView: AdapterView<*>?, view: View?, position: Int, id: Long) {}
                override fun onNothingSelected(p0: AdapterView<*>?) {}
            }
    }




    private fun setAppLanguageSpinner(): Int {
        var langIndex = Languages.valueOf(viewModel.language.value!!.value.name).ordinal

        binding.spLanguage.setSelection(langIndex)

        return langIndex
    }

    private fun changeAppLanguageHandler() {
        binding.spLanguage.onItemSelectedListener = object : AdapterView.OnItemClickListener,
            AdapterView.OnItemSelectedListener {

            override fun onItemSelected(adapterView: AdapterView<*>?, view: View?, position: Int, id: Long) {
                Log.i("wereSpinnersInitialized", "spLanguage - " + wereSpinnersInitialized)
                if (wereSpinnersInitialized < 4) {
                    wereSpinnersInitialized++
                    return
                }

                val langRes = Languages.values().find { it.ordinal == position }!!
                if (viewModel.language.value!!.value == langRes) return
                wereSpinnersInitialized = 0
                viewModel.language.value = Language(langRes)
                val res = resources
                val dm = res.displayMetrics
                val conf = res.configuration
                var locale = Locale(LanguageConverter.convertLangToCode(viewModel.language.value!!))
                conf.setLocale(locale)
                res.updateConfiguration(conf, dm)

                recreateApp()
            }

            override fun onItemClick(adapterView: AdapterView<*>?, view: View?, position: Int, id: Long) {}
            override fun onNothingSelected(p0: AdapterView<*>?) {}
        }
    }

    private fun setSpinnerThemeValue() {
        val theme = viewModel.theme.value
        binding.spAppTheme.setSelection(theme!!.value.ordinal)
    }

    private fun changeAppThemeHandler() {
        binding.spAppTheme.onItemSelectedListener = object : AdapterView.OnItemClickListener,
            AdapterView.OnItemSelectedListener {

            override fun onItemSelected(adapterView: AdapterView<*>?, view: View?, position: Int, id: Long) {
                Log.i("wereSpinnersInitialized", "spAppTheme - " + wereSpinnersInitialized)
                if (wereSpinnersInitialized < 4) {
                    wereSpinnersInitialized++
                    return
                }

                val theme = Themes.values().find { it.ordinal == position }
                Log.i("wereSpinnersInitialized", "viewModel.theme.value?.value - " + viewModel.theme.value?.value)
                Log.i("wereSpinnersInitialized", "theme - " + theme)
                if (viewModel.theme.value?.value == theme) return
                wereSpinnersInitialized = 0
                viewModel.theme.value = Theme(theme!!)
                recreateApp()
            }

            override fun onItemClick(adapterView: AdapterView<*>?, view: View?, position: Int, id: Long) {}
            override fun onNothingSelected(p0: AdapterView<*>?) {}
        }
    }

    private fun setLanguagesValue() {

        var languageFromLearning = viewModel.languageFromLearning.value!!
        var languageOfLearning = viewModel.languageOfLearning.value!!

        binding.spLanguageFromLearning.setSelection(
            Languages.valueOf(languageFromLearning.value.name).ordinal
        )
        binding.spLanguageOfLearning.setSelection(
            Languages.valueOf(languageOfLearning.value.name).ordinal
        )
    }

    private fun recreateApp() {
        activity?.recreate()
    }
}