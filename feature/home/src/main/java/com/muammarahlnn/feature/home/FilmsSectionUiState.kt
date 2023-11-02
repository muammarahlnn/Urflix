package com.muammarahlnn.feature.home

import com.muammarahlnn.urflix.core.model.data.FilmModel


/**
 * @author Muammar Ahlan Abimanyu (muammarahlnn)
 * @file FilmSectionUiState, 02/11/2023 15.21 by Muammar Ahlan Abimanyu
 */
sealed interface FilmsSectionUiState {

    data object Loading : FilmsSectionUiState

    data class Success(val films: List<FilmModel>) : FilmsSectionUiState

    data class Error(val message: String) : FilmsSectionUiState
}