package com.muammarahlnn.urflix.core.network.datasource.impl

import com.muammarahlnn.urflix.core.model.data.constant.MoviesSection
import com.muammarahlnn.urflix.core.model.data.constant.TvShowsSection
import com.muammarahlnn.urflix.core.network.api.DiscoverApi
import com.muammarahlnn.urflix.core.network.api.MovieApi
import com.muammarahlnn.urflix.core.network.api.TvShowApi
import com.muammarahlnn.urflix.core.network.datasource.FilmsSectionNetworkDataSource
import com.muammarahlnn.urflix.core.network.model.response.MovieResponse
import com.muammarahlnn.urflix.core.network.model.response.TvShowResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton


/**
 * @author Muammar Ahlan Abimanyu (muammarahlnn)
 * @file FilmsSectionNetworkDataSourceImpl, 04/11/2023 08.42 by Muammar Ahlan Abimanyu
 */
@Singleton
class FilmsSectionNetworkDataSourceImpl @Inject constructor(
    private val movieApi: MovieApi,
    private val tvShowApi: TvShowApi,
    private val discoverApi: DiscoverApi,
) : FilmsSectionNetworkDataSource {

    override fun getMovies(
        section: MoviesSection,
        page: Int
    ): Flow<List<MovieResponse>> = flow {
        when(section) {
            MoviesSection.NOW_PLAYING -> emit(movieApi.getNowPlayingMovies(page = page).results)
            MoviesSection.UPCOMING -> emit(movieApi.getUpcomingMovies(page = page).results)
            MoviesSection.POPULAR -> emit(movieApi.getPopularMovies(page = page).results)
            MoviesSection.TOP_RATED -> emit(movieApi.getTopRatedMovies(page = page).results)
        }
    }

    override fun getTvShows(
        section: TvShowsSection,
        page: Int
    ): Flow<List<TvShowResponse>> = flow {
        when (section) {
            TvShowsSection.AIRING_TODAY -> emit(tvShowApi.getAiringTodayTvShows(page = page).results)
            TvShowsSection.ON_THE_AIR -> emit(tvShowApi.getOnTheAirTvShows(page = page).results)
            TvShowsSection.POPULAR -> emit(tvShowApi.getPopularTvShows(page = page).results)
            TvShowsSection.TOP_RATED -> emit(tvShowApi.getTopRatedTvShows(page = page).results)
        }
    }

    override fun getMoviesWithGenres(genre: String, page: Int): Flow<List<MovieResponse>> =
        flow {
            emit(discoverApi.getMoviesWithGenres(genre, page).results)
        }

    override fun getTvShowsWithGenres(genre: String, page: Int): Flow<List<TvShowResponse>> =
        flow {
            emit(discoverApi.getTvShowsWithGenres(genre, page).results)
        }
}