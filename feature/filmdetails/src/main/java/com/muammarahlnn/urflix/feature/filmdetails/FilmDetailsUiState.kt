package com.muammarahlnn.urflix.feature.filmdetails

import com.muammarahlnn.urflix.core.model.data.FilmDetailsModel


/**
 * @author Muammar Ahlan Abimanyu (muammarahlnn)
 * @file FilmDetailsUiState, 03/11/2023 19.54 by Muammar Ahlan Abimanyu
 */
sealed interface FilmDetailsUiState {

    data object Loading : FilmDetailsUiState

    data class Success(val filmDetails: FilmDetailsModel) : FilmDetailsUiState

    data class Error(val message: String) : FilmDetailsUiState
}