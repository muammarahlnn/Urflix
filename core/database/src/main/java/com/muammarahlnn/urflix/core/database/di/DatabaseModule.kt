package com.muammarahlnn.urflix.core.database.di

import android.content.Context
import androidx.room.Room
import com.muammarahlnn.urflix.core.database.UrflixDatabase
import com.muammarahlnn.urflix.core.database.dao.FilmDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


/**
 * @author Muammar Ahlan Abimanyu (muammarahlnn)
 * @file DatabaseModule, 03/11/2023 21.15 by Muammar Ahlan Abimanyu
 */
@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun providesUrflixDatabase(
        @ApplicationContext context: Context,
    ): UrflixDatabase = Room.databaseBuilder(
        context,
        UrflixDatabase::class.java,
        "urflix-database"
    ).fallbackToDestructiveMigration().build()

    @Provides
    fun providesFilmDao(
        database: UrflixDatabase,
    ): FilmDao = database.filmDao()
}