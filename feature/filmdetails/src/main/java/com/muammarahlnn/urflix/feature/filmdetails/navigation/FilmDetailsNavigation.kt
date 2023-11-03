package com.muammarahlnn.urflix.feature.filmdetails.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.muammarahlnn.urflix.feature.filmdetails.FilmDetailsRoute


/**
 * @author Muammar Ahlan Abimanyu (muammarahlnn)
 * @file FilmDetailsNavigation, 03/11/2023 14.17 by Muammar Ahlan Abimanyu
 */
private const val FILM_DETAILS_ROUTE = "film_details_route"

fun NavController.navigateToFilmDetails() {
    this.navigate(FILM_DETAILS_ROUTE)
}

fun NavGraphBuilder.filmsDetailsScreen() {
    composable(route = FILM_DETAILS_ROUTE) {
        FilmDetailsRoute()
    }
}