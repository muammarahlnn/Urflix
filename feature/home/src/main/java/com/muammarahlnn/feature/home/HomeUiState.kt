package com.muammarahlnn.feature.home

import com.muammarahlnn.urflix.core.common.result.UiState
import com.muammarahlnn.urflix.core.model.data.FilmModel
import com.muammarahlnn.urflix.core.model.data.GenreModel
import com.muammarahlnn.urflix.core.model.data.PersonModel


/**
 * @author Muammar Ahlan Abimanyu (muammarahlnn)
 * @file FilmSectionUiState, 02/11/2023 15.21 by Muammar Ahlan Abimanyu
 */
sealed interface FilmsSectionUiState {

    data object Loading : FilmsSectionUiState

    data class Success(val films: List<FilmModel>) : FilmsSectionUiState

    data class Error(val message: String) : FilmsSectionUiState
}

sealed interface GenresSectionUiState {

    data object Loading : GenresSectionUiState

    data class Success(
        val movieGenres: List<GenreModel>,
        val tvShowGenres: List<GenreModel>,
    ) : GenresSectionUiState

    data class Error(val message: String) : GenresSectionUiState
}

typealias PeopleSectionUiState = UiState<List<PersonModel>>