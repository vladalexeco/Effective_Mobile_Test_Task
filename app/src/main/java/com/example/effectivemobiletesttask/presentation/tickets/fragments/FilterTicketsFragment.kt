package com.example.effectivemobiletesttask.presentation.tickets.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.effectivemobiletesttask.databinding.FragmentFilterTicketsBinding

class FilterTicketsFragment : Fragment() {

    private var _binding: FragmentFilterTicketsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFilterTicketsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.filterTicketsCloseIconImageView.setOnClickListener {
            findNavController().popBackStack()
        }
    }
}