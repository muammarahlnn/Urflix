package com.muammarahlnn.urflix.core.designsystem.component

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


/**
 * @author Muammar Ahlan Abimanyu (muammarahlnn)
 * @file Navigation, 02/11/2023 10.09 by Muammar Ahlan Abimanyu
 */
@Composable
fun UrflixNavigationBar(
    modifier: Modifier = Modifier,
    content: @Composable RowScope.() -> Unit,
) {
    NavigationBar(
        contentColor = UrflixNavigationDefaults.navigationContentColor(),
        tonalElevation = 0.dp,
        containerColor = MaterialTheme.colorScheme.surfaceVariant,
        content = content,
        modifier = modifier
    )
}

@Composable
fun RowScope.UrflixNavigationBarItem(
    selected: Boolean,
    onClick: () -> Unit,
    icon: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    selectedIcon: @Composable () -> Unit = icon,
    enabled: Boolean = true,
    label: @Composable (() -> Unit)? = null,
    alwaysShowLabel: Boolean = true,
) {
    NavigationBarItem(
        selected = selected,
        onClick = onClick,
        icon = if (selected) selectedIcon else icon,
        modifier = modifier,
        enabled = enabled,
        label = label,
        alwaysShowLabel = alwaysShowLabel,
        colors = NavigationBarItemDefaults.colors(
            selectedIconColor = UrflixNavigationDefaults.navigationSelectedItemColor(),
            unselectedIconColor = UrflixNavigationDefaults.navigationContentColor(),
            selectedTextColor = UrflixNavigationDefaults.navigationSelectedItemColor(),
            unselectedTextColor = UrflixNavigationDefaults.navigationContentColor(),
            indicatorColor = UrflixNavigationDefaults.navigationIndicatorColor(),
        ),
    )
}


object UrflixNavigationDefaults {

    @Composable
    fun navigationContentColor() = MaterialTheme.colorScheme.onSurfaceVariant

    @Composable
    fun navigationSelectedItemColor() = MaterialTheme.colorScheme.primary

    @Composable
    fun navigationIndicatorColor() = MaterialTheme.colorScheme.secondary
}