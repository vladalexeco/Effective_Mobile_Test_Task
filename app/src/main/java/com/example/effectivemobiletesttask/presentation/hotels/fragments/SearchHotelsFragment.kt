package com.example.effectivemobiletesttask.presentation.hotels.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.effectivemobiletesttask.databinding.FragmentSearchHotelsBinding

class SearchHotelsFragment : Fragment() {

    private var _binding: FragmentSearchHotelsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding =FragmentSearchHotelsBinding.inflate(inflater, container, false)
        return binding.root
    }
}