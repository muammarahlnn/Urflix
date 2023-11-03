package com.muammarahlnn.urflix.core.network.datasource.impl

import com.muammarahlnn.urflix.core.network.api.SearchApi
import com.muammarahlnn.urflix.core.network.datasource.SearchNetworkDataSource
import com.muammarahlnn.urflix.core.network.model.response.MovieResponse
import com.muammarahlnn.urflix.core.network.model.response.TvShowResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton


/**
 * @author Muammar Ahlan Abimanyu (muammarahlnn)
 * @file SearchNetworkDataSourceImpl, 04/11/2023 01.26 by Muammar Ahlan Abimanyu
 */
@Singleton
class SearchNetworkDataSourceImpl @Inject constructor(
    private val searchApi: SearchApi
) : SearchNetworkDataSource{

    override fun getSearchMovies(query: String): Flow<List<MovieResponse>> =
        flow {
            emit(searchApi.getSearchMovies(query).results)
        }

    override fun getSearchTvShows(query: String): Flow<List<TvShowResponse>> =
        flow {
            emit(searchApi.getSearchTvShows(query).results)
        }
}