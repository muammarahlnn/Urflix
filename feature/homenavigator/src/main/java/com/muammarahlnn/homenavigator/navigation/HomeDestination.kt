package com.muammarahlnn.homenavigator.navigation

import androidx.compose.ui.graphics.vector.ImageVector
import com.muammarahlnn.feature.bookmarks.navigation.BOOKMARKS_ROUTE
import com.muammarahlnn.feature.home.navigation.HOME_ROUTE
import com.muammarahlnn.feature.profile.navigation.PROFILE_ROUTE
import com.muammarahlnn.feature.search.navigation.SEARCH_ROUTE
import com.muammarahlnn.urflix.core.designsystem.icon.UrflixIcons
import com.muammarahlnn.urflix.feature.home.R as homeR
import com.muammarahlnn.urflix.feature.search.R as searchR
import com.muammarahlnn.urflix.feature.bookmarks.R as bookmarksR
import com.muammarahlnn.urflix.feature.profile.R as profileR


/**
 * @author Muammar Ahlan Abimanyu (muammarahlnn)
 * @file TopLevelDestination, 02/11/2023 09.45 by Muammar Ahlan Abimanyu
 */
enum class HomeDestination(
    val route: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val iconTextId: Int,
    val titleTextId: Int,
) {
    HOME(
        route = HOME_ROUTE,
        selectedIcon = UrflixIcons.Home,
        unselectedIcon = UrflixIcons.HomeBorder,
        iconTextId = homeR.string.home,
        titleTextId = homeR.string.urflix,
    ),
    SEARCH(
        route = SEARCH_ROUTE,
        selectedIcon = UrflixIcons.Search,
        unselectedIcon = UrflixIcons.SearchBorder,
        iconTextId = searchR.string.search,
        titleTextId = searchR.string.search,
    ),
    BOOKMARKS(
        route = BOOKMARKS_ROUTE,
        selectedIcon = UrflixIcons.Bookmarks,
        unselectedIcon = UrflixIcons.BookmarksBorder,
        iconTextId = bookmarksR.string.saved,
        titleTextId = bookmarksR.string.saved,
    ),
    PROFILE(
        route = PROFILE_ROUTE,
        selectedIcon = UrflixIcons.Person,
        unselectedIcon = UrflixIcons.PersonBorder,
        iconTextId = profileR.string.profile,
        titleTextId = profileR.string.profile,
    ),
}