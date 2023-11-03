package com.muammarahlnn.urflix.core.network.datasource

import com.muammarahlnn.urflix.core.network.model.response.ImageResponses
import com.muammarahlnn.urflix.core.network.model.response.MovieDetailsResponse
import com.muammarahlnn.urflix.core.network.model.response.TvShowDetailsResponse
import kotlinx.coroutines.flow.Flow


/**
 * @author Muammar Ahlan Abimanyu (muammarahlnn)
 * @file FilmDetailDataSource, 03/11/2023 18.41 by Muammar Ahlan Abimanyu
 */
interface FilmDetailsNetworkDataSource {

    fun getMovieDetails(movieId: Int): Flow<MovieDetailsResponse>

    fun getTvShowDetails(tvShowId: Int): Flow<TvShowDetailsResponse>

    fun getMovieImages(movieId: Int): Flow<ImageResponses>

    fun getTvShowImages(tvShowId: Int): Flow<ImageResponses>
}