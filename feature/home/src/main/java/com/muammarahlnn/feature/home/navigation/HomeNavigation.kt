package com.muammarahlnn.feature.home.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.muammarahlnn.feature.home.HomeRoute


/**
 * @author Muammar Ahlan Abimanyu (muammarahlnn)
 * @file HomeNavigation, 02/11/2023 09.34 by Muammar Ahlan Abimanyu
 */
const val HOME_ROUTE = "home_route"

fun NavController.navigateToHome(navOptions: NavOptions? = null) {
    this.navigate(HOME_ROUTE, navOptions)
}

fun NavGraphBuilder.homeScreen(
    onFilmClick: (Int, Int) -> Unit,
) {
    composable(route = HOME_ROUTE) {
        HomeRoute(
            onFilmClick = onFilmClick,
        )
    }
}