package com.muammarahlnn.urflix.feature.filmssection

import com.muammarahlnn.urflix.core.model.data.FilmModel


/**
 * @author Muammar Ahlan Abimanyu (muammarahlnn)
 * @file FilmsUiState, 04/11/2023 16.08 by Muammar Ahlan Abimanyu
 */
sealed interface FilmsUiState {

    data object Loading : FilmsUiState

    data class Success(val films: List<FilmModel>) : FilmsUiState

    data class Error(val message: String) : FilmsUiState
}