package com.muammarahlnn.urflix.core.network.api

import com.muammarahlnn.urflix.core.network.model.response.BaseResults
import com.muammarahlnn.urflix.core.network.model.response.TvShowResponse
import retrofit2.http.GET
import retrofit2.http.Query


/**
 * @author Muammar Ahlan Abimanyu (muammarahlnn)
 * @file TvShowApi, 02/11/2023 11.51 by Muammar Ahlan Abimanyu
 */
interface TvShowApi {

    @GET(GET_AIRING_TODAY_TV_SHOWS)
    suspend fun getAiringTodayTvShows(
        @Query(QUERY_PAGE) page: Int = 1,
    ): BaseResults<TvShowResponse>

    @GET(GET_ON_THE_AIR_TV_SHOWS)
    suspend fun getOnTheAirTvShows(
        @Query(QUERY_PAGE) page: Int = 1,
    ): BaseResults<TvShowResponse>

    @GET(GET_POPULAR_TV_SHOWS)
    suspend fun getPopularTvShows(
        @Query(QUERY_PAGE) page: Int = 1,
    ): BaseResults<TvShowResponse>

    @GET(GET_TOP_RATED_TV_SHOWS)
    suspend fun getTopRatedTvShows(
        @Query(QUERY_PAGE) page: Int = 1,
    ): BaseResults<TvShowResponse>

    companion object {

        private const val GET_AIRING_TODAY_TV_SHOWS = "/tv/airing_today"

        private const val GET_ON_THE_AIR_TV_SHOWS = "/tv/on_the_air"

        private const val GET_POPULAR_TV_SHOWS = "/tv/popular"

        private const val GET_TOP_RATED_TV_SHOWS = "/tv/top_rated"

        private const val QUERY_PAGE = "page"
    }
}