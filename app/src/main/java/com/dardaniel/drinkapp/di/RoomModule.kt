package com.dardaniel.drinkapp.di

import android.content.Context
import androidx.room.Room
import com.dardaniel.drinkapp.data.database.CocktailDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    private const val COCKTAIL_DATABASE_NAME = "cocktail_database"

    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, CocktailDatabase::class.java, COCKTAIL_DATABASE_NAME).build()

    @Singleton
    @Provides
    fun provideCocktailDao(db: CocktailDatabase) = db.getCocktailDao()

}