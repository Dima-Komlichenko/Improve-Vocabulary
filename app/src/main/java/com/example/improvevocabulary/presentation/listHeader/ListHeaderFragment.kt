package com.example.improvevocabulary.presentation.listHeader

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.improvevocabulary.R
import com.example.improvevocabulary.databinding.FragmentListHeaderBinding
import com.example.improvevocabulary.presentation.words.WordsViewModel

class ListHeaderFragment : Fragment() {

    private lateinit var binding: FragmentListHeaderBinding
    private lateinit var viewModel: ListHeaderViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this)[ListHeaderViewModel::class.java]
        binding = FragmentListHeaderBinding.inflate(inflater, container, false)

        return binding.root
    }


}