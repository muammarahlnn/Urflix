package com.muammarahlnn.homenavigator.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.muammarahlnn.feature.bookmarks.navigation.bookmarksScreen
import com.muammarahlnn.feature.home.navigation.HOME_ROUTE
import com.muammarahlnn.feature.home.navigation.homeScreen
import com.muammarahlnn.feature.profile.navigation.profileScreen
import com.muammarahlnn.feature.search.navigation.searchScreen
import com.muammarahlnn.homenavigator.HomeNavigatorState


/**
 * @author Muammar Ahlan Abimanyu (muammarahlnn)
 * @file HomeNavHost, 02/11/2023 10.25 by Muammar Ahlan Abimanyu
 */
@Composable
internal fun HomeNavHost(
    state: HomeNavigatorState,
    onSeeAllFilmClick: (Int, Int) -> Unit,
    onFilmClick: (Int, Int) -> Unit,
    onSeeAllGenresClick: (Int) -> Unit,
    onGenreItemClick: (Int, String, Int) -> Unit,
    onCameraActionClick: () -> Unit,
    modifier: Modifier = Modifier,
    startDestination: String = HOME_ROUTE,
) {
    val navController = state.navController
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier,
    ) {
        homeScreen(
            onSeeAllFilmClick = onSeeAllFilmClick,
            onFilmClick = onFilmClick,
            onSeeAllGenresClick = onSeeAllGenresClick,
            onGenreItemClick = onGenreItemClick,
        )
        searchScreen(
            onFilmClick = onFilmClick,
        )
        bookmarksScreen(
            onFilmClick = onFilmClick,
        )
        profileScreen(
            onCameraActionClick = onCameraActionClick,
        )
    }
}