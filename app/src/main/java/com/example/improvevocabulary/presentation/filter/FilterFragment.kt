package com.example.improvevocabulary.presentation.filter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.domain.model.PressedSortButton
import com.example.improvevocabulary.databinding.FragmentFilterBinding
import soup.neumorphism.ShapeType

class FilterFragment : Fragment() {

    private lateinit var binding: FragmentFilterBinding
    private val viewModel: FilterViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFilterBinding.inflate(inflater, container, false)

        viewModel.pressedSortButton.observe(viewLifecycleOwner) { viewModel.saveFilterBy(it) }

        setButtonsListeners()
        //setPressedButton()

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        setPressedButton()
    }

    private fun setPressedButton() {
        when(viewModel.pressedSortButton.value) {
            PressedSortButton.ALPHABETICALLY -> binding.btnAlphabetically.setShapeType(ShapeType.PRESSED)
            PressedSortButton.NON_ALPHABETICALLY -> binding.btnNonAlphabetically.setShapeType(ShapeType.PRESSED)
            PressedSortButton.NEWER -> binding.btnNewer.setShapeType(ShapeType.PRESSED)
            PressedSortButton.OLDER -> binding.btnOlder.setShapeType(ShapeType.PRESSED)
            else -> {}
        }
    }


    private fun setButtonsListeners() {
        binding.apply {
            btnAlphabetically.setOnClickListener {
                it.isPressed = true
                btnAlphabetically.setShapeType(ShapeType.PRESSED)
                btnNonAlphabetically.setShapeType(ShapeType.FLAT)
                btnOlder.setShapeType(ShapeType.FLAT)
                btnNewer.setShapeType(ShapeType.FLAT)

                viewModel.pressedSortButton.value = PressedSortButton.ALPHABETICALLY
            }

            btnNonAlphabetically.setOnClickListener {
                btnAlphabetically.setShapeType(ShapeType.FLAT)
                btnNonAlphabetically.setShapeType(ShapeType.PRESSED)
                btnOlder.setShapeType(ShapeType.FLAT)
                btnNewer.setShapeType(ShapeType.FLAT)

                viewModel.pressedSortButton.value = PressedSortButton.NON_ALPHABETICALLY
            }

            btnNewer.setOnClickListener {
                btnAlphabetically.setShapeType(ShapeType.FLAT)
                btnNonAlphabetically.setShapeType(ShapeType.FLAT)
                btnNewer.setShapeType(ShapeType.PRESSED)
                btnOlder.setShapeType(ShapeType.FLAT)

                viewModel.pressedSortButton.value = PressedSortButton.NEWER
            }

            btnOlder.setOnClickListener {
                btnAlphabetically.setShapeType(ShapeType.FLAT)
                btnNonAlphabetically.setShapeType(ShapeType.FLAT)
                btnNewer.setShapeType(ShapeType.FLAT)
                btnOlder.setShapeType(ShapeType.PRESSED)

                viewModel.pressedSortButton.value = PressedSortButton.OLDER
            }
        }
    }
}