package com.muammarahlnn.urflix.core.designsystem.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.unit.dp


/**
 * @author Muammar Ahlan Abimanyu (muammarahlnn)
 * @file Theme, 02/11/2023 08.35 by Muammar Ahlan Abimanyu
 */
val DarkColorScheme = darkColorScheme(
    primary = Orange50,
    secondary = Navy50,
    background = Navy20,
    onBackground = White50,
    surface = Navy20,
    surfaceVariant = Navy30,
)

@Composable
fun UrflixTheme(content: @Composable () -> Unit) {
    val defaultBackgroundTheme = BackgroundTheme(
        color = DarkColorScheme.background,
        tonalElevation = 0.dp,
    )

    CompositionLocalProvider(
        LocalBackgroundTheme provides defaultBackgroundTheme
    ) {
        MaterialTheme(
            colorScheme = DarkColorScheme,
            typography = UrflixTypography,
            content = content,
        )
    }
}