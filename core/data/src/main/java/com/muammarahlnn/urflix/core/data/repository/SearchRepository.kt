package com.muammarahlnn.urflix.core.data.repository

import com.muammarahlnn.urflix.core.model.data.FilmModel
import kotlinx.coroutines.flow.Flow


/**
 * @author Muammar Ahlan Abimanyu (muammarahlnn)
 * @file SearchRepository, 04/11/2023 01.30 by Muammar Ahlan Abimanyu
 */
interface SearchRepository {

    fun getSearchMovies(query: String): Flow<List<FilmModel>>

    fun getSearchTvShows(query: String): Flow<List<FilmModel>>
}