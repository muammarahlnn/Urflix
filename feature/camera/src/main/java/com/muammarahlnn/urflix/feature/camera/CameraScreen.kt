package com.muammarahlnn.urflix.feature.camera

import android.Manifest
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Matrix
import android.util.Log
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.ImageProxy
import androidx.camera.view.CameraController
import androidx.camera.view.LifecycleCameraController
import androidx.camera.view.PreviewView
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionStatus
import com.google.accompanist.permissions.rememberPermissionState
import com.muammarahlnn.urflix.core.designsystem.icon.UrflixIcons
import com.muammarahlnn.urflix.core.designsystem.util.saveTempPhotoFile
import java.util.concurrent.Executor


/**
 * @author Muammar Ahlan Abimanyu (muammarahlnn)
 * @file CameraScreen, 02/11/2023 21.08 by Muammar Ahlan Abimanyu
 */
@Composable
internal fun CameraRoute(
    onCameraClosed: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: CameraViewModel = hiltViewModel(),
) {
    val photoTaken by viewModel.bitmap.collectAsStateWithLifecycle()
    CameraScreen(
        photoTaken,
        onCameraPermissionDenied = onCameraClosed,
        onCameraClosed = onCameraClosed,
        onPhotoTaken = viewModel::takePhoto,
        onRetakeClick = viewModel::resetPhoto,
        modifier = modifier,
    )
}

@OptIn(ExperimentalPermissionsApi::class)
@Composable
private fun CameraScreen(
    photoTaken: Bitmap?,
    onCameraPermissionDenied: () -> Unit,
    onCameraClosed: () -> Unit,
    onPhotoTaken: (Bitmap) -> Unit,
    onRetakeClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    var isCameraPermissionGranted by rememberSaveable {
        mutableStateOf(false)
    }
    val cameraPermissionState = rememberPermissionState(
        permission = Manifest.permission.CAMERA,
    ) { isGranted ->
        isCameraPermissionGranted = isGranted
        if (!isGranted) {
            onCameraPermissionDenied()
        }
    }
    isCameraPermissionGranted = when (cameraPermissionState.status) {
        PermissionStatus.Granted -> true
        is PermissionStatus.Denied -> false
    }

    if (isCameraPermissionGranted) {
        CameraContent(
            photoTaken = photoTaken,
            onPhotoTaken = onPhotoTaken,
            onCloseButtonClick = onCameraClosed,
            onRetakeClick = onRetakeClick,
            modifier = modifier,
        )
    } else {
        RequestCameraPermissionContent(
            onAllowedPermission = {
                cameraPermissionState.launchPermissionRequest()
            },
            onCanceledPermission = {
                onCameraPermissionDenied()
            },
        )
    }
}

@Composable
private fun CameraContent(
    photoTaken: Bitmap?,
    onPhotoTaken: (Bitmap) -> Unit,
    onCloseButtonClick: () -> Unit,
    onRetakeClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    if (photoTaken == null) {
        CameraPreview(
            onPhotoTaken = onPhotoTaken,
            onCloseButtonClick,
            modifier = modifier,
        )
    } else {
        PhotoTakenPreview(
            photoTaken = photoTaken,
            onCloseButtonClick = onCloseButtonClick,
            onRetakeClick = onRetakeClick,
            modifier = modifier,
        )
    }
}

@Composable
private fun CameraPreview(
    onPhotoTaken: (Bitmap) -> Unit,
    onCloseButtonClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val cameraController = remember {
        LifecycleCameraController(context).apply {
            setEnabledUseCases(CameraController.IMAGE_CAPTURE)
        }
    }

    var isTorchEnabled by rememberSaveable { mutableStateOf(false) }
    cameraController.enableTorch(isTorchEnabled)

    Column(
        modifier = modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            AndroidView(
                factory = { context ->
                    PreviewView(context).apply {
                        this.controller = cameraController
                        cameraController.bindToLifecycle(lifecycleOwner)
                    }
                },
                modifier = Modifier.fillMaxSize()
            )
            IconButton(
                onClick = onCloseButtonClick,
                modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.TopStart)
            ) {
                Icon(
                    imageVector = UrflixIcons.Clear,
                    contentDescription = stringResource(id = R.string.close_camera),
                    tint = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier.size(32.dp)
                )
            }
            IconButton(
                onClick = {
                    isTorchEnabled = !isTorchEnabled
                },
                modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.TopEnd)
            ) {
                val torchIconModifier = Modifier.size(32.dp)
                val torchIconTint = MaterialTheme.colorScheme.onBackground
                if (!isTorchEnabled) {
                    Icon(
                        imageVector = UrflixIcons.FlashOn,
                        contentDescription = stringResource(id = R.string.flash_on_icon_desc),
                        tint = torchIconTint,
                        modifier = torchIconModifier,
                    )
                } else {
                    Icon(
                        imageVector = UrflixIcons.FlashOff,
                        contentDescription = stringResource(id = R.string.flash_off_icon_desc),
                        tint = torchIconTint,
                        modifier = torchIconModifier,
                    )
                }
            }
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Black)
                .padding(16.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(64.dp)
                    .border(
                        width = 4.dp,
                        color = MaterialTheme.colorScheme.onBackground,
                        shape = CircleShape
                    )
                    .padding(12.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.onBackground)
                    .align(Alignment.Center)
                    .clickable {
                        takePhoto(
                            context = context,
                            controller = cameraController,
                            onPhotoTaken = onPhotoTaken,
                        )
                    }
            )

            IconButton(
                onClick = {
                    cameraController.cameraSelector =
                        if (cameraController.cameraSelector == CameraSelector.DEFAULT_BACK_CAMERA) {
                            CameraSelector.DEFAULT_FRONT_CAMERA
                        } else {
                            CameraSelector.DEFAULT_BACK_CAMERA
                        }
                },
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .padding(end = 16.dp)
            ) {
                Icon(
                    imageVector = UrflixIcons.SwitchCamera,
                    contentDescription = stringResource(id = R.string.switch_camera),
                    tint = MaterialTheme.colorScheme.background
                )
            }
        }
    }
}

