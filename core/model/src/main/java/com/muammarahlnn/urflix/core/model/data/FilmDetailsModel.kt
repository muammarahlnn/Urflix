package com.muammarahlnn.urflix.core.model.data


/**
 * @author Muammar Ahlan Abimanyu (muammarahlnn)
 * @file FilmDetailModel, 03/11/2023 18.57 by Muammar Ahlan Abimanyu
 */
data class FilmDetailsModel(
    val id: Int,
    val title: String,
    val releaseDate: String,
    val duration: Int,
    val voteAverage: Float,
    val overview: String,
    val posterImage: String,
    val backdrops: List<ImageModel>,
    val genres: List<GenreModel>,
)

data class ImageModel(
    val fileImage: String,
)