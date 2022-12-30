package com.example.improvevocabulary.presentation.lists.listHeader

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.improvevocabulary.R
import com.example.improvevocabulary.databinding.FragmentListHeaderBinding
import com.example.improvevocabulary.presentation.add.AddFragment
import com.example.improvevocabulary.presentation.add.AddViewModel
import com.example.improvevocabulary.presentation.filter.FilterFragment
import com.example.improvevocabulary.presentation.lists.baseList.WordListViewModel
import com.example.improvevocabulary.presentation.search.SearchFragment
import com.example.improvevocabulary.presentation.test.TypeOfList
import com.example.improvevocabulary.presentation.wordsFragment.WordListInfo
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ListHeaderFragment : Fragment() {

    private lateinit var binding: FragmentListHeaderBinding
    private val viewModel: ListHeaderViewModel by activityViewModels()
    private val wordListViewModel: WordListViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentListHeaderBinding.inflate(inflater, container, false)

        setBtnsListeners()

        if(wordListViewModel.wordListInfo.value == WordListInfo.Studied ||
            wordListViewModel.typeOfList.value == TypeOfList.AfterTest ) {
            binding.btnAdd.visibility = View.GONE
        }

        if(wordListViewModel.wordListInfo.value == WordListInfo.OnStudy &&
            wordListViewModel.typeOfList.value == TypeOfList.AfterTest) {
            binding.btnRepeat.visibility = View.VISIBLE
        }


        return binding.root
    }

    override fun onResume() {
        super.onResume()
        when (viewModel.pressedFilterButtonId.value) {
            PressedFilterButton.FILTER_BTN -> binding.btnFilter.setImageResource(R.drawable.ic_filter_pressed)
            PressedFilterButton.SEARCH_BTN -> binding.btnSearch.setImageResource(R.drawable.ic_search_pressed)
            PressedFilterButton.ADD_BTN -> binding.btnAdd.setImageResource(R.drawable.ic_add_pressed)
            else -> {}
        }
    }

    private fun setBtnsListeners() {
        binding.btnBack.setOnClickListener { activity?.onBackPressed() }

        binding.btnFilter.setOnClickListener {
            var isFragmentTarger: Boolean =
                requireActivity().supportFragmentManager.findFragmentById(binding.extraView.id) is FilterFragment

            viewModel.pressedFilterButtonId.value = PressedFilterButton.FILTER_BTN

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

            viewModel.pressedFilterButtonId.value = PressedFilterButton.SEARCH_BTN

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

            viewModel.pressedFilterButtonId.value = PressedFilterButton.ADD_BTN

            fragmentAction(
                binding.btnAdd,
                R.drawable.ic_add,
                R.drawable.ic_add_pressed,
                isFragmentTarger
            )
        }

        binding.btnRepeat.setOnClickListener {
            viewModel.pressedFilterButtonId.value = PressedFilterButton.REPEAT_BTN
        }
    }

    private fun fragmentAction(imageButton: ImageButton, nonPressedDrawable: Int, pressedDrawable: Int, isFragmentTarger: Boolean) {
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
}