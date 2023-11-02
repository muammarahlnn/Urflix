package com.muammarahlnn.feature.search.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.muammarahlnn.feature.search.SearchRoute


/**
 * @author Muammar Ahlan Abimanyu (muammarahlnn)
 * @file SearchNavigation, 02/11/2023 09.36 by Muammar Ahlan Abimanyu
 */
const val SEARCH_ROUTE = "search_route"

fun NavController.navigateToSearch(navOptions: NavOptions? = null) {
    this.navigate(SEARCH_ROUTE, navOptions)
}

fun NavGraphBuilder.searchScreen() {
    composable(route = SEARCH_ROUTE) {
        SearchRoute()
    }
}