package com.muammarahlnn.urflix.core.network.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


/**
 * @author Muammar Ahlan Abimanyu (muammarahlnn)
 * @file TvShowDetailResponse, 03/11/2023 18.37 by Muammar Ahlan Abimanyu
 */
@Serializable
data class TvShowDetailsResponse(
    val id: Int,
    val name: String,
    @SerialName("first_air_date") val firstAirDate: String? = null,
    @SerialName("vote_average") val voteAverage: Float? = null,
    val overview: String? = null,
    @SerialName("backdrop_path") val backdropPath: String? = null,
    @SerialName("poster_path") val posterPath: String? = null,
    val genres: List<GenreResponse>? = null,
    @SerialName("last_episode_to_air") val lastEpisodeToAir: EpisodeResponse? = null,
)

@Serializable
data class EpisodeResponse(
    val id: Int,
    val name: String? = null,
    val overview: String? = null,
    @SerialName("vote_average") val voteAverage: Float? = null,
    @SerialName("air_date") val airDate: String? = null,
    @SerialName("episode_number") val episodeNumber: Int? = null,
    val runtime: Int? = null,
    @SerialName("season_number") val seasonNumber: Int? = null,
)