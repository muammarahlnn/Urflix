package com.muammarahlnn.urflix.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.muammarahlnn.homenavigator.navigation.HOME_NAVIGATOR_ROUTE
import com.muammarahlnn.homenavigator.navigation.homeNavigator
import com.muammarahlnn.urflix.feature.camera.navigation.cameraScreen
import com.muammarahlnn.urflix.feature.camera.navigation.navigateToCamera
import com.muammarahlnn.urflix.feature.filmdetails.navigation.filmsDetailsScreen
import com.muammarahlnn.urflix.feature.filmdetails.navigation.navigateToFilmDetails
import com.muammarahlnn.urflix.feature.filmssection.navigation.filmsGenreScreen
import com.muammarahlnn.urflix.feature.filmssection.navigation.filmsSectionScreen
import com.muammarahlnn.urflix.feature.filmssection.navigation.navigateToFilmsGenre
import com.muammarahlnn.urflix.feature.filmssection.navigation.navigateToFilmsSection
import com.muammarahlnn.urflix.feature.genres.navigation.genresScreen
import com.muammarahlnn.urflix.feature.genres.navigation.navigateToGenres


/**
 * @author Muammar Ahlan Abimanyu (muammarahlnn)
 * @file UrflixNavHost, 02/11/2023 10.30 by Muammar Ahlan Abimanyu
 */
@Composable
internal fun UrflixNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = HOME_NAVIGATOR_ROUTE,
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        enterTransition = {
            slideIntoContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Start,
                animationSpec = tween(600)
            )
        },
        exitTransition = {
            slideOutOfContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Start,
                animationSpec = tween(600)
            )
        },
        popEnterTransition = {
            slideIntoContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.End,
                animationSpec = tween(600)
            )
        },
        popExitTransition = {
            slideOutOfContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.End,
                animationSpec = tween(600)
            )
        },
        modifier = modifier,
    ) {
        homeNavigator(
            onFilmClick = navController::navigateToFilmDetails,
            onSeeAllFilmClick = navController::navigateToFilmsSection,
            onSeeAllGenresClick = navController::navigateToGenres,
            onGenreItemClick = navController::navigateToFilmsGenre,
            onCameraActionClick = navController::navigateToCamera
        )
        filmsDetailsScreen(
            onBackClick = navController::popBackStack,
            onGenreItemClick = navController::navigateToFilmsGenre,
        )
        filmsSectionScreen(
            onBackClick = navController::popBackStack,
            onFilmClick = navController::navigateToFilmDetails
        )
        filmsGenreScreen(
            onBackClick = navController::popBackStack,
            onFilmClick = navController::navigateToFilmDetails
        )
        genresScreen(
            onBackClick = navController::popBackStack,
            onGenreItemClick = navController::navigateToFilmsGenre,
        )
        cameraScreen(
            onCameraClosed = navController::navigateUp
        )
    }
}