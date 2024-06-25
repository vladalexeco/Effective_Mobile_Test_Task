package com.example.effectivemobiletesttask.presentation.route.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.effectivemobiletesttask.databinding.FragmentSearchRouteBinding

class SearchRouteFragment : Fragment() {

    private var _binding: FragmentSearchRouteBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchRouteBinding.inflate(inflater, container, false)
        return binding.root
    }
}