package com.muammarahlnn.urflix.core.network.datasource.impl

import com.muammarahlnn.urflix.core.network.api.MovieApi
import com.muammarahlnn.urflix.core.network.api.TvShowApi
import com.muammarahlnn.urflix.core.network.datasource.FilmDetailsNetworkDataSource
import com.muammarahlnn.urflix.core.network.model.response.ImageResponses
import com.muammarahlnn.urflix.core.network.model.response.MovieDetailsResponse
import com.muammarahlnn.urflix.core.network.model.response.TvShowDetailsResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton


/**
 * @author Muammar Ahlan Abimanyu (muammarahlnn)
 * @file FilmDetailsNetworkDataSource, 03/11/2023 18.48 by Muammar Ahlan Abimanyu
 */
@Singleton
class FilmDetailsNetworkDataSourceImpl @Inject constructor(
    private val movieApi: MovieApi,
    private val tvShowApi: TvShowApi,
) : FilmDetailsNetworkDataSource {

    override fun getMovieDetails(movieId: Int): Flow<MovieDetailsResponse> =
        flow {
            emit(movieApi.getMovieDetails(movieId))
        }

    override fun getTvShowDetails(tvShowId: Int): Flow<TvShowDetailsResponse> =
        flow {
            emit(tvShowApi.getTvShowDetails(tvShowId))
        }

    override fun getMovieImages(movieId: Int): Flow<ImageResponses> =
        flow {
            emit(movieApi.getMovieImages(movieId))
        }

    override fun getTvShowImages(tvShowId: Int): Flow<ImageResponses> =
        flow {
            emit(tvShowApi.getTvShowImages(tvShowId))
        }

}