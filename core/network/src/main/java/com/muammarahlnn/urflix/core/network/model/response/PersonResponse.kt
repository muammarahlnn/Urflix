package com.muammarahlnn.urflix.core.network.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * @Author Muammar Ahlan Abimanyu
 * @File PersonResponse, 24/05/2024 18.52
 */
@Serializable
data class PersonResponse(
    val id: Int,
    val name: String?,
    @SerialName("profile_path") val profilePath: String? = null,
)