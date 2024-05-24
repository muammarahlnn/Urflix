package com.muammarahlnn.urflix.core.data.repository.impl

import com.muammarahlnn.urflix.core.data.mapper.toFilmModels
import com.muammarahlnn.urflix.core.data.mapper.toGenreModels
import com.muammarahlnn.urflix.core.data.mapper.toPersonModels
import com.muammarahlnn.urflix.core.data.repository.HomeRepository
import com.muammarahlnn.urflix.core.model.data.FilmModel
import com.muammarahlnn.urflix.core.model.data.GenreModel
import com.muammarahlnn.urflix.core.model.data.PersonModel
import com.muammarahlnn.urflix.core.model.data.constant.MoviesSection
import com.muammarahlnn.urflix.core.model.data.constant.TvShowsSection
import com.muammarahlnn.urflix.core.network.datasource.HomeNetworkDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject


/**
 * @author Muammar Ahlan Abimanyu (muammarahlnn)
 * @file HomeRepositoryImpl, 02/11/2023 12.43 by Muammar Ahlan Abimanyu
 */
class HomeRepositoryImpl @Inject constructor(
    private val homeNetworkDataSource: HomeNetworkDataSource
) : HomeRepository {

    override fun getMovies(section: MoviesSection): Flow<List<FilmModel>> =
        homeNetworkDataSource.getMovies(section).map {
            it.toFilmModels()
        }

    override fun getTvShows(section: TvShowsSection): Flow<List<FilmModel>> =
        homeNetworkDataSource.getTvShows(section).map {
            it.toFilmModels()
        }

    override fun getMovieGenres(): Flow<List<GenreModel>> =
        homeNetworkDataSource.getMovieGenres().map {
            it.toGenreModels()
        }

    override fun getTvShowGenres(): Flow<List<GenreModel>> =
        homeNetworkDataSource.getTvShowGenres().map {
            it.toGenreModels()
        }

    override fun getTrendingMovies(): Flow<List<FilmModel>> =
        homeNetworkDataSource.getTrendingMovies().map {
            it.toFilmModels()
        }

    override fun getPopularPeople(): Flow<List<PersonModel>> =
        homeNetworkDataSource.getPopularPeople().map {
            it.toPersonModels()
        }
}