package com.muammarahlnn.urflix.core.data.repository

import com.muammarahlnn.urflix.core.model.data.FilmModel
import com.muammarahlnn.urflix.core.model.data.GenreModel
import com.muammarahlnn.urflix.core.model.data.PersonModel
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

    fun getMovieGenres(): Flow<List<GenreModel>>

    fun getTvShowGenres(): Flow<List<GenreModel>>

    fun getTrendingMovies(): Flow<List<FilmModel>>

    fun getPopularPeople(): Flow<List<PersonModel>>
}