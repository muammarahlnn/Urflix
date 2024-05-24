package com.muammarahlnn.urflix.core.data.mapper

import com.muammarahlnn.urflix.core.model.data.FilmModel
import com.muammarahlnn.urflix.core.model.data.PersonModel
import com.muammarahlnn.urflix.core.model.ui.FilmType
import com.muammarahlnn.urflix.core.network.BuildConfig
import com.muammarahlnn.urflix.core.network.model.response.GenreResponse
import com.muammarahlnn.urflix.core.network.model.response.MovieResponse
import com.muammarahlnn.urflix.core.network.model.response.PersonResponse
import com.muammarahlnn.urflix.core.network.model.response.TvShowResponse


/**
 * @author Muammar Ahlan Abimanyu (muammarahlnn)
 * @file HomeMapper, 02/11/2023 12.34 by Muammar Ahlan Abimanyu
 */

@JvmName("MovieResponsesToFilmModels")
fun List<MovieResponse>.toFilmModels(
    movieGenres: List<GenreResponse> = listOf(),
) = map {
    it.toFilmModel(movieGenres)
}

@JvmName("MovieResponseToFilmModel")
fun MovieResponse.toFilmModel(
    movieGenres: List<GenreResponse> = listOf(),
) = FilmModel(
    id = id,
    title = title.orEmpty(),
    releaseDate = releaseDate.orEmpty(),
    voteAverage = voteAverage ?: 0f,
    posterImage = posterPath?.toPosterImage().orEmpty(),
    backdropImage = backdropPath?.toBackdropImage().orEmpty(),
    genreNames = genreIds?.toGenreNames(movieGenres) ?: listOf(),
    filmType = FilmType.MOVIES,
)

@JvmName("TvShowResponsesToFilmModels")
fun List<TvShowResponse>.toFilmModels(
    tvShowGenres: List<GenreResponse> = listOf()
) = map {
    it.toFilmModel(tvShowGenres)
}

@JvmName("TvShowResponseToFilmModel")
fun TvShowResponse.toFilmModel(
    tvShowGenres: List<GenreResponse> = listOf()
) = FilmModel(
    id = id,
    title = name.orEmpty(),
    releaseDate = firstAirDate.orEmpty(),
    voteAverage = voteAverage ?: 0f,
    posterImage = posterPath?.toPosterImage().orEmpty(),
    backdropImage = backdropPath?.toBackdropImage().orEmpty(),
    genreNames = genreIds?.toGenreNames(tvShowGenres) ?: listOf(),
    filmType = FilmType.TV_SHOWS,
)

fun List<Int>.toGenreNames(genres: List<GenreResponse>): List<String> =
    genres.filter {
        it.id in this
    }.map {
        it.name
    }

fun List<PersonResponse>.toPersonModels() = map {
    it.toPersonModel()
}

fun PersonResponse.toPersonModel() = PersonModel(
    id = id,
    name = name.orEmpty(),
    profileImage = profilePath?.toPosterImage().orEmpty(),
)

fun String.toPosterImage() = "$IMG_URL/w342$this"

internal const val IMG_URL = BuildConfig.IMG_URL