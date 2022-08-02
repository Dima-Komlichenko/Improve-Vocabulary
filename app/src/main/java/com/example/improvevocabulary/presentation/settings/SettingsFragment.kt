package com.example.improvevocabulary.presentation.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.improvevocabulary.R
import com.example.improvevocabulary.databinding.FragmentSettingsBinding

class SettingsFragment : Fragment() {

    private lateinit var binding: FragmentSettingsBinding
    private lateinit var viewModel: SettingsViewModel
    lateinit var viewModelFactory: SettingsViewModelFactory

    var wasViewShown = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentSettingsBinding.inflate(inflater, container, false)



        //use di to inicialialize viewModel


        //viewModel.load() // load data from storage to viewModel
        //setSpinnerLanguageValue()


        //changeAppLanguageHandler()

        //viewModel.language.observe(viewLifecycleOwner) {
        //    viewModel.saveLanguage((it))
        //}
        return binding.root
    }

    private fun setSpinnerLanguageValue() {
        var languages = resources.getStringArray(R.array.app_languages)
        binding.spLanguage.setSelection(languages.indexOf(viewModel.language.value))
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        wasViewShown = false
    }

    private fun changeAppLanguageHandler() {
        binding.spLanguage.onItemSelectedListener = object : AdapterView.OnItemClickListener,
            AdapterView.OnItemSelectedListener {

            override fun onItemSelected(adapterView: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if (!wasViewShown) { wasViewShown = true; return } // if we are firstly here not change viewModel state

                var languages = resources.getStringArray(R.array.app_languages)
                var lang = languages[position]
                viewModel.language.value = lang
            }

            override fun onItemClick(adapterView: AdapterView<*>?, view: View?, position: Int, id: Long) {}
            override fun onNothingSelected(p0: AdapterView<*>?) {}
        }
    }

}