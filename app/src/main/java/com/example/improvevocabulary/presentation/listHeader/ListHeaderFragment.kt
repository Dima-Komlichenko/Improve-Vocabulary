package com.example.improvevocabulary.presentation.listHeader

import android.os.Bundle
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
    ): View? {
        viewModel = ViewModelProvider(this)[ListHeaderViewModel::class.java]
        binding = FragmentListHeaderBinding.inflate(inflater, container, false)
        setBtnsListeners()
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        when(viewModel.pressedButtonId.value) {
            PressedButton.FILTER_BTN -> binding.btnFilter.setImageResource(R.drawable.ic_filter_pressed)
            PressedButton.SEARCH_BTN -> binding.btnSearch.setImageResource(R.drawable.ic_search_pressed)
            PressedButton.ADD_BTN -> binding.btnAdd.setImageResource(R.drawable.ic_add_pressed)
            else -> {}
        }
    }

    private fun setBtnsListeners() {
        binding.btnBack.setOnClickListener { activity?.finish() }

        binding.btnFilter.setOnClickListener {
            binding.btnFilter.setImageResource(R.drawable.ic_filter_pressed)
            binding.btnSearch.setImageResource(R.drawable.ic_search)
            binding.btnAdd.setImageResource(R.drawable.ic_add)
            viewModel.pressedButtonId.value = PressedButton.FILTER_BTN

            val fragment = FilterFragment()
            activity!!.supportFragmentManager
                .beginTransaction()
                .replace(binding.extraView.id, fragment)
                .commit()
        }

        binding.btnSearch.setOnClickListener {
            binding.btnFilter.setImageResource(R.drawable.ic_filter)
            binding.btnSearch.setImageResource(R.drawable.ic_search_pressed)
            binding.btnAdd.setImageResource(R.drawable.ic_add)
            viewModel.pressedButtonId.value = PressedButton.SEARCH_BTN

            val fragment = SearchFragment()
            activity!!.supportFragmentManager
                .beginTransaction()
                .replace(binding.extraView.id, fragment)
                .commit()
        }

        binding.btnAdd.setOnClickListener {
            binding.btnFilter.setImageResource(R.drawable.ic_filter)
            binding.btnSearch.setImageResource(R.drawable.ic_search)
            binding.btnAdd.setImageResource(R.drawable.ic_add_pressed)
            viewModel.pressedButtonId.value = PressedButton.ADD_BTN

            val fragment = AddFragment()
            activity!!.supportFragmentManager
                .beginTransaction()
                .replace(binding.extraView.id, fragment)
                .commit()
        }
    }


}