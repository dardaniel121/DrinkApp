package com.dardaniel.drinkapp.data

import com.dardaniel.drinkapp.core.Resource
import com.dardaniel.drinkapp.data.database.dao.CocktailDao
import com.dardaniel.drinkapp.data.database.entities.toDataBase
import com.dardaniel.drinkapp.data.network.CocktailService
import com.dardaniel.drinkapp.domain.model.Cocktail
import com.dardaniel.drinkapp.domain.model.toDomain
import javax.inject.Inject

class CocktailRepository @Inject constructor(
    private val api: CocktailService,
    private val db: CocktailDao
) {

    suspend fun getByTypeDrink(type: String): Resource<List<Cocktail>> {
        return Resource.Success(api.getByTypeDrink(type).drinkList.map { it.toDomain() })
    }

    suspend fun getDetailById(id: String): Resource<Cocktail> {
        return Resource.Success(api.getDetailById(id).drinkList[0].toDomain())
    }

    suspend fun getAllCocktailsFavorites(): Resource<List<Cocktail>> {
        return Resource.Success(db.getAllFavoritesCocktails().map { it.toDomain() })
    }

    suspend fun insertCocktailFavorite(cocktail: Cocktail) {
        db.insertFavoriteCocktail(cocktail.toDataBase())
    }

    suspend fun deleteFavoriteCocktail(id: String) {
        db.deleteFavoriteCocktail(id)
    }

    suspend fun getFavoriteCocktailById(id: String): Cocktail? {
        return db.getFavoriteCocktailById(id).toDomain()
    }
}

/**
 *

suspend fun getAllCocktailsFavorites(): List<CocktailEntity>

@Insert(onConflict = OnConflictStrategy.REPLACE)
suspend fun insertCocktailFavorite(cocktailEntity: CocktailEntity)

 */