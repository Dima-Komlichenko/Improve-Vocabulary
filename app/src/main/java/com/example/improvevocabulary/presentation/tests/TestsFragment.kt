package com.example.improvevocabulary.presentation.tests

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.improvevocabulary.databinding.FragmentTestsBinding

class TestsFragment : Fragment() {

    private lateinit var binding: FragmentTestsBinding
    private lateinit var viewModel: ViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        viewModel = ViewModelProvider(this)[TestsViewModel::class.java]
        binding = FragmentTestsBinding.inflate(inflater, container, false)
        return binding.root
    }

}