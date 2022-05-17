package com.dardaniel.drinkapp.domain

import com.dardaniel.drinkapp.core.Resource
import com.dardaniel.drinkapp.data.CocktailRepository
import com.dardaniel.drinkapp.domain.model.Cocktail
import javax.inject.Inject


class GetCocktailsUseCase @Inject constructor(private val repository: CocktailRepository) {

    suspend operator fun invoke(type: String): Resource<List<Cocktail>> =
        repository.getByTypeDrink(type)


}