package com.muammarahlnn.urflix.core.network.datasource

import com.muammarahlnn.urflix.core.model.data.constant.MoviesSection
import com.muammarahlnn.urflix.core.model.data.constant.TvShowsSection
import com.muammarahlnn.urflix.core.network.model.response.GenreResponse
import com.muammarahlnn.urflix.core.network.model.response.MovieResponse
import com.muammarahlnn.urflix.core.network.model.response.TvShowResponse
import kotlinx.coroutines.flow.Flow


/**
 * @author Muammar Ahlan Abimanyu (muammarahlnn)
 * @file HomeNetworkDataSource, 02/11/2023 11.55 by Muammar Ahlan Abimanyu
 */
interface HomeNetworkDataSource {

    fun getMovies(section: MoviesSection): Flow<List<MovieResponse>>

    fun getTvShows(section: TvShowsSection): Flow<List<TvShowResponse>>

    fun getMovieGenres(): Flow<List<GenreResponse>>

    fun getTvShowGenres(): Flow<List<GenreResponse>>

    fun getTrendingMovies(): Flow<List<MovieResponse>>
}