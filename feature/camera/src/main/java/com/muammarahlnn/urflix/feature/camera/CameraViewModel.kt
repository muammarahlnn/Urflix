package com.muammarahlnn.urflix.feature.camera

import android.graphics.Bitmap
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow


/**
 * @author Muammar Ahlan Abimanyu (muammarahlnn)
 * @file CameraViewModel, 02/11/2023 21.17 by Muammar Ahlan Abimanyu
 */
class CameraViewModel : ViewModel() {

    private val _bitmap: MutableStateFlow<Bitmap?> = MutableStateFlow(null)
    val bitmap = _bitmap.asStateFlow()

    fun takePhoto(bitmap: Bitmap) {
        _bitmap.value = bitmap
    }

    fun resetPhoto() {
        _bitmap.value = null
    }
}