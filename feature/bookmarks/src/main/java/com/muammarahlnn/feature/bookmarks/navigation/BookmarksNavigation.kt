package com.muammarahlnn.feature.bookmarks.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.muammarahlnn.feature.bookmarks.BookmarksRoute


/**
 * @author Muammar Ahlan Abimanyu (muammarahlnn)
 * @file BookmarksNavigation, 02/11/2023 09.37 by Muammar Ahlan Abimanyu
 */
const val BOOKMARKS_ROUTE = "bookmarks_route"

fun NavController.navigateToBookmarks(navOptions: NavOptions? = null) {
    this.navigate(BOOKMARKS_ROUTE, navOptions)
}

fun NavGraphBuilder.bookmarksScreen() {
    composable(route = BOOKMARKS_ROUTE) {
        BookmarksRoute()
    }
}