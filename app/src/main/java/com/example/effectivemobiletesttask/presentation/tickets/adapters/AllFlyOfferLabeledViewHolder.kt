package com.example.effectivemobiletesttask.presentation.tickets.adapters

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.model.FlyOffer
import com.example.effectivemobiletesttask.databinding.ItemViewAllTicketsLabeledBinding

class AllFlyOfferLabeledViewHolder(
    private val binding: ItemViewAllTicketsLabeledBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(model: FlyOffer) {

        binding.itemViewLabeledAllTicketsTopLabelTextView.text = model.extraCondition
        binding.itemViewLabeledAllTicketsPriceTextView.text = model.price.toString()
        binding.itemViewLabeledAllTicketsDepartureTimeTextView.text = model.departureTime
        binding.itemViewLabeledAllTicketsArrivalTimeTextView.text = model.arrivalTime
        binding.itemViewLabeledAllTicketsDepartureCodeTextView.text = model.departureCode
        binding.itemViewLabeledAllTicketsArrivalCodeTextView.text = model.arrivalCode
        binding.itemViewLabeledAllTicketsTravelTimeTextView.text = "${model.travelTime} ч"
        if (model.withoutTransfer) {
            binding.itemViewLabeledAllTicketsWithoutTransfersTextView.text = "Без пересадок"
        } else {
            binding.itemViewLabeledAllTicketsWithoutTransfersTextView.visibility = View.GONE
            binding.itemViewLabeledAllTicketsSlashTextView.visibility = View.GONE
        }
    }
}