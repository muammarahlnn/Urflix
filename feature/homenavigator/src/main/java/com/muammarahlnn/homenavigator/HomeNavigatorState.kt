package com.muammarahlnn.homenavigator

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.muammarahlnn.feature.bookmarks.navigation.BOOKMARKS_ROUTE
import com.muammarahlnn.feature.bookmarks.navigation.navigateToBookmarks
import com.muammarahlnn.feature.home.navigation.HOME_ROUTE
import com.muammarahlnn.feature.home.navigation.navigateToHome
import com.muammarahlnn.feature.profile.navigation.PROFILE_ROUTE
import com.muammarahlnn.feature.profile.navigation.navigateToProfile
import com.muammarahlnn.feature.search.navigation.SEARCH_ROUTE
import com.muammarahlnn.feature.search.navigation.navigateToSearch
import com.muammarahlnn.homenavigator.navigation.HomeDestination


/**
 * @author Muammar Ahlan Abimanyu (muammarahlnn)
 * @file HomeNavigatorState, 02/11/2023 09.40 by Muammar Ahlan Abimanyu
 */
@Composable
internal fun rememberHomeNavigatorState(
    navController: NavHostController = rememberNavController(),
): HomeNavigatorState {
    return remember(navController) {
        HomeNavigatorState(navController)
    }
}

@Stable
internal class HomeNavigatorState(
    val navController: NavHostController,
) {

    val currentDestination: NavDestination?
        @Composable
        get() = navController.currentBackStackEntryAsState().value?.destination

    val homeDestinations = HomeDestination.values().asList()

    val currentHomeDestination: HomeDestination?
        @Composable
        get() = when (currentDestination?.route) {
            HOME_ROUTE -> HomeDestination.HOME
            SEARCH_ROUTE -> HomeDestination.SEARCH
            BOOKMARKS_ROUTE -> HomeDestination.BOOKMARKS
            PROFILE_ROUTE -> HomeDestination.PROFILE
            else -> null
        }

    fun navigateToHomeDestination(homeDestination: HomeDestination) {
        val navOptions = navOptions {
            popUpTo(homeDestination.route)
            launchSingleTop = true
            restoreState = true
        }

        when (homeDestination) {
            HomeDestination.HOME -> navController.navigateToHome(navOptions)
            HomeDestination.SEARCH -> navController.navigateToSearch(navOptions)
            HomeDestination.BOOKMARKS -> navController.navigateToBookmarks(navOptions)
            HomeDestination.PROFILE -> navController.navigateToProfile(navOptions)
        }
    }
}