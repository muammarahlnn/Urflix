package com.muammarahlnn.urflix.ui

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.muammarahlnn.urflix.core.designsystem.component.UrflixBackground
import com.muammarahlnn.urflix.navigation.UrflixNavHost


/**
 * @author Muammar Ahlan Abimanyu (muammarahlnn)
 * @file UrflixApp, 02/11/2023 09.17 by Muammar Ahlan Abimanyu
 */
@Composable
internal fun UrflixApp() {
    UrflixBackground {
        val systemUiController = rememberSystemUiController()
        val statusBarColor = MaterialTheme.colorScheme.surfaceVariant
        LaunchedEffect(systemUiController, statusBarColor) {
            systemUiController.setStatusBarColor(
                color = statusBarColor,
            )
        }

        Scaffold(
            containerColor = Color.Transparent,
            contentColor = MaterialTheme.colorScheme.onBackground,
            contentWindowInsets = WindowInsets(0, 0, 0, 0),
        ) { padding ->
            UrflixNavHost(
                modifier = Modifier.padding(padding)
            )
        }
    }
}