private fun takePhoto(
    context: Context,
    controller: LifecycleCameraController,
    onPhotoTaken: (Bitmap) -> Unit,
) {
    val mainExecutor: Executor = ContextCompat.getMainExecutor(context)

    controller.takePicture(
        mainExecutor, object : ImageCapture.OnImageCapturedCallback() {
            override fun onCaptureSuccess(image: ImageProxy) {
                super.onCaptureSuccess(image)
                val matrix = Matrix().apply {
                    postRotate(image.imageInfo.rotationDegrees.toFloat())
                }
                val rotatedBitmap = Bitmap.createBitmap(
                    image.toBitmap(),
                    0,
                    0,
                    image.width,
                    image.height,
                    matrix,
                    true
                )
                onPhotoTaken(rotatedBitmap)
            }

            override fun onError(exception: ImageCaptureException) {
                super.onError(exception)
                Log.e("CameraScreen", "Couldn't take photo: ", exception)
            }
        }
    )
}

@Composable
private fun PhotoTakenPreview(
    photoTaken: Bitmap,
    onCloseButtonClick: () -> Unit,
    onRetakeClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            Image(
                bitmap = photoTaken.asImageBitmap(),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
            IconButton(
                onClick = onCloseButtonClick,
                modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.TopStart)
            ) {
                Icon(
                    imageVector = UrflixIcons.Clear,
                    contentDescription = stringResource(id = R.string.close_camera),
                    tint = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier.size(32.dp)
                )
            }
        }
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Black)
                .padding(16.dp)
        ) {
            IconButton(
                onClick = onRetakeClick,
            ) {
                Icon(
                    imageVector = UrflixIcons.Clear,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier.size(48.dp)
                )
            }

            IconButton(
                onClick = {
                    saveTempPhotoFile(context, photoTaken)
                    onCloseButtonClick()
                },
            ) {
                Icon(
                    imageVector = UrflixIcons.Check,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier.size(48.dp)
                )
            }
        }
    }
}

@Composable
private fun RequestCameraPermissionContent(
    onAllowedPermission: () -> Unit,
    onCanceledPermission: () -> Unit,
    modifier: Modifier = Modifier,
) {
    var showRequestCameraPermissionDialog by rememberSaveable {
        mutableStateOf(true)
    }
    if (showRequestCameraPermissionDialog) {
        RequestCameraPermissionDialog(
            onAllowedPermission = {
                onAllowedPermission()
                showRequestCameraPermissionDialog = false
            },
            onCanceledPermission = {
                onCanceledPermission()
                showRequestCameraPermissionDialog = false
            },
        )
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.onBackground)
    )
}

@Composable
private fun RequestCameraPermissionDialog(
    onAllowedPermission: () -> Unit,
    onCanceledPermission: () -> Unit,
    modifier: Modifier = Modifier,
) {
    AlertDialog(
        onDismissRequest = {
            // do nothing, to prevent user to dismiss the dialog
            // by clicking outside the dialog or pressing the back button
        },
        title = {
            Text(
                text = stringResource(id = R.string.request_camera_permission_dialog_title),
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.SemiBold
                ),
                color = MaterialTheme.colorScheme.onBackground,
            )
        },
        text = {
            Text(
                text = stringResource(id = R.string.request_camera_permission_dialog_content),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onBackground,
            )
        },
        confirmButton = {
            TextButton(
                onClick = onAllowedPermission
            ) {
                Text(
                    text = stringResource(id = R.string.request_camera_permission_dialog_confirm_text),
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.onBackground,
                )
            }
        },
        dismissButton = {
            TextButton(
                onClick = onCanceledPermission
            ) {
                Text(
                    text = stringResource(id = R.string.request_camera_permission_dialog_dismiss_text),
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.onBackground,
                )
            }
        },
        containerColor = MaterialTheme.colorScheme.background,
        modifier = modifier,
    )
}