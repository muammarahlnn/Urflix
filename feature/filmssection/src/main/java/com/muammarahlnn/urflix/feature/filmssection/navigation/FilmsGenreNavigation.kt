package com.muammarahlnn.urflix.feature.filmssection.navigation

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.muammarahlnn.urflix.feature.filmssection.FilmsGenreRoute


/**
 * @author Muammar Ahlan Abimanyu (muammarahlnn)
 * @file FilmsGenreNavigation, 04/11/2023 16.45 by Muammar Ahlan Abimanyu
 */
private const val FILMS_GENRE_ROUTE = "films_genre_route"
private const val GENRE_ID_ARG = "genre_id_arg"
private const val GENRE_NAME_ARG = "genre_name_arg"
private const val FILM_TYPE_ORDINAL_ARG = "film_type_ordinal_arg"
private const val FILMS_GENRE_ROUTE_WITH_ARGS =
    "$FILMS_GENRE_ROUTE/{$GENRE_ID_ARG}/{$GENRE_NAME_ARG}/{$FILM_TYPE_ORDINAL_ARG}"

internal class FilmsGenreArgs(
    val genreId: Int,
    val genreName: String,
    val filmTypeOrdinal: Int,
) {

    constructor(
        savedStateHandle: SavedStateHandle,
    ) : this(
        genreId = checkNotNull(savedStateHandle[GENRE_ID_ARG]),
        genreName = checkNotNull(savedStateHandle[GENRE_NAME_ARG]),
        filmTypeOrdinal = checkNotNull(savedStateHandle[FILM_TYPE_ORDINAL_ARG]),
    )
}

fun NavController.navigateToFilmsGenre(
    genreId: Int,
    genreName: String,
    filmTypeOrdinal: Int,
) {
    this.navigate("$FILMS_GENRE_ROUTE/$genreId/$genreName/$filmTypeOrdinal") {
        launchSingleTop = true
    }
}

fun NavGraphBuilder.filmsGenreScreen(
    onBackClick: () -> Unit,
    onFilmClick: (Int, Int) -> Unit,
) {
    composable(
        route = FILMS_GENRE_ROUTE_WITH_ARGS,
        arguments = listOf(
            navArgument(GENRE_ID_ARG) {
                type = NavType.IntType
            },
            navArgument(GENRE_NAME_ARG) {
                type = NavType.StringType
            },
            navArgument(FILM_TYPE_ORDINAL_ARG) {
                type = NavType.IntType
            },
        )
    ) {
        FilmsGenreRoute(
            onBackClick = onBackClick,
            onFilmClick = onFilmClick,
        )
    }
}