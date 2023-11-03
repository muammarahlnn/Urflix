package com.muammarahlnn.urflix.core.data.repository.impl

import com.muammarahlnn.urflix.core.data.mapper.toFilmModels
import com.muammarahlnn.urflix.core.data.repository.BookmarksRepository
import com.muammarahlnn.urflix.core.database.dao.FilmDao
import com.muammarahlnn.urflix.core.model.data.FilmModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject


/**
 * @author Muammar Ahlan Abimanyu (muammarahlnn)
 * @file BookmarksRepositoryImpl, 03/11/2023 22.13 by Muammar Ahlan Abimanyu
 */
class BookmarksRepositoryImpl @Inject constructor(
    private val filmDao: FilmDao,
) : BookmarksRepository {

    override fun getBookmarkedFilms(): Flow<List<FilmModel>> =
        filmDao.getBookmarkedFilms().map {
            it.toFilmModels()
        }
}