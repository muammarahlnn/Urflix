package com.muammarahlnn.urflix.core.network.api

import com.muammarahlnn.urflix.core.network.model.response.GenreResponses
import retrofit2.http.GET


/**
 * @author Muammar Ahlan Abimanyu (muammarahlnn)
 * @file GenreApi, 04/11/2023 08.34 by Muammar Ahlan Abimanyu
 */
interface GenreApi {

    @GET(GET_MOVIE_GENRES)
    suspend fun getMovieGenres(): GenreResponses

    @GET(GET_TV_SHOW_GENRES)
    suspend fun getTvShowGenres(): GenreResponses

    companion object {

        private const val GET_MOVIE_GENRES = "genre/movie/list"

        private const val GET_TV_SHOW_GENRES = "genre/tv/list"
    }
}