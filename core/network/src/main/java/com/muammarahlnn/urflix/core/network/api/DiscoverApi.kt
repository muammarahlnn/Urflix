package com.muammarahlnn.urflix.core.network.api

import com.muammarahlnn.urflix.core.network.model.response.BaseResults
import com.muammarahlnn.urflix.core.network.model.response.MovieResponse
import com.muammarahlnn.urflix.core.network.model.response.TvShowResponse
import retrofit2.http.GET
import retrofit2.http.Query


/**
 * @author Muammar Ahlan Abimanyu (muammarahlnn)
 * @file DiscoverApi, 04/11/2023 16.38 by Muammar Ahlan Abimanyu
 */
interface DiscoverApi {

    @GET(GET_MOVIES)
    suspend fun getMoviesWithGenres(
        @Query(QUERY_WITH_GENRES) genre: String,
        @Query(QUERY_PAGE) page: Int = 1,
    ): BaseResults<MovieResponse>

    @GET(GET_TV_SHOWS)
    suspend fun getTvShowsWithGenres(
        @Query(QUERY_WITH_GENRES) genre: String,
        @Query(QUERY_PAGE) page: Int = 1,
    ): BaseResults<TvShowResponse>

    companion object {

        private const val GET_MOVIES = "discover/movie"

        private const val GET_TV_SHOWS = "discover/tv"

        private const val QUERY_PAGE = "page"

        private const val QUERY_WITH_GENRES = "with_genres"
    }
}