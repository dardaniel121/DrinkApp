package com.dardaniel.drinkapp.ui.composition

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dardaniel.drinkapp.R
import com.dardaniel.drinkapp.domain.model.Cocktail

class CompositionAdapter(
    private val compositionList: List<Cocktail>,
    private val onClickListener: (Cocktail) -> Unit
) :
    RecyclerView.Adapter<CompositionViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CompositionViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return CompositionViewHolder(
            layoutInflater.inflate(
                R.layout.composition_cocktail_row,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CompositionViewHolder, position: Int) {
        val item = compositionList[position]
        holder.render(item, onClickListener)
    }

    override fun getItemCount(): Int = compositionList.size

}