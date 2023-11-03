package com.muammarahlnn.urflix.core.data.repository

import com.muammarahlnn.urflix.core.model.data.FilmDetailsModel
import kotlinx.coroutines.flow.Flow


/**
 * @author Muammar Ahlan Abimanyu (muammarahlnn)
 * @file FilmDetailsRepository, 03/11/2023 18.57 by Muammar Ahlan Abimanyu
 */
interface FilmDetailsRepository {

    fun getMovieDetails(movieId: Int): Flow<FilmDetailsModel>

    fun getTvShowDetails(tvShowId: Int): Flow<FilmDetailsModel>

    fun isFilmBookmarked(filmId: Int): Flow<Boolean>

    suspend fun insertBookmarkedFilm(film: FilmDetailsModel)

    suspend fun deleteBookmarkedFilm(filmId: Int)
}