package com.muammarahlnn.urflix.core.data.mapper

import com.muammarahlnn.urflix.core.database.model.FilmEntity
import com.muammarahlnn.urflix.core.model.data.FilmDetailsModel
import com.muammarahlnn.urflix.core.model.data.GenreModel
import com.muammarahlnn.urflix.core.model.data.ImageModel
import com.muammarahlnn.urflix.core.model.ui.FilmType
import com.muammarahlnn.urflix.core.network.model.response.GenreResponse
import com.muammarahlnn.urflix.core.network.model.response.ImageResponse
import com.muammarahlnn.urflix.core.network.model.response.MovieDetailsResponse
import com.muammarahlnn.urflix.core.network.model.response.TvShowDetailsResponse


/**
 * @author Muammar Ahlan Abimanyu (muammarahlnn)
 * @file FilmDetailMapper, 03/11/2023 19.01 by Muammar Ahlan Abimanyu
 */

@JvmName("MovieDetailsResponseToFilmDetailsModel")
fun MovieDetailsResponse.toFilmDetailsModel(
    backdrops: List<ImageResponse>?
) = FilmDetailsModel(
    id = id,
    title = title,
    releaseDate = releaseDate.orEmpty(),
    duration = runtime ?: 0,
    voteAverage = voteAverage ?: 0f,
    overview = overview.orEmpty(),
    posterImage = posterPath?.toPosterImage().orEmpty(),
    backdrops = backdrops?.toImageModels() ?: listOf(),
    genres = genres?.toGenreModels() ?: listOf(),
    filmType = FilmType.MOVIES,
)

@JvmName("TvShowDetailsResponseToFilmDetailsModel")
fun TvShowDetailsResponse.toFilmDetailsModel(
    backdrops: List<ImageResponse>?
) = FilmDetailsModel(
    id = id,
    title = name,
    releaseDate = firstAirDate.orEmpty(),
    duration = runtime ?: 0,
    voteAverage = voteAverage ?: 0f,
    overview = overview.orEmpty(),
    posterImage = posterPath?.toPosterImage().orEmpty(),
    backdrops = backdrops?.toImageModels() ?: listOf(),
    genres = genres?.toGenreModels() ?: listOf(),
    filmType = FilmType.TV_SHOWS,
)

fun FilmDetailsModel.toFilmEntity() = FilmEntity(
    filmId = id,
    title = title,
    releaseDate = releaseDate,
    voteAverage = voteAverage,
    posterImage = posterImage,
    filmTypeOrdinal = filmType.ordinal,
)

fun List<ImageResponse>.toImageModels() = map {
    it.toImageModel()
}
fun ImageResponse.toImageModel() = ImageModel(
    fileImage = filePath?.toBackdropImage().orEmpty()
)

fun String.toBackdropImage() = "$IMG_URL/w500$this"

fun List<GenreResponse>.toGenreModels() = map {
    it.toGenreModel()
}

fun GenreResponse.toGenreModel() = GenreModel(id, name)