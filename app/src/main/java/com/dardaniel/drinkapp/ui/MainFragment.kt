package com.dardaniel.drinkapp.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dardaniel.drinkapp.R
import com.dardaniel.drinkapp.core.Resource
import com.dardaniel.drinkapp.core.composition.Composition
import com.dardaniel.drinkapp.databinding.FragmentMainBinding
import com.dardaniel.drinkapp.domain.model.Cocktail
import com.dardaniel.drinkapp.ui.composition.CompositionDrinkFragment
import com.dardaniel.drinkapp.ui.favorites.FavoritesAdapter
import com.dardaniel.drinkapp.ui.viewmodel.CocktailViewModel
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment(R.layout.fragment_main) {

    private lateinit var binding: FragmentMainBinding

    private val cocktailViewModel: CocktailViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentMainBinding.bind(view)

        binding.clAlcoholic.setOnClickListener {
            goToComposition(Composition.ALCOHOLIC)
        }
        binding.clNoAlcoholic.setOnClickListener {
            goToComposition(Composition.NON_ALCOHOLIC)
        }

        cocktailViewModel.showFavorites()
        cocktailViewModel.showFavoritesViewModel.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Success -> {
                    var listCocktail: List<Cocktail> = it.data as List<Cocktail>
                    if (listCocktail.isNotEmpty()) {
                        binding.titleFavorites.visibility = View.VISIBLE

                        initRecyclerview(listCocktail)
                    }
                }
                is Resource.Failure -> {
                    Toast.makeText(requireContext(), "ERROR ", Toast.LENGTH_SHORT).show()
                    Log.d("4ds ", "error ${it.exception}")
                }
            }
        }
    }

    private fun goToComposition(composition: Composition) {
        var action =
            MainFragmentDirections.actionMainFragmentToCompositionDrinkFragment(composition.value)
        findNavController().navigate(action)
    }

    private fun initRecyclerview(cocktailList: List<Cocktail>) {
        binding.rvFavorites.layoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
        binding.rvFavorites.adapter = FavoritesAdapter(cocktailList)
    }
}