package com.muammarahlnn.urflix.core.network.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


/**
 * @author Muammar Ahlan Abimanyu (muammarahlnn)
 * @file MovieDetailResponse, 03/11/2023 18.28 by Muammar Ahlan Abimanyu
 */
@Serializable
data class MovieDetailsResponse(
    val id: Int,
    val title: String,
    @SerialName("release_date") val releaseDate: String? = null,
    @SerialName("vote_average") val voteAverage: Float? = null,
    val runtime: Int? = null,
    val overview: String? = null,
    @SerialName("backdrop_path") val backdropPath: String? = null,
    @SerialName("poster_path") val posterPath: String? = null,
    val genres: List<GenreResponse>? = null,
)