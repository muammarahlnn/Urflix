package com.muammarahlnn.urflix.core.data.mapper

import com.muammarahlnn.urflix.core.database.model.FilmEntity
import com.muammarahlnn.urflix.core.model.data.FilmModel
import com.muammarahlnn.urflix.core.model.ui.FilmType


/**
 * @author Muammar Ahlan Abimanyu (muammarahlnn)
 * @file BookmarksMapper, 03/11/2023 22.14 by Muammar Ahlan Abimanyu
 */

fun List<FilmEntity>.toFilmModels() = map {
    it.toFilmModel()
}

fun FilmEntity.toFilmModel() = FilmModel(
    id = filmId,
    title = title,
    releaseDate = releaseDate,
    voteAverage = voteAverage,
    posterImage = posterImage,
    genreIds = listOf(),
    filmType = FilmType.getFilmType(filmTypeOrdinal)
)