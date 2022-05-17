package com.dardaniel.drinkapp.ui.detail

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.dardaniel.drinkapp.R
import com.dardaniel.drinkapp.core.Resource
import com.dardaniel.drinkapp.databinding.FragmentDetailDrinkBinding
import com.dardaniel.drinkapp.domain.model.Cocktail
import com.dardaniel.drinkapp.ui.viewmodel.CocktailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailDrinkFragment : Fragment(R.layout.fragment_detail_drink) {

    private lateinit var binding: FragmentDetailDrinkBinding

    private val cocktailViewModel: CocktailViewModel by viewModels()

    private val args by navArgs<DetailDrinkFragmentArgs>()
    private var objCocktail: Cocktail? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDetailDrinkBinding.bind(view)


        cocktailViewModel.showDetailCocktail(args.id)
        cocktailViewModel.isFavorite(args.id)
        cocktailViewModel.detailCocktailViewModel.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Loading -> {
                    binding.pbDetail.visibility = View.VISIBLE
                }
                is Resource.Success -> {
                    val cocktail: Cocktail = it.data
                    objCocktail = cocktail
                    binding.tvNameCockDetail.text = cocktail.name
                    Glide.with(binding.imageCocktailDetail.context).load(cocktail.image)
                        .into(binding.imageCocktailDetail)
                    binding.tvDescDetail.text = cocktail.description
                    isFavorite()
                    binding.pbDetail.visibility = View.GONE
                }
                is Resource.Failure -> {
                    Log.d("4ds ", "${it.exception.message}")
                    Toast.makeText(
                        requireContext(),
                        "error ${it.exception.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }

        binding.imgFavorite.setOnClickListener { addFavorite() }
    }

    private fun addFavorite() {
        objCocktail?.let { cocktailViewModel.addFavoritesCocktail(it) }
        cocktailViewModel.addFavoriteViewModel.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Loading -> {

                }
                is Resource.Success -> {
                    Toast.makeText(
                        requireContext(),
                        "Agregado...",
                        Toast.LENGTH_LONG
                    ).show()
                }
                is Resource.Failure -> {
                    Toast.makeText(
                        requireContext(),
                        "Error ${it.exception.message}",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }

    private fun isFavorite() {
        cocktailViewModel.isFavorite.observe(viewLifecycleOwner) {
            if (it) {
                binding.imgFavorite.setColorFilter(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.yellow_favorite
                    )
                )
            } else {
                binding.imgFavorite.setColorFilter(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.white
                    )
                )
            }

        }
    }
}