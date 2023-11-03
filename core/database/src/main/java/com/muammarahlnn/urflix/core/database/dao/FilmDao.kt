package com.muammarahlnn.urflix.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.muammarahlnn.urflix.core.database.model.FilmEntity
import kotlinx.coroutines.flow.Flow


/**
 * @author Muammar Ahlan Abimanyu (muammarahlnn)
 * @file FilmDao, 03/11/2023 21.08 by Muammar Ahlan Abimanyu
 */
@Dao
interface FilmDao {

    @Query("SELECT * FROM films")
    fun getBookmarkedFilms(): Flow<List<FilmEntity>>

    @Query("SELECT EXISTS (SELECT * FROM films WHERE film_id = :filmId)")
    fun isBookmarkedFilmExists(filmId: Int): Flow<Boolean>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertBookmarkedFilm(film: FilmEntity)

    @Query("DELETE FROM films WHERE film_id = :filmId")
    suspend fun deleteBookmarkedFilm(filmId: Int)
}