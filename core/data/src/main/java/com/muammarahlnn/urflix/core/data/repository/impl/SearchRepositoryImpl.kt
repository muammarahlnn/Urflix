package com.muammarahlnn.urflix.core.data.repository.impl

import com.muammarahlnn.urflix.core.data.mapper.toFilmModels
import com.muammarahlnn.urflix.core.data.repository.SearchRepository
import com.muammarahlnn.urflix.core.model.data.FilmModel
import com.muammarahlnn.urflix.core.network.datasource.SearchNetworkDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject


/**
 * @author Muammar Ahlan Abimanyu (muammarahlnn)
 * @file SearchRepositoryImpl, 04/11/2023 01.31 by Muammar Ahlan Abimanyu
 */
class SearchRepositoryImpl @Inject constructor(
    private val searchNetworkDataSource: SearchNetworkDataSource
) : SearchRepository {

    override fun getSearchMovies(query: String): Flow<List<FilmModel>> =
        searchNetworkDataSource.getSearchMovies(query).map {
            it.toFilmModels()
        }

    override fun getSearchTvShows(query: String): Flow<List<FilmModel>> =
        searchNetworkDataSource.getSearchTvShows(query).map {
            it.toFilmModels()
        }
}