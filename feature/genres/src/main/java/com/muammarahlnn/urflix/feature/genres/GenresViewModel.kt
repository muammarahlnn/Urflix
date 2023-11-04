package com.muammarahlnn.urflix.feature.genres

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.muammarahlnn.urflix.core.common.result.asResult
import com.muammarahlnn.urflix.core.common.result.onError
import com.muammarahlnn.urflix.core.common.result.onLoading
import com.muammarahlnn.urflix.core.common.result.onSuccess
import com.muammarahlnn.urflix.core.data.repository.HomeRepository
import com.muammarahlnn.urflix.core.model.ui.FilmType
import com.muammarahlnn.urflix.feature.genres.navigation.GenresArgs
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


/**
 * @author Muammar Ahlan Abimanyu (muammarahlnn)
 * @file GenresViewModel, 04/11/2023 15.08 by Muammar Ahlan Abimanyu
 */
@HiltViewModel
class GenresViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val homeRepository: HomeRepository,
) : ViewModel() {

    private val args = GenresArgs(savedStateHandle)

    val filmType = FilmType.getFilmType(args.filmTypeOrdinal)

    private val _genresUiState = MutableStateFlow<GenresUiState>(GenresUiState.Loading)

    val genresUiState = _genresUiState.asStateFlow()


    init {
        fetchGenres()
    }

    fun fetchGenres() {
        viewModelScope.launch {
            when (filmType) {
                FilmType.MOVIES -> homeRepository.getMovieGenres()
                FilmType.TV_SHOWS -> homeRepository.getTvShowGenres()
            }
                .asResult()
                .collect { result ->
                    result.onLoading {
                        _genresUiState.update {
                            GenresUiState.Loading
                        }
                    }.onSuccess { genres ->
                        _genresUiState.update {
                            GenresUiState.Success(genres)
                        }
                    }.onError { exception, message ->
                        Log.e("GenresViewModel", exception?.message.toString())
                        _genresUiState.update {
                            GenresUiState.Error(message)
                        }
                    }
                }
        }
    }
}