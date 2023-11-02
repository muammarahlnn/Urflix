package com.muammarahlnn.feature.profile

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier


/**
 * @author Muammar Ahlan Abimanyu (muammarahlnn)
 * @file ProfileScreen, 02/11/2023 09.33 by Muammar Ahlan Abimanyu
 */
@Composable
internal fun ProfileRoute(
    modifier: Modifier = Modifier,
) {
    ProfileScreen(
        modifier = modifier,
    )
}

@Composable
private fun ProfileScreen(
    modifier: Modifier = Modifier,
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
    ) {
        Text(
            text = "Profile",
            color = MaterialTheme.colorScheme.onBackground,
        )
    }
}