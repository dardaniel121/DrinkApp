package com.dardaniel.drinkapp.ui.composition

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dardaniel.drinkapp.databinding.CompositionCocktailRowBinding
import com.dardaniel.drinkapp.domain.model.Cocktail

class CompositionViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val binding = CompositionCocktailRowBinding.bind(view)

    fun render(cocktail: Cocktail, onClickListener: (Cocktail) -> Unit) {
        binding.tvNameCock.text = cocktail.name
        Glide.with(binding.imgComposition.context).load(cocktail.image).into(binding.imgComposition)
        binding.tvPrice.text = "$ " + cocktail.price
        itemView.setOnClickListener { onClickListener(cocktail) }
    }
}