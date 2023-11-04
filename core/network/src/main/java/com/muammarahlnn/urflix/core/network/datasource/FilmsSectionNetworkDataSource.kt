package com.muammarahlnn.urflix.core.network.datasource

import com.muammarahlnn.urflix.core.model.data.constant.MoviesSection
import com.muammarahlnn.urflix.core.model.data.constant.TvShowsSection
import com.muammarahlnn.urflix.core.network.model.response.MovieResponse
import com.muammarahlnn.urflix.core.network.model.response.TvShowResponse
import kotlinx.coroutines.flow.Flow


/**
 * @author Muammar Ahlan Abimanyu (muammarahlnn)
 * @file FilmsSectionNetworkDataSource, 04/11/2023 08.39 by Muammar Ahlan Abimanyu
 */
interface FilmsSectionNetworkDataSource {

    fun getMovies(
        section: MoviesSection,
        page: Int
    ): Flow<List<MovieResponse>>

    fun getTvShows(
        section: TvShowsSection,
        page: Int
    ): Flow<List<TvShowResponse>>

    fun getMoviesWithGenres(
        genre: String,
        page: Int,
    ): Flow<List<MovieResponse>>

    fun getTvShowsWithGenres(
        genre: String,
        page: Int
    ): Flow<List<TvShowResponse>>
}