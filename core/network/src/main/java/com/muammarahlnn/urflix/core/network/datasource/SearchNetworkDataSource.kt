package com.muammarahlnn.urflix.core.network.datasource

import com.muammarahlnn.urflix.core.network.model.response.MovieResponse
import com.muammarahlnn.urflix.core.network.model.response.TvShowResponse
import kotlinx.coroutines.flow.Flow


/**
 * @author Muammar Ahlan Abimanyu (muammarahlnn)
 * @file SearchNetworkDataSource, 04/11/2023 01.26 by Muammar Ahlan Abimanyu
 */
interface SearchNetworkDataSource {

    fun getSearchMovies(query: String): Flow<List<MovieResponse>>

    fun getSearchTvShows(query: String): Flow<List<TvShowResponse>>
}