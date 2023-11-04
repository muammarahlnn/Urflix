package com.muammarahlnn.urflix.core.data.repository

import com.muammarahlnn.urflix.core.model.data.FilmModel
import com.muammarahlnn.urflix.core.model.data.constant.MoviesSection
import com.muammarahlnn.urflix.core.model.data.constant.TvShowsSection
import kotlinx.coroutines.flow.Flow


/**
 * @author Muammar Ahlan Abimanyu (muammarahlnn)
 * @file FilmsSectionRepository, 04/11/2023 08.47 by Muammar Ahlan Abimanyu
 */
interface FilmsSectionRepository {

    suspend fun getMovies(
        section: MoviesSection,
        page: Int,
    ): Flow<List<FilmModel>>

    suspend fun getTvShows(
        section: TvShowsSection,
        page: Int,
    ): Flow<List<FilmModel>>

    suspend fun getMoviesWithGenres(
        genre: String,
        page: Int,
    ): Flow<List<FilmModel>>

    suspend fun getTvShowsWithGenres(
        genre: String,
        page: Int,
    ): Flow<List<FilmModel>>
}