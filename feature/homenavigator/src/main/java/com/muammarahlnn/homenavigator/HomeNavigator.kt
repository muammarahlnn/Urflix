package com.muammarahlnn.homenavigator

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import com.muammarahlnn.homenavigator.navigation.HomeDestination
import com.muammarahlnn.homenavigator.navigation.HomeNavHost
import com.muammarahlnn.urflix.core.designsystem.component.UrflixNavigationBar
import com.muammarahlnn.urflix.core.designsystem.component.UrflixNavigationBarItem


/**
 * @author Muammar Ahlan Abimanyu (muammarahlnn)
 * @file HomeNavigator, 02/11/2023 09.39 by Muammar Ahlan Abimanyu
 */
@Composable
internal fun HomeNavigatorRoute(
    modifier: Modifier = Modifier,
) {
    HomeNavigator(
        modifier = modifier,
    )
}

@Composable
private fun HomeNavigator(
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
                HomeNavigatorTopAppBar(
                    titleId = destination.titleTextId
                )
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
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeNavigatorTopAppBar(
    titleId: Int,
    modifier: Modifier = Modifier,
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = stringResource(id = titleId),
                style = MaterialTheme.typography.titleSmall.copy(
                    fontWeight = FontWeight.SemiBold
                ),
            )
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
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