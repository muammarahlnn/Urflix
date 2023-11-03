package com.muammarahlnn.urflix.core.model.data

import com.muammarahlnn.urflix.core.model.ui.FilmType


/**
 * @author Muammar Ahlan Abimanyu (muammarahlnn)
 * @file FilmModel, 02/11/2023 11.58 by Muammar Ahlan Abimanyu
 */
data class FilmModel(
    val id: Int,
    val title: String,
    val releaseDate: String,
    val voteAverage: Float,
    val posterImage: String,
    val genreIds: List<Int>,
    val filmType: FilmType,
)
