package com.muammarahlnn.urflix.core.designsystem.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import coil.compose.AsyncImage
import com.muammarahlnn.urflix.core.designsystem.R


/**
 * @author Muammar Ahlan Abimanyu (muammarahlnn)
 * @file Image, 04/11/2023 04.36 by Muammar Ahlan Abimanyu
 */
@Composable
fun BaseAsyncImage(
    model: Any,
    modifier: Modifier = Modifier,
) {
    AsyncImage(
        contentScale = ContentScale.Crop,
        model = model,
        contentDescription = null,
        error = painterResource(id = R.drawable.error_image_placeholder),
        modifier = modifier,
    )
}