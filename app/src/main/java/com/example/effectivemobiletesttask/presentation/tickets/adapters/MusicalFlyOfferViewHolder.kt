package com.example.effectivemobiletesttask.presentation.tickets.adapters

import android.annotation.SuppressLint
import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.domain.model.MusicalFlyOffer
import com.example.effectivemobiletesttask.R
import com.example.effectivemobiletesttask.databinding.ItemViewMusicallyFlyAwayBinding

class MusicalFlyOfferViewHolder(
    private val binding: ItemViewMusicallyFlyAwayBinding,
    private val context: Context
) : RecyclerView.ViewHolder(binding.root) {

    @SuppressLint("UseCompatLoadingForDrawables")
    fun bind(model: MusicalFlyOffer) {
        val currentImage = when(model.id) {
            1 -> context.getDrawable(R.drawable.fly_away_placeholder)
            2 -> context.getDrawable(R.drawable.fly_away_placeholder_2)
            3 -> context.getDrawable(R.drawable.fly_away_placeholder_3)
            else -> context.getDrawable(R.drawable.fly_away_placeholder)
        }

        Glide.with(itemView).load(currentImage).into(binding.flyAwayImageView)

        binding.flyAwayMusicGroupTextView.text = model.musicArtist
        binding.flyAwayCityTextView.text = model.destination
        binding.flyAwayPriceTextView.text = model.price.toString()
    }
}