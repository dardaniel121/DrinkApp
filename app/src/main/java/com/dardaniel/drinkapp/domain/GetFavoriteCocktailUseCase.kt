package com.dardaniel.drinkapp.domain

import com.bumptech.glide.load.engine.Resource
import com.dardaniel.drinkapp.data.CocktailRepository
import javax.inject.Inject

class GetFavoriteCocktailUseCase @Inject constructor(private val repository: CocktailRepository) {

    suspend fun invoke(id: String): Boolean {
        return repository.getFavoriteCocktailById(id) != null
    }
}