package com.dardaniel.drinkapp.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dardaniel.drinkapp.data.database.entities.FavoriteCocktailEntity

@Dao
interface CocktailDao {

    @Query(" SELECT * FROM favorite_table")
    suspend fun getAllFavoritesCocktails(): List<FavoriteCocktailEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavoriteCocktail(cocktailEntity: FavoriteCocktailEntity)

    @Query("DELETE FROM favorite_table WHERE id =:id")
    suspend fun deleteFavoriteCocktail(id: String)

    @Query("SELECT * FROM FAVORITE_TABLE WHERE id =:id")
    suspend fun getFavoriteCocktailById(id: String): FavoriteCocktailEntity
}