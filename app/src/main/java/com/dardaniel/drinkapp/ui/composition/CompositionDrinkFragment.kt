package com.dardaniel.drinkapp.ui.composition

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.dardaniel.drinkapp.R
import com.dardaniel.drinkapp.core.Resource
import com.dardaniel.drinkapp.core.composition.Composition
import com.dardaniel.drinkapp.databinding.FragmentCompositionDrinkBinding
import com.dardaniel.drinkapp.domain.model.Cocktail
import com.dardaniel.drinkapp.ui.viewmodel.CocktailViewModel
import com.dardaniel.drinkapp.utils.round
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CompositionDrinkFragment : Fragment(R.layout.fragment_composition_drink) {

    private lateinit var binding: FragmentCompositionDrinkBinding

    private val args by navArgs<CompositionDrinkFragmentArgs>()

    private val cocktailViewModel: CocktailViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentCompositionDrinkBinding.bind(view)

        cocktailViewModel.showCocktail(args.composition)

        binding.txTitleComposition.text = args.composition.uppercase()

        cocktailViewModel.cocktailViewModel.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Loading -> {
                    binding.pbComposition.visibility = View.VISIBLE
                }
                is Resource.Success -> {
                    binding.pbComposition.visibility = View.GONE

                    val list: List<Cocktail> = it.data as List<Cocktail>
                    list.forEach { p ->
                        run {
                            p.price =
                                price(args.composition).round(2).toString()
                        }
                    }
                    initRecyclerview(list)
                }
                is Resource.Failure -> {
                    binding.pbComposition.visibility = View.GONE
                    Toast.makeText(requireContext(), "ERROR ", Toast.LENGTH_SHORT).show()
                    Log.d("4ds ", "error ${it.exception}")
                }
            }
        }
    }

    private fun initRecyclerview(cocktailList: List<Cocktail>) {
        binding.rvComposition.layoutManager = LinearLayoutManager(requireContext())
        binding.rvComposition.adapter =
            CompositionAdapter(cocktailList) { showItemDetailCocktail(it) }
    }

    private fun showItemDetailCocktail(cocktail: Cocktail) {
        var action =
            CompositionDrinkFragmentDirections.actionCompositionDrinkFragmentToDetailDrinkFragment(
                cocktail.id
            )
        findNavController().navigate(action)
    }

    private fun price(composition: String): Double {
        return if (composition == Composition.ALCOHOLIC.value) {
            50 + Math.random() * (150 - 50)
        } else {
            50 + Math.random() * (100 - 50)
        }
    }
}