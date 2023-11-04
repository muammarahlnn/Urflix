package com.muammarahlnn.urflix.feature.filmssection.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.muammarahlnn.urflix.core.common.result.asResult
import com.muammarahlnn.urflix.core.common.result.onError
import com.muammarahlnn.urflix.core.common.result.onLoading
import com.muammarahlnn.urflix.core.common.result.onSuccess
import com.muammarahlnn.urflix.core.model.data.FilmModel
import com.muammarahlnn.urflix.core.model.ui.FilmType
import com.muammarahlnn.urflix.feature.filmssection.FilmsUiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


/**
 * @author Muammar Ahlan Abimanyu (muammarahlnn)
 * @file FilmsSectionViewModel, 04/11/2023 08.54 by Muammar Ahlan Abimanyu
 */

abstract class FilmsViewModel : ViewModel() {

    abstract val filmType: FilmType

    protected abstract suspend fun fetchMovies() : Flow<List<FilmModel>>

    protected abstract suspend fun fetchTvShows() : Flow<List<FilmModel>>

    private val films = mutableListOf<FilmModel>()

    protected var currentPage = 1

    private val _filmsUiState = MutableStateFlow<FilmsUiState>(FilmsUiState.Loading)

    val filmsUiState = _filmsUiState.asStateFlow()

    private val _loadingNextPageState = MutableStateFlow(false)

    val loadingNextPageState = _loadingNextPageState.asStateFlow()

    fun refresh() {
        films.clear()
        currentPage = 1
        fetchFilms()
    }

    fun fetchNextPage() {
        currentPage++
        fetchFilms()
    }

    protected fun fetchFilms() {
        viewModelScope.launch {
            when (filmType) {
                FilmType.MOVIES -> fetchMovies().handleFetchFilms()
                FilmType.TV_SHOWS -> fetchTvShows().handleFetchFilms()
            }
        }
    }

    private suspend fun Flow<List<FilmModel>>.handleFetchFilms() {
        this.asResult().collect { result ->
            result.onLoading {
                if (currentPage == 1) {
                    _filmsUiState.update {
                        FilmsUiState.Loading
                    }
                } else {
                    _loadingNextPageState.update { true }
                }
            }.onSuccess {
                films.addAll(it)
                _filmsUiState.update {
                    FilmsUiState.Success(films)
                }
                _loadingNextPageState.update { false }
            }.onError { exception, message ->
                Log.e("FilmsViewModel", exception?.message.toString())
                _filmsUiState.update {
                    FilmsUiState.Error(message)
                }
                _loadingNextPageState.update { false }
            }
        }
    }
}