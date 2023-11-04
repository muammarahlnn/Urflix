package com.muammarahlnn.urflix.core.designsystem.component

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable


/**
 * @author Muammar Ahlan Abimanyu (muammarahlnn)
 * @file TopAppBar, 04/11/2023 15.32 by Muammar Ahlan Abimanyu
 */

object UrflixTopAppBarDefaults {

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun topAppBarColors() = TopAppBarDefaults.topAppBarColors(
        containerColor = MaterialTheme.colorScheme.background,
        scrolledContainerColor = MaterialTheme.colorScheme.background,
        navigationIconContentColor = MaterialTheme.colorScheme.onBackground,
        actionIconContentColor = MaterialTheme.colorScheme.onBackground,
    )
}