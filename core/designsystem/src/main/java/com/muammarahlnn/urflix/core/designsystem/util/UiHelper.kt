package com.muammarahlnn.urflix.core.designsystem.util

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed

fun Modifier.noRippleClickable(action: () -> Unit) = composed {
    this.clickable(
        interactionSource = remember {
            MutableInteractionSource()
        },
        indication = null,
    ) {
        action()
    }
}