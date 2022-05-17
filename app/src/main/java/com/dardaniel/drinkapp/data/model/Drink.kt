package com.dardaniel.drinkapp.data.model

import com.google.gson.annotations.SerializedName


data class Drink(
    @SerializedName("drinks")
    val drinkList: List<CocktailModel>
)
