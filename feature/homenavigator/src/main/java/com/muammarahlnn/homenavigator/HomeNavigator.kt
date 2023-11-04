package com.muammarahlnn.homenavigator

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import com.muammarahlnn.homenavigator.navigation.HomeDestination
import com.muammarahlnn.homenavigator.navigation.HomeNavHost
import com.muammarahlnn.urflix.core.designsystem.component.UrflixNavigationBar
import com.muammarahlnn.urflix.core.designsystem.component.UrflixNavigationBarItem
import com.muammarahlnn.urflix.feature.homenavigator.R


/**
 * @author Muammar Ahlan Abimanyu (muammarahlnn)
 * @file HomeNavigator, 02/11/2023 09.39 by Muammar Ahlan Abimanyu
 */
@Composable
internal fun HomeNavigatorRoute(
    onSeeAllFilmClick: (Int, Int) -> Unit,
    onFilmClick: (Int, Int) -> Unit,
    onSeeAllGenresClick: (Int) -> Unit,
    onGenreItemClick: (Int, String, Int) -> Unit,
    onCameraActionClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    HomeNavigator(
        onFilmClick = onFilmClick,
        onSeeAllFilmClick = onSeeAllFilmClick,
        onSeeAllGenresClick = onSeeAllGenresClick,
        onCameraActionClick = onCameraActionClick,
        onGenreItemClick = onGenreItemClick,
        modifier = modifier,
    )
}

@Composable
private fun HomeNavigator(
    onSeeAllFilmClick: (Int, Int) -> Unit,
    onFilmClick: (Int, Int) -> Unit,
    onSeeAllGenresClick: (Int) -> Unit,
    onGenreItemClick: (Int, String, Int) -> Unit,
    onCameraActionClick: () -> Unit,
    modifier: Modifier = Modifier,
    state: HomeNavigatorState = rememberHomeNavigatorState(),
) {
    Scaffold(
        containerColor = Color.Transparent,
        contentColor = MaterialTheme.colorScheme.onBackground,
        contentWindowInsets = WindowInsets(0, 0, 0, 0),
        topBar = {
            val destination = state.currentHomeDestination
            if (destination != null) {
                when (destination) {
                    HomeDestination.HOME -> UrflixLogoTopAppBar()
                    else -> HomeNavigatorTopAppBar(
                        titleId = destination.titleTextId
                    )
                }
            }
        },
        bottomBar = {
            HomeNavigatorBottomBar(
                destinations = state.homeDestinations,
                onNavigateToDestination = state::navigateToHomeDestination,
                currentDestination = state.currentDestination
            )
        },
        modifier = modifier,
    ) { padding ->
        HomeNavHost(
            state = state,
            onFilmClick = onFilmClick,
            onSeeAllFilmClick = onSeeAllFilmClick,
            onSeeAllGenresClick = onSeeAllGenresClick,
            onGenreItemClick = onGenreItemClick,
            onCameraActionClick = onCameraActionClick,
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun UrflixLogoTopAppBar() {
    CenterAlignedTopAppBar(
        title = {
            Image(
                painter = painterResource(id = R.drawable.urflix_logo),
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = Modifier.height(42.dp)
            )
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.background,
        ),
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun HomeNavigatorTopAppBar(
    titleId: Int,
    modifier: Modifier = Modifier,
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = stringResource(id = titleId),
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.SemiBold
                ),
            )
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.background,
            titleContentColor = MaterialTheme.colorScheme.onBackground,
        ),
        modifier = modifier,
    )
}

@Composable
private fun HomeNavigatorBottomBar(
    destinations: List<HomeDestination>,
    onNavigateToDestination: (HomeDestination) -> Unit,
    currentDestination: NavDestination?,
    modifier: Modifier = Modifier,
) {
    UrflixNavigationBar(
        modifier = modifier,
    ) {
        destinations.forEach { destination ->
            val selected = currentDestination.isHomeDestinationInHierarchy(destination)
            UrflixNavigationBarItem(
                selected = selected,
                onClick = { onNavigateToDestination(destination) },
                icon = {
                    Icon(
                        imageVector = destination.unselectedIcon,
                        contentDescription = null,
                    )
                },
                selectedIcon = {
                    Icon(
                        imageVector = destination.selectedIcon,
                        contentDescription = null,
                    )
                },
                label = {
                    Text(stringResource(destination.iconTextId))
                },
            )
        }
    }
}

@Composable
private fun NavDestination?.isHomeDestinationInHierarchy(destination: HomeDestination): Boolean =
    this?.hierarchy?.any {
        it.route?.contains(destination.name, true) ?: false
    } ?: false