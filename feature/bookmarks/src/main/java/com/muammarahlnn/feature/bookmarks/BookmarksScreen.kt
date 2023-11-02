package com.muammarahlnn.feature.bookmarks

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier


/**
 * @author Muammar Ahlan Abimanyu (muammarahlnn)
 * @file BookmarksScreen, 02/11/2023 09.33 by Muammar Ahlan Abimanyu
 */
@Composable
internal fun BookmarksRoute(
    modifier: Modifier = Modifier,
) {
    BookmarksScreen(
        modifier = modifier,
    )
}

@Composable
private fun BookmarksScreen(
    modifier: Modifier = Modifier,
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
    ) {
        Text(
            text = "Bookmarks",
            color = MaterialTheme.colorScheme.onBackground,
        )
    }
}