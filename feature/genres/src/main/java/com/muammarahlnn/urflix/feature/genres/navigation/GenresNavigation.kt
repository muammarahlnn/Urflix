package com.muammarahlnn.urflix.feature.genres.navigation

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.muammarahlnn.urflix.feature.genres.GenresRoute


/**
 * @author Muammar Ahlan Abimanyu (muammarahlnn)
 * @file GenresNavigation, 04/11/2023 15.03 by Muammar Ahlan Abimanyu
 */
private const val GENRES_ROUTE = "genres_route"
private const val FILM_TYPE_ORDINAL_ARG = "film_type_ordinal"
private const val GENRES_ROUTE_WITH_ARGS =
    "$GENRES_ROUTE/{$FILM_TYPE_ORDINAL_ARG}"

internal class GenresArgs(
    val filmTypeOrdinal: Int,
) {

    constructor(
        savedStateHandle: SavedStateHandle,
    ) : this(
        filmTypeOrdinal = checkNotNull(savedStateHandle[FILM_TYPE_ORDINAL_ARG])
    )
}

fun NavController.navigateToGenres(
    filmTypeOrdinal: Int,
) {
    this.navigate("$GENRES_ROUTE/$filmTypeOrdinal") {
        launchSingleTop = true
    }
}

fun NavGraphBuilder.genresScreen(
    onBackClick: () -> Unit,
    onGenreItemClick: (Int, String, Int) -> Unit,
) {
    composable(
        route = GENRES_ROUTE_WITH_ARGS,
        arguments = listOf(
            navArgument(FILM_TYPE_ORDINAL_ARG) {
                type = NavType.IntType
            },
        ),
    ) {
        GenresRoute(
            onBackClick = onBackClick,
            onGenreItemClick = onGenreItemClick,
        )
    }
}