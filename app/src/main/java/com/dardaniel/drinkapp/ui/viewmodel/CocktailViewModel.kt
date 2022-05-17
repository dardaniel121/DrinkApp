package com.dardaniel.drinkapp.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dardaniel.drinkapp.core.Resource
import com.dardaniel.drinkapp.domain.*
import com.dardaniel.drinkapp.domain.model.Cocktail
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CocktailViewModel @Inject constructor(
    private val getCocktailUseCase: GetCocktailsUseCase,
    private val getDetailCocktailUseCase: GetDetailCocktailUseCase,
    private val getFavoritesUseCase: GetFavoritesUseCase,
    private val postFavoriteUseCase: PostFavoriteUseCase,
    private val getFavoriteCocktailUseCase: GetFavoriteCocktailUseCase

) : ViewModel() {

    val cocktailViewModel = MutableLiveData<Resource<Any>>()
    val detailCocktailViewModel = MutableLiveData<Resource<Cocktail>>()

    val addFavoriteViewModel = MutableLiveData<Resource<String>>()
    val showFavoritesViewModel = MutableLiveData<Resource<Any>>()
    val isFavorite = MutableLiveData<Boolean>()

    fun showCocktail(type: String) {
        viewModelScope.launch {
            cocktailViewModel.postValue(Resource.Loading)
            try {
                cocktailViewModel.postValue(getCocktailUseCase.invoke(type))
            } catch (ex: Exception) {
                cocktailViewModel.postValue(Resource.Failure(ex))
            }
        }
    }

    fun showDetailCocktail(id: String) {
        viewModelScope.launch {
            detailCocktailViewModel.postValue(Resource.Loading)
            try {
                detailCocktailViewModel.postValue(getDetailCocktailUseCase(id)!!)
            } catch (ex: Exception) {
                detailCocktailViewModel.postValue(Resource.Failure(ex))
            }
        }
    }

    fun addFavoritesCocktail(cocktail: Cocktail) {
        viewModelScope.launch {
            addFavoriteViewModel.postValue(postFavoriteUseCase.invoke(cocktail))
        }
    }

    fun showFavorites() {
        viewModelScope.launch {
            try {
                showFavoritesViewModel.postValue(getFavoritesUseCase.invoke())
            } catch (ex: Exception) {
                showFavoritesViewModel.postValue(Resource.Failure(ex))
            }
        }
    }

    fun isFavorite(id: String) {
        viewModelScope.launch {
            isFavorite.postValue(getFavoriteCocktailUseCase.invoke(id))
        }
    }

}


