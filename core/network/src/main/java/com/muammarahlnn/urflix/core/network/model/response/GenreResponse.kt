package com.muammarahlnn.urflix.core.network.model.response

import kotlinx.serialization.Serializable


/**
 * @author Muammar Ahlan Abimanyu (muammarahlnn)
 * @file GenreResponse, 03/11/2023 18.28 by Muammar Ahlan Abimanyu
 */

@Serializable
data class GenreResponses(
    val genres: List<GenreResponse>,
)

@Serializable
data class GenreResponse(
    val id: Int,
    val name: String,
)