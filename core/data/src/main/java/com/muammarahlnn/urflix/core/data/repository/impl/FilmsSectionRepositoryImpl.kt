package com.muammarahlnn.urflix.core.data.repository.impl

import com.muammarahlnn.urflix.core.data.mapper.toFilmModels
import com.muammarahlnn.urflix.core.data.repository.FilmsSectionRepository
import com.muammarahlnn.urflix.core.model.data.FilmModel
import com.muammarahlnn.urflix.core.model.data.constant.MoviesSection
import com.muammarahlnn.urflix.core.model.data.constant.TvShowsSection
import com.muammarahlnn.urflix.core.network.datasource.FilmsSectionNetworkDataSource
import com.muammarahlnn.urflix.core.network.datasource.HomeNetworkDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import javax.inject.Inject


/**
 * @author Muammar Ahlan Abimanyu (muammarahlnn)
 * @file FilmsSectionRepositoryImpl, 04/11/2023 08.49 by Muammar Ahlan Abimanyu
 */
class FilmsSectionRepositoryImpl @Inject constructor(
    private val filmsSectionNetworkDataSource: FilmsSectionNetworkDataSource,
    private val homeNetworkDataSource: HomeNetworkDataSource,
) : FilmsSectionRepository {

    override suspend fun getMovies(section: MoviesSection, page: Int): Flow<List<FilmModel>> =
        combine(
            filmsSectionNetworkDataSource.getMovies(section, page),
            homeNetworkDataSource.getMovieGenres()
        ) { movieResponses, movieGenres ->
            movieResponses.toFilmModels(movieGenres)
        }

    override suspend fun getTvShows(section: TvShowsSection, page: Int): Flow<List<FilmModel>> =
        combine(
            filmsSectionNetworkDataSource.getTvShows(section, page),
            homeNetworkDataSource.getTvShowGenres()
        ) { tvShowResponses, tvShowGenres ->
            tvShowResponses.toFilmModels(tvShowGenres)
        }

    override suspend fun getMoviesWithGenres(genre: String, page: Int): Flow<List<FilmModel>> =
        combine(
            filmsSectionNetworkDataSource.getMoviesWithGenres(genre, page),
            homeNetworkDataSource.getMovieGenres()
        ) { movieResponses, movieGenres ->
            movieResponses.toFilmModels(movieGenres)
        }

    override suspend fun getTvShowsWithGenres(genre: String, page: Int): Flow<List<FilmModel>> =
        combine(
            filmsSectionNetworkDataSource.getTvShowsWithGenres(genre, page),
            homeNetworkDataSource.getTvShowGenres()
        ) { tvShowResponses, tvShowGenres ->
            tvShowResponses.toFilmModels(tvShowGenres)
        }

}