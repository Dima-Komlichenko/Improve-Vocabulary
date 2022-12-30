package com.example.improvevocabulary.presentation.testHeader

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.improvevocabulary.R
import com.example.improvevocabulary.databinding.FragmentListHeaderBinding
import com.example.improvevocabulary.databinding.FragmentTestHeaderBinding

class TestHeaderFragment : Fragment() {

    private lateinit var binding: FragmentTestHeaderBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentTestHeaderBinding.inflate(inflater, container, false)

        binding.btnBack.setOnClickListener { activity?.onBackPressed() }

        return binding.root
    }
}