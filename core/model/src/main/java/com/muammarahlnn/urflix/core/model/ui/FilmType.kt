package com.muammarahlnn.urflix.core.model.ui


/**
 * @author Muammar Ahlan Abimanyu (muammarahlnn)
 * @file FilmType, 02/11/2023 12.52 by Muammar Ahlan Abimanyu
 */
enum class FilmType(
    val value: String,
    val displayedText: String,
) {
    MOVIES("MOVIES", "Movies"),
    TV_SHOWS("TV SHOWS", "TV Shows");

    companion object {

        fun getFilmType(filmTypeOrdinal: Int): FilmType =
            when (filmTypeOrdinal) {
                MOVIES.ordinal -> MOVIES
                TV_SHOWS.ordinal -> TV_SHOWS
                else -> throw IllegalStateException("FilmType not found.")
            }
    }
}