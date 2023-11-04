package com.muammarahlnn.feature.home

import com.muammarahlnn.urflix.core.model.data.GenreModel


/**
 * @author Muammar Ahlan Abimanyu (muammarahlnn)
 * @file GenresSectionUiState, 04/11/2023 09.23 by Muammar Ahlan Abimanyu
 */
sealed interface GenresSectionUiState {

    data object Loading : GenresSectionUiState

    data class Success(
        val movieGenres: List<GenreModel>,
        val tvShowGenres: List<GenreModel>,
    ) : GenresSectionUiState

    data class Error(val message: String) : GenresSectionUiState
}