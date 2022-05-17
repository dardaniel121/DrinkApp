package com.dardaniel.drinkapp.domain.model

import com.dardaniel.drinkapp.data.database.entities.FavoriteCocktailEntity
import com.dardaniel.drinkapp.data.model.CocktailModel

data class Cocktail(
    val id: String,
    val name: String,
    val image: String,
    var description: String? = null,
    var price: String? = null
)

fun CocktailModel.toDomain() = Cocktail(id, name, image, description)
fun FavoriteCocktailEntity.toDomain() = Cocktail(id, name, image, description)


