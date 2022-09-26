package com.example.improvevocabulary.presentation.add

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.improvevocabulary.R
import com.example.improvevocabulary.databinding.FragmentAddBinding
import com.example.improvevocabulary.databinding.FragmentFilterBinding
import com.example.improvevocabulary.presentation.filter.FilterViewModel
import com.google.android.material.snackbar.Snackbar

class AddFragment : Fragment() {

    private val viewModel: AddViewModel by activityViewModels()
    private lateinit var binding: FragmentAddBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentAddBinding.inflate(inflater, container, false)

        binding.etFirstWord.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {}
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                viewModel.firstFieldText.value = binding.etFirstWord.text.toString()
            }
        })

        binding.etSecondWord.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {}
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                viewModel.secondFieldText.value = binding.etSecondWord.text.toString()
            }
        })

        binding.btnSave.setOnClickListener {
            if(binding.etFirstWord.text == null || binding.etSecondWord.text == null
                || binding.etFirstWord.text.toString() == "" || binding.etSecondWord.text.toString() == "") {
                Snackbar.make(
                    binding.root,
                    resources.getString(R.string.word_not_enteted),
                    Snackbar.LENGTH_SHORT or Snackbar.LENGTH_INDEFINITE
                ).show()
            }

            binding.etFirstWord.setText("")
            binding.etSecondWord.setText("")

            viewModel.clickBtnSave.value = true
        }

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        binding.etFirstWord.setText(viewModel.firstFieldText.value)
        binding.etSecondWord.setText(viewModel.secondFieldText.value)

    }

}