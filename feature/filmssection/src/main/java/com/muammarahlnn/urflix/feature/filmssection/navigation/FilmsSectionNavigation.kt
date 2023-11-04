package com.muammarahlnn.urflix.feature.filmssection.navigation

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.muammarahlnn.urflix.feature.filmssection.FilmsSectionRoute


/**
 * @author Muammar Ahlan Abimanyu (muammarahlnn)
 * @file FilmsSectionNavigation, 04/11/2023 07.53 by Muammar Ahlan Abimanyu
 */

private const val FILMS_SECTION_ROUTE = "films_section_route"
private const val FILM_SECTION_ORDINAL_ARG = "film_section_ordinal_arg"
private const val FILM_TYPE_ORDINAL_ARG = "film_type_ordinal_arg"
private const val FILMS_SECTION_ROUTE_WITH_ARGS =
    "$FILMS_SECTION_ROUTE/{$FILM_SECTION_ORDINAL_ARG}/{$FILM_TYPE_ORDINAL_ARG}"

internal class FilmsSectionArgs(
    val filmSectionOrdinal: Int,
    val filmTypeOrdinal: Int,
) {

    constructor(
        savedStateHandle: SavedStateHandle,
    ) : this(
        filmSectionOrdinal = checkNotNull(savedStateHandle[FILM_SECTION_ORDINAL_ARG]),
        filmTypeOrdinal = checkNotNull(savedStateHandle[FILM_TYPE_ORDINAL_ARG]),
    )
}

fun NavController.navigateToFilmsSection(
    filmSectionOrdinal: Int,
    filmTypeOrdinal: Int,
) {
    this.navigate("$FILMS_SECTION_ROUTE/$filmSectionOrdinal/$filmTypeOrdinal") {
        launchSingleTop = true
    }
}

fun NavGraphBuilder.filmsSectionScreen(
    onBackClick: () -> Unit,
    onFilmClick: (Int, Int) -> Unit,
) {
    composable(
        route = FILMS_SECTION_ROUTE_WITH_ARGS,
        arguments = listOf(
            navArgument(FILM_SECTION_ORDINAL_ARG) {
                type = NavType.IntType
            },
            navArgument(FILM_TYPE_ORDINAL_ARG) {
                type = NavType.IntType
            },
        )
    ) {
        FilmsSectionRoute(
            onBackClick = onBackClick,
            onFilmClick = onFilmClick,
        )
    }
}