package com.muammarahlnn.urflix.core.data.repository

import com.muammarahlnn.urflix.core.model.data.FilmModel
import kotlinx.coroutines.flow.Flow


/**
 * @author Muammar Ahlan Abimanyu (muammarahlnn)
 * @file BookmarksRepository, 03/11/2023 22.12 by Muammar Ahlan Abimanyu
 */
interface BookmarksRepository {

    fun getBookmarkedFilms(): Flow<List<FilmModel>>
}