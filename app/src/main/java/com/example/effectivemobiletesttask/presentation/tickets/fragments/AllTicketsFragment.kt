package com.example.effectivemobiletesttask.presentation.tickets.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.effectivemobiletesttask.R
import com.example.effectivemobiletesttask.databinding.FragmentAllTicketsBinding
import com.example.effectivemobiletesttask.presentation.tickets.adapters.AllFlyOfferAdapter
import com.example.effectivemobiletesttask.presentation.tickets.adapters.decorators.BottomSpaceItemDecoration
import com.example.effectivemobiletesttask.presentation.tickets.states.AllTicketsScreenEvent
import com.example.effectivemobiletesttask.presentation.tickets.viewmodels.AllTicketsViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class AllTicketsFragment : Fragment() {

    private var _binding: FragmentAllTicketsBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModel<AllTicketsViewModel>()
    private var allFlyOfferAdapter: AllFlyOfferAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAllTicketsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val destination = arguments?.getString(SearchTicketsFragment.DESTINATION_KEY)
        val arrival = arguments?.getString(SearchTicketsFragment.ARRIVAL_KEY)
        val route = "$destination - $arrival"

        binding.allTicketsDepartureArrivalTextView.text = route

        allFlyOfferAdapter = AllFlyOfferAdapter(
            onItemViewClick = { flyOffer ->
                Toast.makeText(requireContext(), "Fly Offer Id ${flyOffer.id}", Toast.LENGTH_SHORT).show()
            }
        )

        binding.allTicketsRecycleView.apply {
            adapter = allFlyOfferAdapter
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            this.addItemDecoration(BottomSpaceItemDecoration(16, requireContext()))
        }

        binding.allTicketsFilterButtonLinearLayout.setOnClickListener {
            findNavController().navigate(R.id.action_allTicketsFragment_to_filterTicketsFragment)
        }

        binding.allTicketsChartButtonLinearLayout.setOnClickListener {
            Toast.makeText(requireContext(), "You click on Chart Button", Toast.LENGTH_SHORT).show()
        }

        binding.allTicketsLeftArrowImageView.setOnClickListener {
            findNavController().popBackStack()
        }

        viewModel.onEvent(allTicketsScreenEvent = AllTicketsScreenEvent.GetAllTicketsEvent)

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.uiState.collect { state ->
                Log.d("FRAGMENT_RESPONSE", "${state.allTicketsList}")
                allFlyOfferAdapter?.setNewFlyOffers(state.allTicketsList)
            }
        }
    }
}