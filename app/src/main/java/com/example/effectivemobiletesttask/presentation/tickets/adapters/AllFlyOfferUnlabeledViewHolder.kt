package com.example.effectivemobiletesttask.presentation.tickets.adapters

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.model.FlyOffer
import com.example.effectivemobiletesttask.databinding.ItemViewAllTicketsUnlabeledBinding

class AllFlyOfferUnlabeledViewHolder(
    private val binding: ItemViewAllTicketsUnlabeledBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(model: FlyOffer) {

        binding.itemViewUnlabeledAllTicketsPriceTextView.text = model.price.toString()
        binding.itemViewUnlabeledAllTicketsDepartureTimeTextView.text = model.departureTime
        binding.itemViewUnlabeledAllTicketsArrivalTimeTextView.text = model.arrivalTime
        binding.itemViewUnlabeledAllTicketsDepartureCodeTextView.text = model.departureCode
        binding.itemViewUnlabeledAllTicketsArrivalCodeTextView.text = model.arrivalCode
        binding.itemViewUnlabeledAllTicketsTravelTimeTextView.text = "${model.travelTime} ч"
        if (model.withoutTransfer) {
            binding.itemViewUnlabeledAllTicketsWithoutTransfersTextView.text = "Без пересадок"
        } else {
            binding.itemViewUnlabeledAllTicketsSlashTextView.visibility = View.GONE
        }
    }
}