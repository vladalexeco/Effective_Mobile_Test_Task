package com.example.effectivemobiletesttask.presentation.tickets.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.model.FlyOffer
import com.example.effectivemobiletesttask.databinding.ItemViewAllTicketsLabeledBinding
import com.example.effectivemobiletesttask.databinding.ItemViewAllTicketsUnlabeledBinding

class AllFlyOfferAdapter(
    private val onItemViewClick: (FlyOffer) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    private val allFlyOffers = ArrayList<FlyOffer>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == ITEM_VIEW_WITH_EXTRA) {
            AllFlyOfferLabeledViewHolder(
                ItemViewAllTicketsLabeledBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
            )
        } else {
            AllFlyOfferUnlabeledViewHolder(
                ItemViewAllTicketsUnlabeledBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
            )
        }
    }

    override fun getItemCount(): Int {
        return allFlyOffers.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder) {
            is AllFlyOfferLabeledViewHolder -> {
                holder.bind(allFlyOffers[position])
                holder.itemView.setOnClickListener { onItemViewClick.invoke(allFlyOffers[position]) }
            }
            is AllFlyOfferUnlabeledViewHolder -> {
                holder.bind(allFlyOffers[position])
                holder.itemView.setOnClickListener { onItemViewClick.invoke(allFlyOffers[position]) }
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (allFlyOffers[position].extraCondition.isBlank()) {
            ITEM_VIEW_WITHOUT_EXTRA
        } else {
            ITEM_VIEW_WITH_EXTRA
        }
    }

    fun setNewFlyOffers(newFlyOffers: List<FlyOffer>) {
        allFlyOffers.addAll(newFlyOffers)
        notifyDataSetChanged()
    }

    companion object {
        const val ITEM_VIEW_WITH_EXTRA = 1
        const val ITEM_VIEW_WITHOUT_EXTRA = 0
    }
}

