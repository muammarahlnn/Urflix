package com.muammarahlnn.feature.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.muammarahlnn.urflix.core.designsystem.component.CircularLoading
import com.muammarahlnn.urflix.core.designsystem.component.FilmItemCard
import com.muammarahlnn.urflix.core.designsystem.icon.UrflixIcons
import com.muammarahlnn.urflix.core.model.data.constant.MoviesSection
import com.muammarahlnn.urflix.core.model.data.constant.TvShowsSection
import com.muammarahlnn.urflix.core.model.ui.FilmType
import com.muammarahlnn.urflix.feature.home.R


/**
 * @author Muammar Ahlan Abimanyu (muammarahlnn)
 * @file HomeScreen, 02/11/2023 09.30 by Muammar Ahlan Abimanyu
 */
@Composable
internal fun HomeRoute(
    onFilmClick: (Int, Int) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
) {
    val nowPlayingMoviesUiState by viewModel.nowPlayingMoviesUiState.collectAsStateWithLifecycle()
    val upcomingMoviesUiState by viewModel.upcomingMoviesUiState.collectAsStateWithLifecycle()
    val popularMoviesUiState by viewModel.popularMoviesUiState.collectAsStateWithLifecycle()
    val topRatedMoviesUiState by viewModel.topRatedMoviesUiState.collectAsStateWithLifecycle()
    val moviesSectionsUiData = listOf(
        MoviesSectionUiData(
            moviesSection = MoviesSection.NOW_PLAYING,
            uiState = nowPlayingMoviesUiState,
        ),
        MoviesSectionUiData(
            moviesSection = MoviesSection.UPCOMING,
            uiState = upcomingMoviesUiState,
        ),
        MoviesSectionUiData(
            moviesSection = MoviesSection.POPULAR,
            uiState = popularMoviesUiState,
        ),
        MoviesSectionUiData(
            moviesSection = MoviesSection.TOP_RATED,
            uiState = topRatedMoviesUiState,
        ),
    )

    val airingTodayTvShowsUiState by viewModel.airingTodayTvShowsUiState.collectAsStateWithLifecycle()
    val onTheAirTvShowsUiState by viewModel.onTheAirTvShowsUiState.collectAsStateWithLifecycle()
    val popularTvShowsUiState by viewModel.popularTvShowsUiState.collectAsStateWithLifecycle()
    val topRatedTvShowsUiState by viewModel.topRatedTvShowsUiState.collectAsStateWithLifecycle()
    val tvShowsSectionsUiData = listOf(
        TvShowsSectionUiData(
            moviesSection = TvShowsSection.AIRING_TODAY,
            uiState = airingTodayTvShowsUiState,
        ),
        TvShowsSectionUiData(
            moviesSection = TvShowsSection.ON_THE_AIR,
            uiState = onTheAirTvShowsUiState,
        ),
        TvShowsSectionUiData(
            moviesSection = TvShowsSection.POPULAR,
            uiState = popularTvShowsUiState,
        ),
        TvShowsSectionUiData(
            moviesSection = TvShowsSection.TOP_RATED,
            uiState = topRatedTvShowsUiState,
        ),
    )
    HomeScreen(
        moviesSectionsUiData = moviesSectionsUiData,
        tvShowsSectionsUiData = tvShowsSectionsUiData,
        onFilmClick = onFilmClick,
        modifier = modifier,
    )
}

@Composable
private fun HomeScreen(
    moviesSectionsUiData: List<MoviesSectionUiData>,
    tvShowsSectionsUiData: List<TvShowsSectionUiData>,
    onFilmClick: (Int, Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier.fillMaxSize()
    ) {
        item {
            Spacer(modifier = Modifier.height(16.dp))
        }

        moviesSectionsUiData.forEach { moviesSectionUiData ->
            item {
                FilmsSectionHeader(
                    section = moviesSectionUiData.moviesSection.displayedText,
                    filmType = FilmType.MOVIES,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
            }
            item {
                FilmsSectionHorizontalList(
                    uiState = moviesSectionUiData.uiState,
                    onFilmClick = onFilmClick,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp)
                )
            }
        }

        tvShowsSectionsUiData.forEach { tvShowsSectionUiData ->
            item {
                FilmsSectionHeader(
                    section = tvShowsSectionUiData.moviesSection.displayedText,
                    filmType = FilmType.TV_SHOWS,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
            }
            item {
                FilmsSectionHorizontalList(
                    uiState = tvShowsSectionUiData.uiState,
                    onFilmClick = onFilmClick,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp)
                )
            }
        }
    }
}

@Composable
private fun FilmsSectionHorizontalList(
    uiState: FilmsSectionUiState,
    onFilmClick: (Int, Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    when (uiState) {
        FilmsSectionUiState.Loading -> {
            CircularLoading(
                modifier = modifier
                    .fillMaxWidth()
                    .height(filmItemHeight)
            )
        }

        is FilmsSectionUiState.Success -> {
            LazyRow(
                contentPadding = PaddingValues(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = modifier.fillMaxWidth()
            ) {
                items(
                    items = uiState.films,
                    key = { it.id },
                ) { film ->
                    FilmItemCard(
                        width = filmItemWidth,
                        height = filmItemHeight,
                        film = film,
                        onFilmClick = onFilmClick,
                    )
                }
            }
        }

        is FilmsSectionUiState.Error -> TODO()
    }
}

@Composable
private fun FilmsSectionHeader(
    section: String,
    filmType: FilmType,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Text(
            text = section,
            color = MaterialTheme.colorScheme.onBackground,
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.align(Alignment.Bottom)
        )

        Spacer(Modifier.width(4.dp))

        Text(
            text = filmType.value,
            color = MaterialTheme.colorScheme.secondary,
            style = MaterialTheme.typography.titleSmall,
            modifier = Modifier.align(Alignment.Bottom)
        )

        Spacer(Modifier.weight(1f))

        Icon(
            imageVector = UrflixIcons.ArrowForward,
            contentDescription = stringResource(id = R.string.see_more),
            tint = MaterialTheme.colorScheme.onBackground,
        )
    }
}

private val filmItemHeight = 200.dp
private val filmItemWidth = 120.dp

data class MoviesSectionUiData(
    val moviesSection: MoviesSection,
    val uiState: FilmsSectionUiState,
)

data class TvShowsSectionUiData(
    val moviesSection: TvShowsSection,
    val uiState: FilmsSectionUiState,
)