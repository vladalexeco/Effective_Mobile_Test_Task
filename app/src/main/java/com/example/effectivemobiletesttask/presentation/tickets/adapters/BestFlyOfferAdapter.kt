package com.example.effectivemobiletesttask.presentation.tickets.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.model.BestFlyOffer
import com.example.effectivemobiletesttask.databinding.ItemViewFlightsBinding

class BestFlyOfferAdapter(
    private val onItemViewClick: (BestFlyOffer) -> Unit
) : RecyclerView.Adapter<BestFlyOfferViewHolder>() {

    private val bestFlyOffers = ArrayList<BestFlyOffer>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BestFlyOfferViewHolder {
        return BestFlyOfferViewHolder(
            binding = ItemViewFlightsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun getItemCount(): Int {
        return bestFlyOffers.size
    }

    override fun onBindViewHolder(holder: BestFlyOfferViewHolder, position: Int) {
        holder.bind(bestFlyOffers[position])
        holder.itemView.setOnClickListener { onItemViewClick.invoke(bestFlyOffers[position]) }
    }

    fun setFlyOffers(newBestFlyOffers: List<BestFlyOffer>) {
        bestFlyOffers.addAll(newBestFlyOffers)
        notifyDataSetChanged()
    }
}