package com.muammarahlnn.urflix.core.network.api

import com.muammarahlnn.urflix.core.network.model.response.BaseResults
import com.muammarahlnn.urflix.core.network.model.response.ImageResponses
import com.muammarahlnn.urflix.core.network.model.response.MovieDetailsResponse
import com.muammarahlnn.urflix.core.network.model.response.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


/**
 * @author Muammar Ahlan Abimanyu (muammarahlnn)
 * @file MovieApi, 02/11/2023 11.26 by Muammar Ahlan Abimanyu
 */
interface MovieApi {

    @GET(GET_NOW_PLAYING_MOVIES)
    suspend fun getNowPlayingMovies(
        @Query(QUERY_PAGE) page: Int = 1,
    ): BaseResults<MovieResponse>

    @GET(GET_UPCOMING_MOVIES)
    suspend fun getUpcomingMovies(
        @Query(QUERY_PAGE) page: Int = 1,
    ): BaseResults<MovieResponse>

    @GET(GET_POPULAR_MOVIES)
    suspend fun getPopularMovies(
        @Query(QUERY_PAGE) page: Int = 1,
    ): BaseResults<MovieResponse>

    @GET(GET_TOP_RATED_MOVIES)
    suspend fun getTopRatedMovies(
        @Query(QUERY_PAGE) page: Int = 1,
    ): BaseResults<MovieResponse>

    @GET(GET_MOVIE_DETAILS)
    suspend fun getMovieDetails(
        @Path(PATH_ID) movieId: Int,
    ): MovieDetailsResponse

    @GET(GET_MOVIE_IMAGES)
    suspend fun getMovieImages(
        @Path(PATH_ID) movieId: Int,
    ): ImageResponses

    companion object {

        private const val GET_NOW_PLAYING_MOVIES = "movie/now_playing"

        private const val GET_UPCOMING_MOVIES = "movie/upcoming"

        private const val GET_POPULAR_MOVIES = "movie/popular"

        private const val GET_TOP_RATED_MOVIES = "movie/top_rated"

        private const val GET_MOVIE_DETAILS = "movie/{id}"

        private const val GET_MOVIE_IMAGES = "movie/{id}/images"

        private const val QUERY_PAGE = "page"

        private const val PATH_ID = "id"
    }
}