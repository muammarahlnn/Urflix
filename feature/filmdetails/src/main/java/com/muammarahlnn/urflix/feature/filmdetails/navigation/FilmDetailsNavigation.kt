package com.muammarahlnn.urflix.feature.filmdetails.navigation

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.muammarahlnn.urflix.feature.filmdetails.FilmDetailsRoute


/**
 * @author Muammar Ahlan Abimanyu (muammarahlnn)
 * @file FilmDetailsNavigation, 03/11/2023 14.17 by Muammar Ahlan Abimanyu
 */
private const val FILM_DETAILS_ROUTE = "film_details_route"
private const val FILM_ID_ARG = "film_id"
private const val FILM_TYPE_ORDINAL_ARG = "film_type_ordinal"
private const val FILM_DETAILS_ROUTE_WITH_ARGS =
    "$FILM_DETAILS_ROUTE/{$FILM_ID_ARG}/{$FILM_TYPE_ORDINAL_ARG}"

internal class FilmDetailsArgs(
    val filmId: Int,
    val filmTypeOrdinal: Int,
) {

    constructor(
        savedStateHandle: SavedStateHandle
    ) : this(
        filmId = checkNotNull(savedStateHandle[FILM_ID_ARG]),
        filmTypeOrdinal = checkNotNull(savedStateHandle[FILM_TYPE_ORDINAL_ARG])
    )
}

fun NavController.navigateToFilmDetails(
    filmId: Int,
    filmTypeOrdinal: Int,
) {
    this.navigate("$FILM_DETAILS_ROUTE/$filmId/$filmTypeOrdinal") {
        launchSingleTop = true
    }
}

fun NavGraphBuilder.filmsDetailsScreen(
    onBackClick: () -> Unit,
) {
    composable(
        route = FILM_DETAILS_ROUTE_WITH_ARGS,
        arguments = listOf(
            navArgument(FILM_ID_ARG) {
                type = NavType.IntType
            },
            navArgument(FILM_TYPE_ORDINAL_ARG) {
                type = NavType.IntType
            },
        )
    ) {
        FilmDetailsRoute(
            onBackClick = onBackClick,
        )
    }
}