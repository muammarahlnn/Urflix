package com.muammarahlnn.urflix.core.network.api

import com.muammarahlnn.urflix.core.network.model.response.BaseResults
import com.muammarahlnn.urflix.core.network.model.response.MovieResponse
import retrofit2.http.GET


/**
 * @author Muammar Ahlan Abimanyu (muammarahlnn)
 * @file TrendingApi, 04/11/2023 20.01 by Muammar Ahlan Abimanyu
 */
interface TrendingApi {

    @GET(GET_TRENDING_MOVIES)
    suspend fun getTrendingMovies(): BaseResults<MovieResponse>

    companion object {

        private const val GET_TRENDING_MOVIES = "trending/movie/day"
    }
}