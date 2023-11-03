package com.muammarahlnn.urflix.core.network.api

import com.muammarahlnn.urflix.core.network.model.response.BaseResults
import com.muammarahlnn.urflix.core.network.model.response.MovieResponse
import com.muammarahlnn.urflix.core.network.model.response.TvShowResponse
import retrofit2.http.GET
import retrofit2.http.Query


/**
 * @author Muammar Ahlan Abimanyu (muammarahlnn)
 * @file SearchApi, 04/11/2023 01.21 by Muammar Ahlan Abimanyu
 */
interface SearchApi {

    @GET(GET_SEARCH_MOVIE)
    suspend fun getSearchMovies(
        @Query(QUERY_QUERY) query: String,
        @Query(QUERY_PAGE) page: Int = 1,
    ): BaseResults<MovieResponse>

    @GET(GET_SEARCH_TV_SHOW)
    suspend fun getSearchTvShows(
        @Query(QUERY_QUERY) query: String,
        @Query(QUERY_PAGE) page: Int = 1,
    ): BaseResults<TvShowResponse>

    companion object {

        private const val GET_SEARCH_MOVIE = "search/movie"

        private const val GET_SEARCH_TV_SHOW = "search/tv"

        private const val QUERY_QUERY = "query"

        private const val QUERY_PAGE = "page"
    }
}