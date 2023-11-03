package com.muammarahlnn.urflix.core.data.mapper

import com.muammarahlnn.urflix.core.model.data.FilmModel
import com.muammarahlnn.urflix.core.model.ui.FilmType
import com.muammarahlnn.urflix.core.network.BuildConfig
import com.muammarahlnn.urflix.core.network.model.response.MovieResponse
import com.muammarahlnn.urflix.core.network.model.response.TvShowResponse


/**
 * @author Muammar Ahlan Abimanyu (muammarahlnn)
 * @file HomeMapper, 02/11/2023 12.34 by Muammar Ahlan Abimanyu
 */

@JvmName("MovieResponsesToFilmModels")
fun List<MovieResponse>.toFilmModels() = map {
    it.toFilmModel()
}

@JvmName("MovieResponseToFilmModel")
fun MovieResponse.toFilmModel() = FilmModel(
    id = id,
    title = title.orEmpty(),
    releaseDate = releaseDate.orEmpty(),
    voteAverage = voteAverage ?: 0f,
    posterImage = posterPath?.toPosterImage().orEmpty(),
    genreIds = genreIds ?: listOf(),
    filmType = FilmType.MOVIES,
)

@JvmName("TvShowResponsesToFilmModels")
fun List<TvShowResponse>.toFilmModels() = map {
    it.toFilmModel()
}

@JvmName("TvShowResponseToFilmModel")
fun TvShowResponse.toFilmModel() = FilmModel(
    id = id,
    title = name.orEmpty(),
    releaseDate = firstAirDate.orEmpty(),
    voteAverage = voteAverage ?: 0f,
    posterImage = posterPath?.toPosterImage().orEmpty(),
    genreIds = genreIds ?: listOf(),
    filmType = FilmType.TV_SHOWS,
)

fun String.toPosterImage() = "$IMG_URL/w342$this"

internal const val IMG_URL = BuildConfig.IMG_URL