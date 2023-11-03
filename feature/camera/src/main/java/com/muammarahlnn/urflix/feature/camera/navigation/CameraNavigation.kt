package com.muammarahlnn.urflix.feature.camera.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.muammarahlnn.urflix.feature.camera.CameraRoute


/**
 * @author Muammar Ahlan Abimanyu (muammarahlnn)
 * @file CameraNavigation, 02/11/2023 21.25 by Muammar Ahlan Abimanyu
 */
private const val CAMERA_ROUTE = "camera_route"

fun NavController.navigateToCamera() {
    this.navigate(CAMERA_ROUTE)
}

fun NavGraphBuilder.cameraScreen(
    onCameraClosed: () -> Unit,
) {
    composable(route = CAMERA_ROUTE) {
        CameraRoute(
            onCameraClosed = onCameraClosed,
        )
    }
}