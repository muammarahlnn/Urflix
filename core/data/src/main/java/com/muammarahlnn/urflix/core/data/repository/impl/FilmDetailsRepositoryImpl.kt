package com.muammarahlnn.urflix.core.data.repository.impl

import com.muammarahlnn.urflix.core.data.mapper.toFilmDetailsModel
import com.muammarahlnn.urflix.core.data.mapper.toFilmEntity
import com.muammarahlnn.urflix.core.data.repository.FilmDetailsRepository
import com.muammarahlnn.urflix.core.database.dao.FilmDao
import com.muammarahlnn.urflix.core.model.data.FilmDetailsModel
import com.muammarahlnn.urflix.core.network.datasource.FilmDetailsNetworkDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import javax.inject.Inject


/**
 * @author Muammar Ahlan Abimanyu (muammarahlnn)
 * @file FilmDetailsRepositoryImpl, 03/11/2023 19.00 by Muammar Ahlan Abimanyu
 */
class FilmDetailsRepositoryImpl @Inject constructor(
    private val filmDetailsNetworkDataSource: FilmDetailsNetworkDataSource,
    private val filmDao: FilmDao,
) : FilmDetailsRepository {

    override fun getMovieDetails(movieId: Int): Flow<FilmDetailsModel> =
        combine(
            filmDetailsNetworkDataSource.getMovieDetails(movieId),
            filmDetailsNetworkDataSource.getMovieImages(movieId)
        ) { movieDetails, movieImages ->
            movieDetails.toFilmDetailsModel(movieImages.backdrops)
        }


    override fun getTvShowDetails(tvShowId: Int): Flow<FilmDetailsModel> =
        combine(
            filmDetailsNetworkDataSource.getTvShowDetails(tvShowId),
            filmDetailsNetworkDataSource.getTvShowImages(tvShowId)
        ) { tvShowDetails, tvShowImages ->
            tvShowDetails.toFilmDetailsModel(tvShowImages.backdrops)
        }

    override fun isFilmBookmarked(filmId: Int): Flow<Boolean> =
        filmDao.isBookmarkedFilmExists(filmId)

    override suspend fun insertBookmarkedFilm(film: FilmDetailsModel) =
        filmDao.insertBookmarkedFilm(film.toFilmEntity())

    override suspend fun deleteBookmarkedFilm(filmId: Int) =
        filmDao.deleteBookmarkedFilm(filmId)
}