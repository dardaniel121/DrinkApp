package com.dardaniel.drinkapp.data.network

import com.dardaniel.drinkapp.data.model.Drink
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CocktailService @Inject constructor(private val api: DrinkApiClient) {

    suspend fun getByTypeDrink(type: String): Drink {
        return withContext(Dispatchers.IO) {
            api.getByTypeDrink(type)
        }
    }

    suspend fun getDetailById(id: String): Drink {
        return withContext(Dispatchers.IO) {
            api.getDetailById(id)
        }
    }

    //suspend fun deleteFavoriteCocktail()
}