package com.muammarahlnn.homenavigator.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.muammarahlnn.homenavigator.HomeNavigatorRoute


/**
 * @author Muammar Ahlan Abimanyu (muammarahlnn)
 * @file HomeNavigatorNavigation, 02/11/2023 10.27 by Muammar Ahlan Abimanyu
 */
const val HOME_NAVIGATOR_ROUTE = "home_navigator_route"

fun NavGraphBuilder.homeNavigator(
    onFilmClick: () -> Unit,
    onCameraActionClick: () -> Unit,
) {
    composable(route = HOME_NAVIGATOR_ROUTE) {
        HomeNavigatorRoute(
            onFilmClick = onFilmClick,
            onCameraActionClick = onCameraActionClick,
        )
    }
}