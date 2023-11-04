package com.muammarahlnn.urflix.feature.filmdetails

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.muammarahlnn.urflix.core.common.result.Result
import com.muammarahlnn.urflix.core.common.result.asResult
import com.muammarahlnn.urflix.core.common.result.onError
import com.muammarahlnn.urflix.core.common.result.onLoading
import com.muammarahlnn.urflix.core.common.result.onSuccess
import com.muammarahlnn.urflix.core.data.repository.FilmDetailsRepository
import com.muammarahlnn.urflix.core.model.data.FilmDetailsModel
import com.muammarahlnn.urflix.core.model.ui.FilmType
import com.muammarahlnn.urflix.feature.filmdetails.navigation.FilmDetailsArgs
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


/**
 * @author Muammar Ahlan Abimanyu (muammarahlnn)
 * @file FilmDetailsViewModel, 03/11/2023 19.36 by Muammar Ahlan Abimanyu
 */
@HiltViewModel
class FilmDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val filmDetailsRepository: FilmDetailsRepository
) : ViewModel() {

    private val args = FilmDetailsArgs(savedStateHandle)

    private val filmId = args.filmId

    val filmType = FilmType.getFilmType(args.filmTypeOrdinal)

    private val _filmDetailsUiState = MutableStateFlow<FilmDetailsUiState>(FilmDetailsUiState.Loading)

    val filmDetailsUiState = _filmDetailsUiState.asStateFlow()

    private val _isFilmBookmarked = MutableStateFlow(false)

    val isFilmBookmarked = _isFilmBookmarked.asStateFlow()

    init {
        refreshFilmDetailsScreen()
    }

    fun refreshFilmDetailsScreen() {
        fetchFilmDetailsData()
        loadIsFilmBookmarked()
    }

    private fun fetchFilmDetailsData() {
        when (filmType) {
            FilmType.MOVIES -> fetchMovieDetailsData()
            FilmType.TV_SHOWS -> fetchTvShowDetailsData()
        }
    }

    private fun fetchMovieDetailsData() {
        viewModelScope.launch {
            filmDetailsRepository.getMovieDetails(filmId)
                .asResult()
                .collect { result ->
                    handleFetchFilmDetails(result, _filmDetailsUiState)
                }
        }
    }

    private fun fetchTvShowDetailsData() {
        viewModelScope.launch {
            filmDetailsRepository.getTvShowDetails(filmId)
                .asResult()
                .collect { result ->
                    handleFetchFilmDetails(result, _filmDetailsUiState)
                }
        }
    }

    private fun loadIsFilmBookmarked() {
        viewModelScope.launch {
            filmDetailsRepository.isFilmBookmarked(filmId).collect { bookmarked ->
                _isFilmBookmarked.update {
                    bookmarked
                }
            }
        }
    }

    fun insertBookmarkedFilm(filmDetails: FilmDetailsModel) {
        viewModelScope.launch {
            filmDetailsRepository.insertBookmarkedFilm(filmDetails)
        }
    }

    fun deleteBookmarkedFilm() {
        viewModelScope.launch {
            filmDetailsRepository.deleteBookmarkedFilm(filmId)
        }
    }

    private fun handleFetchFilmDetails(
        filmDetailsResult: Result<FilmDetailsModel>,
        filmDetailsUiState: MutableStateFlow<FilmDetailsUiState>,
    ) {
        filmDetailsResult.onLoading {
            filmDetailsUiState.update {
                FilmDetailsUiState.Loading
            }
        }.onSuccess { filmDetails ->
            filmDetailsUiState.update {
                FilmDetailsUiState.Success(filmDetails)
            }
        }.onError { exception, message ->
            Log.e("FilmDetailsViewModel", exception?.message.toString())
            filmDetailsUiState.update {
                FilmDetailsUiState.Error(message)
            }
        }
    }
}