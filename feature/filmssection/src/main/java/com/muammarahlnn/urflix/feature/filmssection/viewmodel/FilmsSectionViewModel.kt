package com.muammarahlnn.urflix.feature.filmssection.viewmodel

import androidx.lifecycle.SavedStateHandle
import com.muammarahlnn.urflix.core.data.repository.FilmsSectionRepository
import com.muammarahlnn.urflix.core.model.data.FilmModel
import com.muammarahlnn.urflix.core.model.data.constant.MoviesSection
import com.muammarahlnn.urflix.core.model.data.constant.TvShowsSection
import com.muammarahlnn.urflix.core.model.ui.FilmType
import com.muammarahlnn.urflix.feature.filmssection.navigation.FilmsSectionArgs
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


/**
 * @author Muammar Ahlan Abimanyu (muammarahlnn)
 * @file FilmsSectionViewModel, 04/11/2023 16.19 by Muammar Ahlan Abimanyu
 */
@HiltViewModel
class FilmsSectionViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val filmsSectionRepository: FilmsSectionRepository
) : FilmsViewModel() {

    private val args = FilmsSectionArgs(savedStateHandle)

    override val filmType: FilmType = FilmType.getFilmType(args.filmTypeOrdinal)

    val title: String
        get() = when (filmType) {
            FilmType.MOVIES ->
                MoviesSection.getMoviesSection(args.filmSectionOrdinal).displayedText
            FilmType.TV_SHOWS ->
                TvShowsSection.getTvShowsSection(args.filmSectionOrdinal).displayedText
        }

    init {
        fetchFilms()
    }

    override suspend fun fetchMovies(): Flow<List<FilmModel>> =
        filmsSectionRepository.getMovies(
            section = MoviesSection.getMoviesSection(args.filmSectionOrdinal),
            page = currentPage
        )

    override suspend fun fetchTvShows(): Flow<List<FilmModel>> =
        filmsSectionRepository.getTvShows(
            section = TvShowsSection.getTvShowsSection(args.filmSectionOrdinal),
            page = currentPage
        )

}