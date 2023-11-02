package com.muammarahlnn.urflix.core.network.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


/**
 * @author Muammar Ahlan Abimanyu (muammarahlnn)
 * @file MovieResponse, 02/11/2023 11.44 by Muammar Ahlan Abimanyu
 */
@Serializable
data class MovieResponse(
    val id: Int,
    val title: String? = null,
    @SerialName("release_date") val releaseDate: String? = null,
    @SerialName("vote_average") val voteAverage: Float? = null,
    @SerialName("backdrop_path") val backdropPath: String? = null,
    @SerialName("poster_path") val posterPath: String? = null,
    @SerialName("genre_ids") val genreIds: List<Int>? = null,
)