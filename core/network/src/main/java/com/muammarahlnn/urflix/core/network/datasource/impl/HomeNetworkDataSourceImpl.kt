package com.muammarahlnn.urflix.core.network.datasource.impl

import com.muammarahlnn.urflix.core.model.data.constant.MoviesSection
import com.muammarahlnn.urflix.core.model.data.constant.TvShowsSection
import com.muammarahlnn.urflix.core.network.api.GenreApi
import com.muammarahlnn.urflix.core.network.api.MovieApi
import com.muammarahlnn.urflix.core.network.api.PersonApi
import com.muammarahlnn.urflix.core.network.api.TrendingApi
import com.muammarahlnn.urflix.core.network.api.TvShowApi
import com.muammarahlnn.urflix.core.network.datasource.HomeNetworkDataSource
import com.muammarahlnn.urflix.core.network.model.response.GenreResponse
import com.muammarahlnn.urflix.core.network.model.response.MovieResponse
import com.muammarahlnn.urflix.core.network.model.response.PersonResponse
import com.muammarahlnn.urflix.core.network.model.response.TvShowResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton


/**
 * @author Muammar Ahlan Abimanyu (muammarahlnn)
 * @file HomeNetworkDataSourceImpl, 02/11/2023 12.07 by Muammar Ahlan Abimanyu
 */
@Singleton
class HomeNetworkDataSourceImpl @Inject constructor(
    private val movieApi: MovieApi,
    private val tvShowApi: TvShowApi,
    private val genreApi: GenreApi,
    private val trendingApi: TrendingApi,
    private val personApi: PersonApi,
) : HomeNetworkDataSource {

    override fun getMovies(section: MoviesSection): Flow<List<MovieResponse>> = flow {
        when(section) {
            MoviesSection.NOW_PLAYING -> emit(movieApi.getNowPlayingMovies().results)
            MoviesSection.UPCOMING -> emit(movieApi.getUpcomingMovies().results)
            MoviesSection.POPULAR -> emit(movieApi.getPopularMovies().results)
            MoviesSection.TOP_RATED -> emit(movieApi.getTopRatedMovies().results)
        }
    }

    override fun getTvShows(section: TvShowsSection): Flow<List<TvShowResponse>> = flow {
        when (section) {
            TvShowsSection.AIRING_TODAY -> emit(tvShowApi.getAiringTodayTvShows().results)
            TvShowsSection.ON_THE_AIR -> emit(tvShowApi.getOnTheAirTvShows().results)
            TvShowsSection.POPULAR -> emit(tvShowApi.getPopularTvShows().results)
            TvShowsSection.TOP_RATED -> emit(tvShowApi.getTopRatedTvShows().results)
        }
    }

    override fun getMovieGenres(): Flow<List<GenreResponse>> = flow {
        emit(genreApi.getMovieGenres().genres)
    }

    override fun getTvShowGenres(): Flow<List<GenreResponse>> = flow {
        emit(genreApi.getTvShowGenres().genres)
    }

    override fun getTrendingMovies(): Flow<List<MovieResponse>> = flow {
        emit(trendingApi.getTrendingMovies().results)
    }

    override fun getPopularPeople(): Flow<List<PersonResponse>> = flow {
        emit(personApi.getPopularPeople().results)
    }
}