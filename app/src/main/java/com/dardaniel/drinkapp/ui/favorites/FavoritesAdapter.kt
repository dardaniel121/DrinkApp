package com.dardaniel.drinkapp.ui.favorites

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dardaniel.drinkapp.R
import com.dardaniel.drinkapp.databinding.FavoritesItemBinding
import com.dardaniel.drinkapp.domain.model.Cocktail

class FavoritesAdapter(private val listFavorites: List<Cocktail>) :
    RecyclerView.Adapter<FavoritesAdapter.FavoritesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritesViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return FavoritesViewHolder(layoutInflater.inflate(R.layout.favorites_item, parent, false))
    }

    override fun onBindViewHolder(holder: FavoritesViewHolder, position: Int) {
        val item = listFavorites[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = listFavorites.size


    class FavoritesViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = FavoritesItemBinding.bind(view)

        fun bind(item: Cocktail) {
            Glide.with(binding.imgCocktailItem.context).load(item.image)
                .into(binding.imgCocktailItem)
        }
    }
}