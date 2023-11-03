package com.muammarahlnn.urflix.core.network.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


/**
 * @author Muammar Ahlan Abimanyu (muammarahlnn)
 * @file ImagesResponse, 03/11/2023 18.43 by Muammar Ahlan Abimanyu
 */
@Serializable
data class ImageResponses(
    val backdrops: List<ImageResponse>? = null,
    val posters: List<ImageResponse>? = null,
)

@Serializable
data class ImageResponse(
    @SerialName("file_path") val filePath: String? = null,
)