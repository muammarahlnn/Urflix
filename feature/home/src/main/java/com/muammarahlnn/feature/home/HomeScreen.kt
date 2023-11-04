package com.muammarahlnn.feature.home

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.muammarahlnn.urflix.core.designsystem.component.CircularLoading
import com.muammarahlnn.urflix.core.designsystem.component.FilmItemCard
import com.muammarahlnn.urflix.core.designsystem.component.WrappedTabRow
import com.muammarahlnn.urflix.core.designsystem.icon.UrflixIcons
import com.muammarahlnn.urflix.core.model.data.GenreModel
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
    onSeeAllFilmClick: (Int, Int) -> Unit,
    onFilmClick: (Int, Int) -> Unit,
    onSeeAllGenresClick: (Int) -> Unit,
    onGenreItemClick: (Int, String, Int) -> Unit,
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
            tvShowsSection = TvShowsSection.AIRING_TODAY,
            uiState = airingTodayTvShowsUiState,
        ),
        TvShowsSectionUiData(
            tvShowsSection = TvShowsSection.ON_THE_AIR,
            uiState = onTheAirTvShowsUiState,
        ),
        TvShowsSectionUiData(
            tvShowsSection = TvShowsSection.POPULAR,
            uiState = popularTvShowsUiState,
        ),
        TvShowsSectionUiData(
            tvShowsSection = TvShowsSection.TOP_RATED,
            uiState = topRatedTvShowsUiState,
        ),
    )

    val genresSectionUiState by viewModel.genresUiState.collectAsStateWithLifecycle()

    HomeScreen(
        moviesSectionsUiData = moviesSectionsUiData,
        tvShowsSectionsUiData = tvShowsSectionsUiData,
        genresSectionUiState = genresSectionUiState,
        onSeeAllFilmClick = onSeeAllFilmClick,
        onFilmClick = onFilmClick,
        onSeeAllGenresClick = onSeeAllGenresClick,
        onGenreItemClick = onGenreItemClick,
        modifier = modifier,
    )
}

@Composable
private fun HomeScreen(
    moviesSectionsUiData: List<MoviesSectionUiData>,
    tvShowsSectionsUiData: List<TvShowsSectionUiData>,
    genresSectionUiState: GenresSectionUiState,
    onSeeAllFilmClick: (Int, Int) -> Unit,
    onFilmClick: (Int, Int) -> Unit,
    onSeeAllGenresClick: (Int) -> Unit,
    onGenreItemClick: (Int, String, Int) -> Unit,
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
                    onSeeAllFilmClick = { filmTypeOrdinal ->
                        onSeeAllFilmClick(
                            moviesSectionUiData.moviesSection.ordinal,
                            filmTypeOrdinal,
                        )
                    },
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
                    section = tvShowsSectionUiData.tvShowsSection.displayedText,
                    filmType = FilmType.TV_SHOWS,
                    onSeeAllFilmClick = { filmTypeOrdinal ->
                        onSeeAllFilmClick(
                            tvShowsSectionUiData.tvShowsSection.ordinal,
                            filmTypeOrdinal,
                        )
                    },
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

        item {
            GenresSectionContent(
                uiState = genresSectionUiState,
                onSeeAllGenresClick = onSeeAllGenresClick,
                onGenreItemClick = onGenreItemClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            )
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

        is FilmsSectionUiState.Error -> Unit
    }
}

@Composable
private fun FilmsSectionHeader(
    section: String,
    filmType: FilmType,
    onSeeAllFilmClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
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
            modifier = Modifier.clickable {
                onSeeAllFilmClick(filmType.ordinal)
            }
        )
    }
}

@Composable
private fun GenresSectionContent(
    uiState: GenresSectionUiState,
    onSeeAllGenresClick: (Int) -> Unit,
    onGenreItemClick: (Int, String, Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    var selectedTabIndex by remember { mutableIntStateOf(0) }
    val tabs = listOf(
        FilmType.MOVIES.displayedText,
        FilmType.TV_SHOWS.displayedText,
    )
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = 16.dp)
        ) {
            Text(
                text = stringResource(id = R.string.popular_genres),
                color = MaterialTheme.colorScheme.onBackground,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.align(Alignment.Bottom)
            )

            Spacer(Modifier.weight(1f))

            Icon(
                imageVector = UrflixIcons.ArrowForward,
                contentDescription = stringResource(id = R.string.see_more),
                tint = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.clickable {
                    onSeeAllGenresClick(selectedTabIndex)
                }
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        when (uiState) {
            GenresSectionUiState.Loading -> {
                CircularLoading(
                    modifier = Modifier.fillMaxWidth()
                )
            }

            is GenresSectionUiState.Success -> {
                AnimatedContent(
                    targetState = selectedTabIndex,
                    label = "Genres horizontal list AnimatedContent"
                ) { targetState ->
                    LazyRow(
                        contentPadding = PaddingValues(horizontal = 16.dp),
                        horizontalArrangement = Arrangement.spacedBy(5.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        items(
                            items = when (targetState) {
                                0 -> uiState.movieGenres
                                1 -> uiState.tvShowGenres
                                else -> listOf()
                            },
                            key = { it.id },
                        ) { genre ->
                            GenreText(
                                genre = genre,
                                onGenreItemClick = {
                                    onGenreItemClick(
                                        genre.id,
                                        genre.name,
                                        selectedTabIndex
                                    )
                                }
                            )
                        }
                    }
                }

                WrappedTabRow(
                    tabs = tabs,
                    selectedTabIndex = selectedTabIndex,
                    onTabClick = { tabIndex ->
                        selectedTabIndex = tabIndex
                    }
                )
            }

            is GenresSectionUiState.Error -> Unit
        }
    }
}

@Composable
private fun GenreText(
    genre: GenreModel,
    onGenreItemClick: () -> Unit,
) {
    Text(
        text = genre.name,
        style = MaterialTheme.typography.labelMedium,
        color = MaterialTheme.colorScheme.onBackground,
        modifier = Modifier
            .clip(RoundedCornerShape(10.dp))
            .background(MaterialTheme.colorScheme.surfaceVariant)
            .clickable {
                onGenreItemClick()
            }
            .padding(horizontal = 16.dp, vertical = 8.dp)
    )
}

private val filmItemHeight = 200.dp
private val filmItemWidth = 120.dp

data class MoviesSectionUiData(
    val moviesSection: MoviesSection,
    val uiState: FilmsSectionUiState,
)

data class TvShowsSectionUiData(
    val tvShowsSection: TvShowsSection,
    val uiState: FilmsSectionUiState,
)