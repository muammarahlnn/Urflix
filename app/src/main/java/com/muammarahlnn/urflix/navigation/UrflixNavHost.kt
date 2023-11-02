package com.muammarahlnn.urflix.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.muammarahlnn.homenavigator.navigation.HOME_NAVIGATOR_ROUTE
import com.muammarahlnn.homenavigator.navigation.homeNavigator


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
        modifier = modifier,
    ) {
        homeNavigator()
    }
}