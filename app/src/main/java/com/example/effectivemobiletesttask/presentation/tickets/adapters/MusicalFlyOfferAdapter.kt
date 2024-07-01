package com.example.effectivemobiletesttask.presentation.tickets.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.model.MusicalFlyOffer
import com.example.effectivemobiletesttask.databinding.ItemViewMusicallyFlyAwayBinding

class MusicalFlyOfferAdapter(
    private val onItemViewClick: (MusicalFlyOffer) -> Unit
) : RecyclerView.Adapter<MusicalFlyOfferViewHolder>() {

    private val musicalFlyOffers = ArrayList<MusicalFlyOffer>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MusicalFlyOfferViewHolder {
        return MusicalFlyOfferViewHolder(
            ItemViewMusicallyFlyAwayBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            parent.context
        )
    }

    override fun getItemCount(): Int {
        return musicalFlyOffers.size
    }

    override fun onBindViewHolder(holder: MusicalFlyOfferViewHolder, position: Int) {
        holder.bind(musicalFlyOffers[position])
        holder.itemView.setOnClickListener { onItemViewClick.invoke(musicalFlyOffers[position]) }
    }

    fun setFlyOffers(musicalFlyOfferList: List<MusicalFlyOffer>) {
        musicalFlyOffers.addAll(musicalFlyOfferList)
        notifyDataSetChanged()
    }
}
