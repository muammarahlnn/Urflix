package com.muammarahlnn.urflix.core.model.data.constant


/**
 * @author Muammar Ahlan Abimanyu (muammarahlnn)
 * @file MoviesSection, 02/11/2023 11.59 by Muammar Ahlan Abimanyu
 */
enum class TvShowsSection(val displayedText: String) {
    AIRING_TODAY("Airing Today"),
    ON_THE_AIR("On The Air"),
    POPULAR("Popular"),
    TOP_RATED("Top Rated");

    companion object {

        fun getTvShowsSection(tvShowsSectionOrdinal: Int): TvShowsSection =
            when(tvShowsSectionOrdinal) {
                AIRING_TODAY.ordinal -> AIRING_TODAY
                ON_THE_AIR.ordinal -> ON_THE_AIR
                POPULAR.ordinal -> POPULAR
                TOP_RATED.ordinal -> TOP_RATED
                else -> throw IllegalStateException("TvShowsSection not found.")
            }
    }
}
