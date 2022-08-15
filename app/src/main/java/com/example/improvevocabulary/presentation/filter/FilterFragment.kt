package com.example.improvevocabulary.presentation.filter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.improvevocabulary.databinding.FragmentFilterBinding

class FilterFragment : Fragment() {

    private lateinit var binding: FragmentFilterBinding
    private lateinit var viewModel: FilterViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this)[FilterViewModel::class.java]
        binding = FragmentFilterBinding.inflate(inflater, container, false)
        setBtnsListeners()
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        when(viewModel.pressedAlphabeticallyButton.value) {
            PressedAlphabeticallyButton.ALPHABETICALLY -> binding.btnAlphabetically.isPressed = true
            PressedAlphabeticallyButton.NON_ALPHABETICALLY -> binding.btnNonAlphabetically.isPressed = true
            else -> {}
        }

        when(viewModel.pressedInOrderButton.value) {
            PressedInOrderButton.NEWER -> binding.btnNewer.isPressed = true
            PressedInOrderButton.OLDER -> binding.btnOlder.isPressed = true
            else -> {}
        }
    }

    private fun setBtnsListeners() {
        binding.apply {
            btnAlphabetically.setOnClickListener {
                it.isPressed = true
                btnNonAlphabetically.isPressed = false
                viewModel.pressedAlphabeticallyButton.value = PressedAlphabeticallyButton.ALPHABETICALLY
            }

            btnNonAlphabetically.setOnClickListener {
                it.isPressed = true
                btnAlphabetically.isPressed = false
                viewModel.pressedAlphabeticallyButton.value = PressedAlphabeticallyButton.NON_ALPHABETICALLY
            }

            //in styles + drawable set pressed view


            btnNewer.setOnClickListener {
                it.isPressed = true
                btnOlder.isPressed = false
                viewModel.pressedInOrderButton.value = PressedInOrderButton.NEWER
            }

            btnNewer.setOnClickListener {
                it.isPressed = true
                btnNewer.isPressed = false
                viewModel.pressedInOrderButton.value = PressedInOrderButton.OLDER
            }
        }
    }
}