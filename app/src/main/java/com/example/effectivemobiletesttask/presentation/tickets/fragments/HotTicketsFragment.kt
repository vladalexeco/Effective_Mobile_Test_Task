package com.example.effectivemobiletesttask.presentation.tickets.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.effectivemobiletesttask.databinding.FragmentHotTicketsBinding

class HotTicketsFragment : Fragment() {

    private var _binding: FragmentHotTicketsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHotTicketsBinding.inflate(inflater, container, false)
        return binding.root
    }
}