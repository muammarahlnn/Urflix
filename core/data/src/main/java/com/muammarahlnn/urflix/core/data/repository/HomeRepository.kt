package com.muammarahlnn.urflix.core.data.repository

import com.muammarahlnn.urflix.core.model.data.FilmModel
import com.muammarahlnn.urflix.core.model.data.constant.MoviesSection
import com.muammarahlnn.urflix.core.model.data.constant.TvShowsSection
import kotlinx.coroutines.flow.Flow


/**
 * @author Muammar Ahlan Abimanyu (muammarahlnn)
 * @file HomeRepository, 02/11/2023 12.19 by Muammar Ahlan Abimanyu
 */
interface HomeRepository {

    fun getMovies(section: MoviesSection): Flow<List<FilmModel>>

    fun getTvShows(section: TvShowsSection): Flow<List<FilmModel>>
}