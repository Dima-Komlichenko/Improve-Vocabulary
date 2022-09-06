package com.example.improvevocabulary.presentation.listHeader

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.improvevocabulary.R
import com.example.improvevocabulary.databinding.FragmentListHeaderBinding
import com.example.improvevocabulary.presentation.add.AddFragment
import com.example.improvevocabulary.presentation.filter.FilterFragment
import com.example.improvevocabulary.presentation.search.SearchFragment
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ListHeaderFragment : Fragment() {

    private lateinit var binding: FragmentListHeaderBinding
    private lateinit var viewModel: ListHeaderViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this)[ListHeaderViewModel::class.java]
        binding = FragmentListHeaderBinding.inflate(inflater, container, false)

        setBtnsListeners()
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        when (viewModel.pressedButtonId.value) {
            PressedButton.FILTER_BTN -> binding.btnFilter.setImageResource(R.drawable.ic_filter_pressed)
            PressedButton.SEARCH_BTN -> binding.btnSearch.setImageResource(R.drawable.ic_search_pressed)
            PressedButton.ADD_BTN -> binding.btnAdd.setImageResource(R.drawable.ic_add_pressed)
            else -> {}
        }
    }

    private fun setBtnsListeners() {
        binding.btnBack.setOnClickListener { activity?.onBackPressed() }

        binding.btnFilter.setOnClickListener {
            var isFragmentTarger: Boolean =
                requireActivity().supportFragmentManager.findFragmentById(binding.extraView.id) is FilterFragment

            viewModel.pressedButtonId.value = PressedButton.FILTER_BTN

            fragmentAction(
                binding.btnFilter,
                R.drawable.ic_filter,
                R.drawable.ic_filter_pressed,
                isFragmentTarger
            )
        }

        binding.btnSearch.setOnClickListener {
            var isFragmentTarger: Boolean =
                requireActivity().supportFragmentManager.findFragmentById(binding.extraView.id) is SearchFragment

            viewModel.pressedButtonId.value = PressedButton.SEARCH_BTN

            fragmentAction(
                binding.btnSearch,
                R.drawable.ic_search,
                R.drawable.ic_search_pressed,
                isFragmentTarger
            )
        }

        binding.btnAdd.setOnClickListener {
            var isFragmentTarger: Boolean =
                requireActivity().supportFragmentManager.findFragmentById(binding.extraView.id) is AddFragment

            viewModel.pressedButtonId.value = PressedButton.ADD_BTN

            fragmentAction(
                binding.btnAdd,
                R.drawable.ic_add,
                R.drawable.ic_add_pressed,
                isFragmentTarger
            )
        }
    }

    private fun fragmentAction(
        imageButton: ImageButton,
        nonPressedDrawable: Int,
        pressedDrawable: Int,
        isFragmentTarger: Boolean
    ) {
        binding.btnFilter.setImageResource(R.drawable.ic_filter)
        binding.btnSearch.setImageResource(R.drawable.ic_search)
        binding.btnAdd.setImageResource(R.drawable.ic_add)

        val currentFragment =
            requireActivity().supportFragmentManager.findFragmentById(binding.extraView.id)

        val fragment: Fragment? =
            when (imageButton.contentDescription) {
                resources.getString(R.string.btn_filter_desc) -> FilterFragment()
                resources.getString(R.string.btn_search_desc) -> SearchFragment()
                resources.getString(R.string.btn_add_desc) -> AddFragment()
                else -> null
            }

        if (isFragmentTarger) {
            imageButton.setImageResource(nonPressedDrawable)
            replaceExtraFragment(
                R.animator.slide_in_top,
                R.animator.slide_in_top,
                binding.extraView.id,
                fragment!!
            )

            lifecycleScope.launch {
                delay(250)
                removeExtraFragment(R.animator.slide_in_top, R.animator.slide_in_top, fragment!!)
            }

        } else if (currentFragment == null) { // if null
            imageButton.setImageResource(pressedDrawable)
            addExtraFragment(
                R.animator.slide_in_bottom,
                R.animator.slide_in_top,
                binding.extraView.id,
                fragment!!
            )

        } else { // if another
            imageButton.setImageResource(pressedDrawable)
            replaceExtraFragment(
                R.animator.slide_in_bottom_delayed,
                R.animator.slide_in_top,
                binding.extraView.id,
                fragment!!
            )

        }
    }

    private fun removeExtraFragment(enterAnimation: Int, exitAnimation: Int, fragment: Fragment) {
        requireActivity().supportFragmentManager
            .beginTransaction()
            .setCustomAnimations(enterAnimation, exitAnimation)
            .remove(fragment)
            .commit()
    }

    private fun replaceExtraFragment(
        enterAnimation: Int,
        exitAnimation: Int,
        viewId: Int,
        fragment: Fragment
    ) {
        requireActivity().supportFragmentManager
            .beginTransaction()
            .setCustomAnimations(enterAnimation, exitAnimation)
            .replace(viewId, fragment)
            .commit()
    }

    private fun addExtraFragment(
        enterAnimation: Int,
        exitAnimation: Int,
        viewId: Int,
        fragment: Fragment
    ) {
        requireActivity().supportFragmentManager
            .beginTransaction()
            .setCustomAnimations(enterAnimation, exitAnimation)
            .add(viewId, fragment)
            .commit()
    }
    //TODO: animate listHeader extra view's horizontal lines for closing that views

}