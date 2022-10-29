package com.example.improvevocabulary.presentation.add

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.data.storage.sharedPrefs.SharedPrefsLanguageFromLearning
import com.example.data.storage.sharedPrefs.SharedPrefsLanguageOfLearning
import com.example.domain.utils.DataValidator
import com.example.improvevocabulary.R
import com.example.improvevocabulary.databinding.FragmentAddBinding
import com.example.improvevocabulary.utlis.DataConverter
import com.google.android.material.snackbar.Snackbar

class AddFragment : Fragment() {

    private val viewModel: AddViewModel by activityViewModels()
    private lateinit var binding: FragmentAddBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        binding = FragmentAddBinding.inflate(inflater, container, false)


        //TODO: вынести sharedPrefs по архитектуре и вынести преобразование в нижний регистр с большой буквы
        binding.etFirstWord.hint =
            DataConverter.capitalize(SharedPrefsLanguageFromLearning(activity?.application!!).get().toString())

        binding.etSecondWord.hint =
            DataConverter.capitalize(SharedPrefsLanguageOfLearning(activity?.application!!).get().toString())

        binding.etFirstWord.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {}
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if (binding.etFirstWord.text.toString() == "") return

                viewModel.firstFieldText.value =
                    DataConverter.convert(binding.etFirstWord.toString())
            }
        })

        binding.etSecondWord.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {}
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if (binding.etSecondWord.text.toString() == "") return

                viewModel.secondFieldText.value =
                    DataConverter.convert(binding.etSecondWord.toString())
            }
        })

        binding.btnSave.setOnClickListener {

            if (viewModel.onStudyCount.value!! >= 20) {
                Snackbar.make(
                    binding.root,
                    resources.getString(R.string.limit_20),
                    Snackbar.LENGTH_SHORT or Snackbar.LENGTH_INDEFINITE
                ).show()
                return@setOnClickListener
            }


            val onStudyLanguage = SharedPrefsLanguageFromLearning(context = requireContext()).get()
            val studiedLanguage = SharedPrefsLanguageOfLearning(context = requireContext()).get()

            if (binding.etFirstWord.text == null || binding.etSecondWord.text == null
                || binding.etFirstWord.text.toString() == "" || binding.etSecondWord.text.toString() == ""
            ) {
                Snackbar.make(
                    binding.root,
                    resources.getString(R.string.word_not_enteted),
                    Snackbar.LENGTH_SHORT or Snackbar.LENGTH_INDEFINITE
                ).show()
                return@setOnClickListener
            }

            if (!DataValidator.isDataValid(binding.etFirstWord.text.toString(), onStudyLanguage) ||
                !DataValidator.isDataValid(binding.etSecondWord.text.toString(), studiedLanguage)
            ) {
                Snackbar.make(
                    binding.root,
                    requireContext().resources.getString(R.string.data_is_not_valid),
                    Snackbar.LENGTH_SHORT or Snackbar.LENGTH_INDEFINITE
                ).show()
                return@setOnClickListener
            }

            binding.etFirstWord.setText("")
            binding.etSecondWord.setText("")

            viewModel.clickBtnSave.value = true
            viewModel.updateOnStudyCount()
        }

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        binding.etFirstWord.setText(viewModel.firstFieldText.value)
        binding.etSecondWord.setText(viewModel.secondFieldText.value)

    }

}