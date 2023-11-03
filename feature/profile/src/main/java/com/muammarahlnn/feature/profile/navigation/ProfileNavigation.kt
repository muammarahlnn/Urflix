package com.muammarahlnn.feature.profile.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.muammarahlnn.feature.profile.ProfileRoute


/**
 * @author Muammar Ahlan Abimanyu (muammarahlnn)
 * @file ProfileNavigation, 02/11/2023 09.35 by Muammar Ahlan Abimanyu
 */
const val PROFILE_ROUTE = "profile_route"

fun NavController.navigateToProfile(navOptions: NavOptions? = null) {
    this.navigate(PROFILE_ROUTE, navOptions)
}

fun NavGraphBuilder.profileScreen(
    onCameraActionClick: () -> Unit,
) {
    composable(route = PROFILE_ROUTE) {
        ProfileRoute(
            onCameraActionClick = onCameraActionClick,
        )
    }
}