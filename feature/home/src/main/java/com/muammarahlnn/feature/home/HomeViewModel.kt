package com.muammarahlnn.feature.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.muammarahlnn.urflix.core.common.result.Result
import com.muammarahlnn.urflix.core.common.result.asResult
import com.muammarahlnn.urflix.core.common.result.onError
import com.muammarahlnn.urflix.core.common.result.onLoading
import com.muammarahlnn.urflix.core.common.result.onSuccess
import com.muammarahlnn.urflix.core.data.repository.HomeRepository
import com.muammarahlnn.urflix.core.model.data.FilmModel
import com.muammarahlnn.urflix.core.model.data.constant.MoviesSection
import com.muammarahlnn.urflix.core.model.data.constant.TvShowsSection
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


/**
 * @author Muammar Ahlan Abimanyu (muammarahlnn)
 * @file HomeViewModel, 02/11/2023 14.01 by Muammar Ahlan Abimanyu
 */
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeRepository: HomeRepository
) : ViewModel() {

    private val _nowPlayingMoviesUiState = MutableStateFlow<FilmsSectionUiState>(FilmsSectionUiState.Loading)

    val nowPlayingMoviesUiState = _nowPlayingMoviesUiState.asStateFlow()

    private val _upcomingMoviesUiState = MutableStateFlow<FilmsSectionUiState>(FilmsSectionUiState.Loading)

    val upcomingMoviesUiState = _upcomingMoviesUiState.asStateFlow()

    private val _popularMoviesUiState = MutableStateFlow<FilmsSectionUiState>(FilmsSectionUiState.Loading)

    val popularMoviesUiState = _popularMoviesUiState.asStateFlow()

    private val _topRatedMoviesUiState = MutableStateFlow<FilmsSectionUiState>(FilmsSectionUiState.Loading)

    val topRatedMoviesUiState = _topRatedMoviesUiState.asStateFlow()

    private val _airingTodayTvShowsUiState = MutableStateFlow<FilmsSectionUiState>(FilmsSectionUiState.Loading)

    val airingTodayTvShowsUiState = _airingTodayTvShowsUiState.asStateFlow()

    private val _onTheAirTvShowsUiState = MutableStateFlow<FilmsSectionUiState>(FilmsSectionUiState.Loading)

    val onTheAirTvShowsUiState = _onTheAirTvShowsUiState.asStateFlow()

    private val _popularTvShowsUiState = MutableStateFlow<FilmsSectionUiState>(FilmsSectionUiState.Loading)

    val popularTvShowsUiState = _popularTvShowsUiState.asStateFlow()

    private val _topRatedTvShowsUiState = MutableStateFlow<FilmsSectionUiState>(FilmsSectionUiState.Loading)

    val topRatedTvShowsUiState = _topRatedTvShowsUiState.asStateFlow()

    private val _genresUiState = MutableStateFlow<GenresSectionUiState>(GenresSectionUiState.Loading)

    val genresUiState = _genresUiState.asStateFlow()

    init {
        fetchHomeScreenData()
    }

    private fun fetchHomeScreenData() {
        fetchNowPlayingMovies()
        fetchUpcomingMovies()
        fetchPopularMovies()
        fetchTopRatedMovies()

        fetchAiringTodayTvShows()
        fetchOnTheAirTvShows()
        fetchPopularTvShows()
        fetchTopRatedTvShows()

        fetchGenres()
    }

    private fun fetchNowPlayingMovies() {
        viewModelScope.launch {
            homeRepository.getMovies(MoviesSection.NOW_PLAYING).asResult().collect { result ->
                handleFetchFilms(result, _nowPlayingMoviesUiState)
            }
        }
    }

    private fun fetchUpcomingMovies() {
        viewModelScope.launch {
            homeRepository.getMovies(MoviesSection.UPCOMING).asResult().collect { result ->
                handleFetchFilms(result, _upcomingMoviesUiState)
            }
        }
    }

    private fun fetchPopularMovies() {
        viewModelScope.launch {
            homeRepository.getMovies(MoviesSection.POPULAR).asResult().collect { result ->
                handleFetchFilms(result, _popularMoviesUiState)
            }
        }
    }

    private fun fetchTopRatedMovies() {
        viewModelScope.launch {
            homeRepository.getMovies(MoviesSection.TOP_RATED).asResult().collect { result ->
                handleFetchFilms(result, _topRatedMoviesUiState)
            }
        }
    }

    private fun fetchAiringTodayTvShows() {
        viewModelScope.launch {
            homeRepository.getTvShows(TvShowsSection.AIRING_TODAY).asResult().collect { result ->
                handleFetchFilms(result, _airingTodayTvShowsUiState)
            }
        }
    }

    private fun fetchOnTheAirTvShows() {
        viewModelScope.launch {
            homeRepository.getTvShows(TvShowsSection.ON_THE_AIR).asResult().collect { result ->
                handleFetchFilms(result, _onTheAirTvShowsUiState)
            }
        }
    }

    private fun fetchPopularTvShows() {
        viewModelScope.launch {
            homeRepository.getTvShows(TvShowsSection.POPULAR).asResult().collect { result ->
                handleFetchFilms(result, _popularTvShowsUiState)
            }
        }
    }

    private fun fetchTopRatedTvShows() {
        viewModelScope.launch {
            homeRepository.getTvShows(TvShowsSection.TOP_RATED).asResult().collect { result ->
                handleFetchFilms(result, _topRatedTvShowsUiState)
            }
        }
    }

    private fun fetchGenres() {
        viewModelScope.launch {
            combine(
                homeRepository.getMovieGenres(),
                homeRepository.getTvShowGenres(),
                ::Pair
            )
                .asResult()
                .collect { result ->
                    result.onLoading {
                        _genresUiState.update {
                            GenresSectionUiState.Loading
                        }
                    }.onSuccess {
                        val (movieGenres,  tvShowGenres) = it
                        _genresUiState.update {
                            GenresSectionUiState.Success(
                                movieGenres = movieGenres,
                                tvShowGenres = tvShowGenres,
                            )
                        }
                    }.onError { exception, message ->
                        Log.e("HomeViewModel", exception?.message.toString())
                        _genresUiState.update {
                            GenresSectionUiState.Error(message)
                        }
                    }
                }
        }
    }

    private fun handleFetchFilms(
        resultFilms: Result<List<FilmModel>>,
        filmsUiState: MutableStateFlow<FilmsSectionUiState>,
    ) {
        resultFilms.onLoading {
            filmsUiState.update {
                FilmsSectionUiState.Loading
            }
        }.onSuccess { films ->
            filmsUiState.update {
                FilmsSectionUiState.Success(films)
            }
        }.onError { exception, message ->
            Log.e("HomeViewModel", exception?.message.toString())
            filmsUiState.update {
                FilmsSectionUiState.Error(message)
            }
        }
    }
}