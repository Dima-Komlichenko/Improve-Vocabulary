package com.example.improvevocabulary.presentation.listHeader

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.improvevocabulary.R
import com.example.improvevocabulary.databinding.FragmentListHeaderBinding
import com.example.improvevocabulary.presentation.add.AddFragment
import com.example.improvevocabulary.presentation.filter.FilterFragment
import com.example.improvevocabulary.presentation.search.SearchFragment

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

            binding.btnSearch.setImageResource(R.drawable.ic_search)
            binding.btnAdd.setImageResource(R.drawable.ic_add)

            viewModel.pressedButtonId.value = PressedButton.FILTER_BTN

            val currentFragment =
                activity!!.supportFragmentManager.findFragmentById(binding.extraView.id)

            val fragment = FilterFragment()

            if (currentFragment is FilterFragment) { // if filter
                binding.btnFilter.setImageResource(R.drawable.ic_filter)

                activity!!.supportFragmentManager
                    .beginTransaction()
                    .setCustomAnimations(R.animator.slide_in_top, R.animator.slide_in_top)
                    .replace(binding.extraView.id, fragment)
                    .commit()

                Handler().postDelayed({
                    activity!!.supportFragmentManager
                        .beginTransaction()
                        .setCustomAnimations(R.animator.slide_in_top, R.animator.slide_in_top)
                        .remove(fragment)
                        .commit()
                }, 250)
            } else if (currentFragment == null) { // if null
                binding.btnFilter.setImageResource(R.drawable.ic_filter_pressed)
                activity!!.supportFragmentManager
                    .beginTransaction()
                    .setCustomAnimations(
                        R.animator.slide_in_bottom,
                        R.animator.slide_in_top
                    )
                    .add(binding.extraView.id, fragment)
                    .commit()
            } else { // if another
                binding.btnFilter.setImageResource(R.drawable.ic_filter_pressed)
                activity!!.supportFragmentManager
                    .beginTransaction()
                    .setCustomAnimations(R.animator.slide_in_bottom_delayed, R.animator.slide_in_top)
                    .replace(binding.extraView.id, fragment)
                    .commit()
            }
        }

        binding.btnSearch.setOnClickListener {
            binding.btnFilter.setImageResource(R.drawable.ic_filter)
            binding.btnAdd.setImageResource(R.drawable.ic_add)
            viewModel.pressedButtonId.value = PressedButton.SEARCH_BTN

            val currentFragment =
                activity!!.supportFragmentManager.findFragmentById(binding.extraView.id)

            val fragment = SearchFragment()

            if (currentFragment is SearchFragment) { // if filter
                binding.btnSearch.setImageResource(R.drawable.ic_search)

                activity!!.supportFragmentManager
                    .beginTransaction()
                    .setCustomAnimations(R.animator.slide_in_top, R.animator.slide_in_top)
                    .replace(binding.extraView.id, fragment)
                    .commit()

                Handler().postDelayed({
                    activity!!.supportFragmentManager
                        .beginTransaction()
                        .setCustomAnimations(R.animator.slide_in_top, R.animator.slide_in_top)
                        .remove(fragment)
                        .commit()
                }, 250)
            } else if (currentFragment == null) { // if null
                binding.btnSearch.setImageResource(R.drawable.ic_search_pressed)
                activity!!.supportFragmentManager
                    .beginTransaction()
                    .setCustomAnimations(
                        R.animator.slide_in_bottom,
                        R.animator.slide_in_top
                    )
                    .add(binding.extraView.id, fragment)
                    .commit()
            } else { // if another
                binding.btnSearch.setImageResource(R.drawable.ic_search_pressed)
                activity!!.supportFragmentManager
                    .beginTransaction()
                    .setCustomAnimations(R.animator.slide_in_bottom_delayed, R.animator.slide_in_top)
                    .replace(binding.extraView.id, fragment)
                    .commit()
            }
        }

        binding.btnAdd.setOnClickListener {
            binding.btnFilter.setImageResource(R.drawable.ic_filter)
            binding.btnSearch.setImageResource(R.drawable.ic_search)

            viewModel.pressedButtonId.value = PressedButton.ADD_BTN

            val currentFragment =
                activity!!.supportFragmentManager.findFragmentById(binding.extraView.id)

            val fragment = AddFragment()

            if (currentFragment is AddFragment) { // if filter
                binding.btnAdd.setImageResource(R.drawable.ic_add)

                activity!!.supportFragmentManager
                    .beginTransaction()
                    .setCustomAnimations(R.animator.slide_in_top, R.animator.slide_in_top)
                    .replace(binding.extraView.id, fragment)
                    .commit()

                Handler().postDelayed({
                    activity!!.supportFragmentManager
                        .beginTransaction()
                        .setCustomAnimations(R.animator.slide_in_top, R.animator.slide_in_top)
                        .remove(fragment)
                        .commit()
                }, 250)
            } else if (currentFragment == null) { // if null
                binding.btnAdd.setImageResource(R.drawable.ic_add_pressed)
                activity!!.supportFragmentManager
                    .beginTransaction()
                    .setCustomAnimations(
                        R.animator.slide_in_bottom,
                        R.animator.slide_in_top
                    )
                    .add(binding.extraView.id, fragment)
                    .commit()
            } else { // if another
                binding.btnAdd.setImageResource(R.drawable.ic_add_pressed)
                activity!!.supportFragmentManager
                    .beginTransaction()
                    .setCustomAnimations(R.animator.slide_in_bottom_delayed, R.animator.slide_in_top)
                    .replace(binding.extraView.id, fragment)
                    .commit()
            }
        }
    }


}