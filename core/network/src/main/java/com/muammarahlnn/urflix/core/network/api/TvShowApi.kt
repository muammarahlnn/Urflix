package com.muammarahlnn.urflix.core.network.api

import com.muammarahlnn.urflix.core.network.model.response.BaseResults
import com.muammarahlnn.urflix.core.network.model.response.ImageResponses
import com.muammarahlnn.urflix.core.network.model.response.TvShowDetailsResponse
import com.muammarahlnn.urflix.core.network.model.response.TvShowResponse
import retrofit2.http.GET
import retrofit2.http.Path
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

    @GET(GET_TV_SHOW_DETAILS)
    suspend fun getTvShowDetails(
        @Path(PATH_ID) tvShowId: Int,
    ): TvShowDetailsResponse

    @GET(GET_TV_SHOW_IMAGES)
    suspend fun getTvShowImages(
        @Path(PATH_ID) tvShowId: Int,
    ): ImageResponses

    companion object {

        private const val GET_AIRING_TODAY_TV_SHOWS = "tv/airing_today"

        private const val GET_ON_THE_AIR_TV_SHOWS = "tv/on_the_air"

        private const val GET_POPULAR_TV_SHOWS = "tv/popular"

        private const val GET_TOP_RATED_TV_SHOWS = "tv/top_rated"

        private const val GET_TV_SHOW_DETAILS = "tv/{id}"

        private const val GET_TV_SHOW_IMAGES = "tv/{id}/images"

        private const val QUERY_PAGE = "page"

        private const val PATH_ID = "id"
    }
}