package com.dardaniel.drinkapp.domain


import com.dardaniel.drinkapp.core.Resource
import com.dardaniel.drinkapp.data.CocktailRepository
import com.dardaniel.drinkapp.domain.model.Cocktail
import java.lang.Exception
import javax.inject.Inject

class PostFavoriteUseCase @Inject constructor(var cocktailRepository: CocktailRepository) {

    suspend fun invoke(cocktail: Cocktail): Resource<String> {
        return try {
            cocktailRepository.insertCocktailFavorite(cocktail)
            Resource.Success("ok")
        } catch (ex: Exception) {
            Resource.Failure(ex)
        }
    }

}


/*


   suspend fun insertCocktailFavorite(cocktail: Cocktail) {
        db.insertFavoriteCocktail(cocktail.toDataBase())
    }
 */