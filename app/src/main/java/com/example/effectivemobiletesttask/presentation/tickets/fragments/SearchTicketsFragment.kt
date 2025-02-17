package com.example.effectivemobiletesttask.presentation.tickets.fragments

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.os.Bundle
import android.text.InputFilter
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.domain.model.BestFlyOffer
import com.example.domain.model.MusicalFlyOffer
import com.example.effectivemobiletesttask.R
import com.example.effectivemobiletesttask.databinding.FragmentSearchTicketsBinding
import com.example.effectivemobiletesttask.presentation.tickets.adapters.BestFlyOfferAdapter
import com.example.effectivemobiletesttask.presentation.tickets.adapters.MusicalFlyOfferAdapter
import com.example.effectivemobiletesttask.presentation.tickets.adapters.decorators.RightSpaceItemDecoration
import com.example.effectivemobiletesttask.presentation.tickets.states.SearchTicketsScreenEvent
import com.example.effectivemobiletesttask.presentation.tickets.viewmodels.SearchTicketsViewModel
import com.google.android.material.bottomsheet.BottomSheetBehavior
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class SearchTicketsFragment : Fragment() {

    private var _binding: FragmentSearchTicketsBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModel<SearchTicketsViewModel>()
    private var musicalFlyOffersAdapter: MusicalFlyOfferAdapter? = null
    private var bestFlyOffersAdapter: BestFlyOfferAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchTicketsBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("ClickableViewAccessibility", "SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.onEvent(searchTicketsEvent = SearchTicketsScreenEvent.GetDataFromStorageEvent(
            key = DATA_STORAGE_KEY
        ))

        val bottomSheetBehavior = BottomSheetBehavior.from(binding.searchTicketsBottomSheet)

        binding.searchTicketsCurrentDateTextView.text = getCurrentDate()
        binding.searchTicketsCurrentDayTextView.text = ", ${getCurrentDayOfWeek()}"

        musicalFlyOffersAdapter = MusicalFlyOfferAdapter(
            onItemViewClick = { musicalFlyOffer ->
                Toast.makeText(
                    requireContext(), "Musical Fly Offer Id: ${musicalFlyOffer.id}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        )

        bestFlyOffersAdapter = BestFlyOfferAdapter(
            onItemViewClick = { bestFlyOffer ->
                Toast.makeText(
                    requireContext(), "Best Fly Offer id ${bestFlyOffer.id}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        )

        binding.searchTicketsStraightRacesRecycleView.apply {
            adapter = bestFlyOffersAdapter
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        }

        binding.searchTicketsMusicallyFlyAwayRecycleView.apply {
            adapter = musicalFlyOffersAdapter
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            this.addItemDecoration(RightSpaceItemDecoration(67, requireContext()))
        }

        // UI action listener events
        binding.searchTicketsShowAllTicketsAppCompatButton.setOnClickListener {
            if (binding.searchTicketsDepartureMainEditText.text.isNotEmpty() &&
                binding.searchTicketsArrivalMainEditText.text.isNotEmpty()
            ) {
                val bundle = Bundle()
                bundle.putString(DESTINATION_KEY, binding.searchTicketsDepartureMainEditText.text.toString())
                bundle.putString(ARRIVAL_KEY, binding.searchTicketsArrivalMainEditText.text.toString())
                bundle.putString(DEPARTURE_DATE_KEY, binding.searchTicketsCurrentDateTextView.text.toString())

                findNavController().navigate(R.id.action_searchTicketsFragment_to_allTicketsFragment, bundle)
            }
        }

        binding.searchTicketsArrivalMainEditText.setOnTouchListener { view, motionEvent ->
            if (motionEvent.action == MotionEvent.ACTION_DOWN) {
                bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
                binding.searchTicketsSecondaryDepartureEditText.text =
                    binding.searchTicketsDepartureMainEditText.text
                true
            } else {
                false
            }
        }

        binding.searchTicketsSecondaryArrivalEditText.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                viewModel.onEvent(searchTicketsEvent = SearchTicketsScreenEvent.GetBestFlyOffersEvent)
                bottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN

                binding.searchTicketsDepartureMainEditText.text =
                    binding.searchTicketsSecondaryDepartureEditText.text

                binding.searchTicketsArrivalMainEditText.text =
                    binding.searchTicketsSecondaryArrivalEditText.text

                true
            } else {
                false
            }
        }

        binding.searchTicketsSecondaryDepartureCloseIconImageView.setOnClickListener {
            binding.searchTicketsSecondaryDepartureEditText.setText("")
        }

        binding.searchTicketsSecondaryArrivalCloseIconImageView.setOnClickListener {
            binding.searchTicketsSecondaryArrivalEditText.setText("")
        }

        binding.searchTicketsSecondaryDepartureEditText.doOnTextChanged { text, _, _, _ ->
            if (text != null) {
                if (text.isEmpty()) {
                    binding.searchTicketsSecondaryDepartureCloseIconImageView.visibility = View.GONE
                } else {
                    binding.searchTicketsSecondaryDepartureCloseIconImageView.visibility =
                        View.VISIBLE
                }
            }
        }

        binding.searchTicketsSecondaryArrivalEditText.doOnTextChanged { text, _, _, _ ->
            if (text != null) {
                if (text.isEmpty()) {
                    binding.searchTicketsSecondaryArrivalCloseIconImageView.visibility = View.GONE
                } else {
                    binding.searchTicketsSecondaryArrivalCloseIconImageView.visibility =
                        View.VISIBLE
                }
            }
        }

        binding.searchTicketsDepartureMainIconImageView.setOnClickListener {
            if (binding.searchTicketsDepartureMainEditText.text.isNotEmpty() &&
                binding.searchTicketsArrivalMainEditText.text.isNotEmpty()
            ) {

                val bufferValue = binding.searchTicketsDepartureMainEditText.text
                binding.searchTicketsDepartureMainEditText.text =
                    binding.searchTicketsArrivalMainEditText.text
                binding.searchTicketsArrivalMainEditText.text = bufferValue
            }
        }

        binding.searchTicketsDepartureMainEditText.doOnTextChanged { text, _, _, _ ->
            if (text != null) {
                if (text.isEmpty()) {
                    binding.searchTicketsDepartureMainIconImageView.visibility = View.GONE
                } else {
                    binding.searchTicketsDepartureMainIconImageView.visibility = View.VISIBLE
                }
            }
        }

        binding.searchTicketsArrivalMainIconImageView.setOnClickListener {
            binding.searchTicketsArrivalMainEditText.setText("")
        }

        binding.searchTicketsArrivalMainEditText.doOnTextChanged { text, _, _, _ ->
            if (text != null) {
                if (text.isEmpty()) {
                    binding.searchTicketsArrivalMainIconImageView.visibility = View.GONE
                } else {
                    binding.searchTicketsArrivalMainIconImageView.visibility = View.VISIBLE
                }
            }
        }

        binding.searchTicketsDifficultRouteLinearLayout.setOnClickListener {
            findNavController().navigate(R.id.action_searchTicketsFragment_to_difficultRouteFragment)
        }

        binding.searchTicketsAnywhereLinearLayout.setOnClickListener {
            binding.searchTicketsSecondaryArrivalEditText.setText("Куда угодно")
        }

        binding.searchTicketsHolidaysLinearLayout.setOnClickListener {
            findNavController().navigate(R.id.action_searchTicketsFragment_to_holidaysFragment)
        }

        binding.searchTicketsHotTicketsLinearLayout.setOnClickListener {
            findNavController().navigate(R.id.action_searchTicketsFragment_to_hotTicketsFragment)
        }

        binding.searchTicketsPopularDestinationStambul.setOnClickListener {
            binding.searchTicketsSecondaryArrivalEditText.setText("Стамбул")
        }

        binding.searchTicketsPopularDestinationSochi.setOnClickListener {
            binding.searchTicketsSecondaryArrivalEditText.setText("Сочи")
        }

        binding.searchTicketsPopularDestinationPhucket.setOnClickListener {
            binding.searchTicketsSecondaryArrivalEditText.setText("Пхукет")
        }

        binding.searchTicketsChangeBackToDateExtraCardView.setOnClickListener {
            showDatePickerDialogDepartureDate()
        }

        binding.searchTicketsBackToExtraCardView.setOnClickListener {
            showDatePickerDialogBackDate()
        }

        binding.searchTicketsFilterExtraCardView.setOnClickListener {
            findNavController().navigate(R.id.action_searchTicketsFragment_to_filterTicketsFragment)
        }

        binding.searchTicketsDepartureMainEditText.filters = arrayOf(InputFilter { source, start, end, dest, dstart, dend ->
            for (i in start until end) {
                if (!source[i].toString().matches("[\\p{InCyrillic} -]+".toRegex())) {
                    return@InputFilter ""
                }
            }
            null
        })

        binding.searchTicketsArrivalMainEditText.filters = arrayOf(InputFilter { source, start, end, dest, dstart, dend ->
            for (i in start until end) {
                if (!source[i].toString().matches("[\\p{InCyrillic} -]+".toRegex())) {
                    return@InputFilter ""
                }
            }
            null
        })

        binding.searchTicketsSecondaryDepartureEditText.filters = arrayOf(InputFilter { source, start, end, dest, dstart, dend ->
            for (i in start until end) {
                if (!source[i].toString().matches("[\\p{InCyrillic} -]+".toRegex())) {
                    return@InputFilter ""
                }
            }
            null
        })

        binding.searchTicketsSecondaryArrivalEditText.filters = arrayOf(InputFilter { source, start, end, dest, dstart, dend ->
            for (i in start until end) {
                if (!source[i].toString().matches("[\\p{InCyrillic} -]+".toRegex())) {
                    return@InputFilter ""
                }
            }
            null
        })
        //

        viewModel.onEvent(searchTicketsEvent = SearchTicketsScreenEvent.GetMusicalFlyOffersEvent)

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.uiState.collect { state ->
                if (state.bestTicketsList.isNotEmpty()) {
                    getBestFlyOffers(newList = state.bestTicketsList)
                }
                if (state.musicalTicketsList.isNotEmpty()) {
                    getMusicalFlyOffers(state.musicalTicketsList)
                }

                if (state.departureMainText.isNotEmpty()) {
                    binding.searchTicketsDepartureMainEditText.setText(state.departureMainText)
                }
            }
        }
    }

    override fun onStop() {
        super.onStop()
        if (binding.searchTicketsDepartureMainEditText.text.isNotEmpty()) {
            viewModel.onEvent(searchTicketsEvent = SearchTicketsScreenEvent.SaveDataToStorageEvent(
                key = DATA_STORAGE_KEY, value = binding.searchTicketsDepartureMainEditText.text.toString()
            ))
        }
    }

    private fun getBestFlyOffers(newList: List<BestFlyOffer>) {

        binding.searchTicketsHeaderTextView.visibility = View.GONE
        binding.searchTicketsMusicallyFlyAwayHeaderTextView.visibility = View.GONE
        binding.searchTicketsMusicallyFlyAwayRecycleView.visibility = View.GONE
        binding.searchTicketsShowAllPlacesAppCompatButton.visibility = View.GONE

        binding.searchTicketsStraightRacesCardView.visibility = View.VISIBLE
        binding.searchTicketsShowAllTicketsAppCompatButton.visibility = View.VISIBLE
        binding.searchTicketsSubscriptionOnPriceCardView.visibility = View.VISIBLE
        binding.searchTicketsExtraParametersBarHorizontalScrollView.visibility = View.VISIBLE

        binding.searchTicketsDepartureMainEditText.text =
            binding.searchTicketsSecondaryDepartureEditText.text

        binding.searchTicketsArrivalMainEditText.text =
            binding.searchTicketsSecondaryArrivalEditText.text

        bestFlyOffersAdapter?.setFlyOffers(newList)
    }

    private fun getMusicalFlyOffers(newList: List<MusicalFlyOffer>) {
        musicalFlyOffersAdapter?.setFlyOffers(newList)
    }

    private fun getCurrentDate(): String {
        val dateFormat = SimpleDateFormat("dd MMM", Locale("ru"))
        return dateFormat.format(Date())
    }

    private fun getCurrentDayOfWeek(): String {
        val dateFormat = SimpleDateFormat("EE", Locale("ru"))
        return dateFormat.format(Date())
    }

    private fun showDatePickerDialogDepartureDate() {
        val calendar = Calendar.getInstance()
        val datePickerDialog = DatePickerDialog(requireContext(), { _, year, month, dayOfMonth ->
            calendar.set(year, month, dayOfMonth)
            val dateFormat = SimpleDateFormat("dd MMMM", Locale("ru")).format(calendar.time)
            val dayFormat = SimpleDateFormat("EE", Locale("ru")).format(calendar.time)
            binding.searchTicketsCurrentDateTextView.text = dateFormat
            binding.searchTicketsCurrentDayTextView.text = ", $dayFormat"
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH))

        datePickerDialog.show()
    }

    private fun showDatePickerDialogBackDate() {
        val calendar = Calendar.getInstance()
        val datePickerDialog = DatePickerDialog(requireContext(), { _, year, month, dayOfMonth ->
            calendar.set(year, month, dayOfMonth)
            val selectedDate = SimpleDateFormat("dd MMMM, EE", Locale("ru")).format(calendar.time)
            binding.searchTicketsBackDateTextView.text = selectedDate
            binding.searchTicketsBackDateImageView.visibility = View.GONE
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH))

        datePickerDialog.show()
    }

    companion object {
        const val DESTINATION_KEY = "destination"
        const val ARRIVAL_KEY = "arrival"
        const val DATA_STORAGE_KEY = "data_storage_key"
        const val DEPARTURE_DATE_KEY = "departure date"
    }
}