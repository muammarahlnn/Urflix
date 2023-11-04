package com.muammarahlnn.urflix.feature.filmssection.viewmodel

import androidx.lifecycle.SavedStateHandle
import com.muammarahlnn.urflix.core.data.repository.FilmsSectionRepository
import com.muammarahlnn.urflix.core.model.data.FilmModel
import com.muammarahlnn.urflix.core.model.ui.FilmType
import com.muammarahlnn.urflix.feature.filmssection.navigation.FilmsGenreArgs
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


/**
 * @author Muammar Ahlan Abimanyu (muammarahlnn)
 * @file FilmsGenreViewModel, 04/11/2023 16.45 by Muammar Ahlan Abimanyu
 */
@HiltViewModel
class FilmsGenreViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val filmsSectionRepository: FilmsSectionRepository,
) : FilmsViewModel() {

    private val args = FilmsGenreArgs(savedStateHandle)

    override val filmType: FilmType = FilmType.getFilmType(args.filmTypeOrdinal)

    val title = args.genreName

    init {
        fetchFilms()
    }

    override suspend fun fetchMovies(): Flow<List<FilmModel>> =
        filmsSectionRepository.getMoviesWithGenres(
            genre = "${args.genreId}",
            page = currentPage
        )

    override suspend fun fetchTvShows(): Flow<List<FilmModel>> =
        filmsSectionRepository.getTvShowsWithGenres(
            genre = "${args.genreId}",
            page = currentPage
        )
}