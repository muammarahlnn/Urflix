package com.muammarahlnn.feature.home

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.PullRefreshState
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.muammarahlnn.feature.home.composable.FilmsSectionHeader
import com.muammarahlnn.feature.home.composable.FilmsSectionHorizontalList
import com.muammarahlnn.feature.home.composable.GenresSectionContent
import com.muammarahlnn.feature.home.composable.PeopleSectionContent
import com.muammarahlnn.feature.home.composable.TrendingFilmsCarousel
import com.muammarahlnn.urflix.core.model.data.constant.MoviesSection
import com.muammarahlnn.urflix.core.model.data.constant.TvShowsSection
import com.muammarahlnn.urflix.core.model.ui.FilmType


/**
 * @author Muammar Ahlan Abimanyu (muammarahlnn)
 * @file HomeScreen, 02/11/2023 09.30 by Muammar Ahlan Abimanyu
 */
@OptIn(ExperimentalMaterialApi::class)
@Composable
internal fun HomeRoute(
    onSeeAllFilmClick: (Int, Int) -> Unit,
    onFilmClick: (Int, Int) -> Unit,
    onSeeAllGenresClick: (Int) -> Unit,
    onGenreItemClick: (Int, String, Int) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
) {
    val trendingMoviesUiState by viewModel.trendingMoviesUiState.collectAsStateWithLifecycle()
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
    val peopleSectionUiState by viewModel.popularPeopleUiState.collectAsStateWithLifecycle()

    val refreshing by viewModel.isRefreshing.collectAsStateWithLifecycle()
    val pullRefreshState = rememberPullRefreshState(
        refreshing = refreshing,
        onRefresh = viewModel::fetchHomeScreenData
    )

    HomeScreen(
        trendingMoviesUiState = trendingMoviesUiState,
        moviesSectionsUiData = moviesSectionsUiData,
        tvShowsSectionsUiData = tvShowsSectionsUiData,
        genresSectionUiState = genresSectionUiState,
        peopleSectionUiState = peopleSectionUiState,
        pullRefreshState = pullRefreshState,
        isRefreshing = refreshing,
        onRefresh = viewModel::fetchHomeScreenData,
        onSeeAllFilmClick = onSeeAllFilmClick,
        onFilmClick = onFilmClick,
        onSeeAllGenresClick = onSeeAllGenresClick,
        onGenreItemClick = onGenreItemClick,
        modifier = modifier,
    )
}

data class MoviesSectionUiData(
    val moviesSection: MoviesSection,
    val uiState: FilmsSectionUiState,
)

data class TvShowsSectionUiData(
    val tvShowsSection: TvShowsSection,
    val uiState: FilmsSectionUiState,
)

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun HomeScreen(
    trendingMoviesUiState: FilmsSectionUiState,
    moviesSectionsUiData: List<MoviesSectionUiData>,
    tvShowsSectionsUiData: List<TvShowsSectionUiData>,
    genresSectionUiState: GenresSectionUiState,
    peopleSectionUiState: PeopleSectionUiState,
    pullRefreshState: PullRefreshState,
    isRefreshing: Boolean,
    onRefresh: () -> Unit,
    onSeeAllFilmClick: (Int, Int) -> Unit,
    onFilmClick: (Int, Int) -> Unit,
    onSeeAllGenresClick: (Int) -> Unit,
    onGenreItemClick: (Int, String, Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current
    Box(
        modifier = Modifier
            .fillMaxSize()
            .pullRefresh(pullRefreshState)
    ) {
        LazyColumn(
            contentPadding = PaddingValues(bottom = 16.dp),
            modifier = modifier.fillMaxSize()
        ) {
            item {
                TrendingFilmsCarousel(
                    trendingMoviesUiState = trendingMoviesUiState,
                    onRefresh = onRefresh,
                    onFilmClick = onFilmClick,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp)
                )
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
                        onRefresh = onRefresh,
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
                        onRefresh = onRefresh,
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
                    onRefresh = onRefresh,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp)
                )
            }

            item {
                PeopleSectionContent(
                    uiState = peopleSectionUiState,
                    onSeeAllPeopleClick = {},
                    onPersonClick = { person ->
                        Toast.makeText(context, person.name, Toast.LENGTH_SHORT).show()
                    },
                    onRefresh = onRefresh,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }

        PullRefreshIndicator(
            refreshing = isRefreshing,
            state = pullRefreshState,
            modifier = Modifier.align(Alignment.TopCenter)
        )
    }
}