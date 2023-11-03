package com.muammarahlnn.feature.search

import com.muammarahlnn.urflix.core.model.data.FilmModel


/**
 * @author Muammar Ahlan Abimanyu (muammarahlnn)
 * @file SearchUiState, 04/11/2023 01.58 by Muammar Ahlan Abimanyu
 */
sealed interface SearchUiState {

    data object None : SearchUiState

    data object Loading : SearchUiState

    data class Success(
        val searchedMovies: List<FilmModel>,
        val searchedTvShows: List<FilmModel>,
    ) : SearchUiState

    data class Error(val message: String) : SearchUiState
}