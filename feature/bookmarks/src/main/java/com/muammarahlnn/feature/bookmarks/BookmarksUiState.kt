package com.muammarahlnn.feature.bookmarks

import com.muammarahlnn.urflix.core.model.data.FilmModel


/**
 * @author Muammar Ahlan Abimanyu (muammarahlnn)
 * @file BookmarksUiState, 03/11/2023 22.17 by Muammar Ahlan Abimanyu
 */
sealed interface BookmarksUiState {

    data object Loading : BookmarksUiState

    data class Success(val films: List<FilmModel>) : BookmarksUiState

    data class SuccessEmpty(
        val message: String = "You haven't bookmarked any films yet"
    ) : BookmarksUiState

    data class Error(val message: String) : BookmarksUiState
}