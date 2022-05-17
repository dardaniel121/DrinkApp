package com.dardaniel.drinkapp.data.network

import com.dardaniel.drinkapp.data.model.Drink
import retrofit2.http.GET
import retrofit2.http.Query

interface DrinkApiClient {

    @GET("filter.php?")
    suspend fun getByTypeDrink(@Query("a") type: String): Drink

    @GET("lookup.php?")
    suspend fun getDetailById(@Query("i") id: String): Drink

}