package com.dardaniel.drinkapp.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.dardaniel.drinkapp.domain.model.Cocktail

@Entity(tableName = "favorite_table")
data class FavoriteCocktailEntity(
    @PrimaryKey
    @ColumnInfo(name = "id") val id: String,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "image") val image: String,
    @ColumnInfo(name = "description") val description: String? = null
)

fun Cocktail.toDataBase() =
    FavoriteCocktailEntity(id = id, name = name, image = image, description = description)