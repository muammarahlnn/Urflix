package com.muammarahlnn.urflix.core.model.data.constant


/**
 * @author Muammar Ahlan Abimanyu (muammarahlnn)
 * @file MoviesSection, 02/11/2023 11.59 by Muammar Ahlan Abimanyu
 */
enum class MoviesSection(val displayedText: String) {
    NOW_PLAYING("Now Playing"),
    UPCOMING("Upcoming"),
    POPULAR("Popular"),
    TOP_RATED("Top Rated");

    companion object {

        fun getMoviesSection(moviesSectionOrdinal: Int): MoviesSection =
            when(moviesSectionOrdinal) {
                NOW_PLAYING.ordinal -> NOW_PLAYING
                UPCOMING.ordinal -> UPCOMING
                POPULAR.ordinal -> POPULAR
                TOP_RATED.ordinal -> TOP_RATED
                else -> throw IllegalStateException("MoviesSection not found.")
            }
    }
}
