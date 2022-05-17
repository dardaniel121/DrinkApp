package com.dardaniel.drinkapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dardaniel.drinkapp.data.database.dao.CocktailDao
import com.dardaniel.drinkapp.data.database.entities.FavoriteCocktailEntity

@Database(entities = [FavoriteCocktailEntity::class], version = 1)
abstract class CocktailDatabase : RoomDatabase() {

    abstract fun getCocktailDao(): CocktailDao

}