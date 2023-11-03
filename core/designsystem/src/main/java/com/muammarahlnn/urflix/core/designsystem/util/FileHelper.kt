package com.muammarahlnn.urflix.core.designsystem.util

import android.content.Context
import android.graphics.Bitmap
import java.io.File
import java.io.FileOutputStream


/**
 * @author Muammar Ahlan Abimanyu (muammarahlnn)
 * @file FileHelper, 02/11/2023 21.19 by Muammar Ahlan Abimanyu
 */

fun saveTempPhotoFile(
    context: Context,
    photo: Bitmap,
) {
    val path = context.getExternalFilesDir(null)!!.absolutePath
    val tempFile = File(path, TEMP_PHOTO_FILE_NAME)
    val fileOut = FileOutputStream(tempFile)

    photo.compress(Bitmap.CompressFormat.JPEG, 100, fileOut)
    fileOut.close()
}

fun isPhotoExists(context: Context): Boolean {
    val imagePath = getImagePath(context)
    return File(imagePath).exists()
}

fun getFilePhoto(context: Context): File {
    val imagePath = getImagePath(context)
    return File(imagePath)
}

fun deletePhoto(context: Context) {
    val imagePath = getImagePath(context)
    val photoFile = File(imagePath)
    if (photoFile.exists()) {
        photoFile.delete()
    }
}

private fun getImagePath(context: Context): String {
    val path = context.getExternalFilesDir(null)!!.absolutePath
    return "$path/$TEMP_PHOTO_FILE_NAME"
}

private const val TEMP_PHOTO_FILE_NAME = "temp_photo.jpg"