package com.muammarahlnn.urflix.feature.genres

import com.muammarahlnn.urflix.core.model.data.GenreModel


/**
 * @author Muammar Ahlan Abimanyu (muammarahlnn)
 * @file GenresUiState, 04/11/2023 15.09 by Muammar Ahlan Abimanyu
 */
sealed interface GenresUiState  {

    data object Loading : GenresUiState

    data class Success(val genres: List<GenreModel>) : GenresUiState

    data class Error(val message: String) : GenresUiState
}