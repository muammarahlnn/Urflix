package com.muammarahlnn.feature.search

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier


/**
 * @author Muammar Ahlan Abimanyu (muammarahlnn)
 * @file SearchScreen, 02/11/2023 09.33 by Muammar Ahlan Abimanyu
 */
@Composable
internal fun SearchRoute(
    modifier: Modifier = Modifier,
) {
    SearchScreen(
        modifier = modifier,
    )
}

@Composable
private fun SearchScreen(
    modifier: Modifier = Modifier,
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
    ) {
        Text(
            text = "Search",
            color = MaterialTheme.colorScheme.onBackground,
        )
    }
}