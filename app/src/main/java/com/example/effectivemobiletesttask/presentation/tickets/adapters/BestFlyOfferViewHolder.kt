package com.example.effectivemobiletesttask.presentation.tickets.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.ColorStateList
import androidx.core.widget.ImageViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.model.BestFlyOffer
import com.example.effectivemobiletesttask.R
import com.example.effectivemobiletesttask.databinding.ItemViewFlightsBinding

class BestFlyOfferViewHolder(
    private val binding: ItemViewFlightsBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(model: BestFlyOffer) {

        binding.flightNameTextView.text = model.aviaCompany
        binding.flightPriceTextView.text = model.price.toString()
        binding.flightTimeTextView.text = model.time.joinToString(separator = " ")
    }
}