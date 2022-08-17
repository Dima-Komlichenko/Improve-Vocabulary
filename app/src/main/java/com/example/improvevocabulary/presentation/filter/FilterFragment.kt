package com.example.improvevocabulary.presentation.filter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.domain.models.PressedSortButton
import com.example.improvevocabulary.app.App
import com.example.improvevocabulary.databinding.FragmentFilterBinding
import com.example.improvevocabulary.presentation.settings.SettingsViewModel
import soup.neumorphism.ShapeType
import javax.inject.Inject

class FilterFragment : Fragment() {

    private lateinit var binding: FragmentFilterBinding
    private lateinit var viewModel: FilterViewModel

    @Inject
    lateinit var filterViewModelFactory: FilterViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFilterBinding.inflate(inflater, container, false)
        (activity?.applicationContext as App).appComponent.inject(this)
        viewModel = ViewModelProvider(this, filterViewModelFactory)[FilterViewModel::class.java]
        viewModel.load()
        viewModel.pressedSortButton.observe(viewLifecycleOwner) { viewModel.saveFilterBy(it) }

        setButtonsListeners()

        return binding.root
    }

    override fun onResume() {
        super.onResume()
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