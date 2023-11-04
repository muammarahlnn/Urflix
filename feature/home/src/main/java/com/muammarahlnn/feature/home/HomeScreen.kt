package com.muammarahlnn.feature.home

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.PullRefreshState
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.muammarahlnn.urflix.core.designsystem.component.BaseAsyncImage
import com.muammarahlnn.urflix.core.designsystem.component.ErrorHomeSection
import com.muammarahlnn.urflix.core.designsystem.component.FilmItemCard
import com.muammarahlnn.urflix.core.designsystem.component.WrappedTabRow
import com.muammarahlnn.urflix.core.designsystem.component.shimmerBrush
import com.muammarahlnn.urflix.core.designsystem.icon.UrflixIcons
import com.muammarahlnn.urflix.core.designsystem.theme.BlackTrans60
import com.muammarahlnn.urflix.core.designsystem.util.noRippleClickable
import com.muammarahlnn.urflix.core.model.data.GenreModel
import com.muammarahlnn.urflix.core.model.data.constant.MoviesSection
import com.muammarahlnn.urflix.core.model.data.constant.TvShowsSection
import com.muammarahlnn.urflix.core.model.ui.FilmType
import com.muammarahlnn.urflix.feature.home.R


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

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun HomeScreen(
    trendingMoviesUiState: FilmsSectionUiState,
    moviesSectionsUiData: List<MoviesSectionUiData>,
    tvShowsSectionsUiData: List<TvShowsSectionUiData>,
    genresSectionUiState: GenresSectionUiState,
    pullRefreshState: PullRefreshState,
    isRefreshing: Boolean,
    onRefresh: () -> Unit,
    onSeeAllFilmClick: (Int, Int) -> Unit,
    onFilmClick: (Int, Int) -> Unit,
    onSeeAllGenresClick: (Int) -> Unit,
    onGenreItemClick: (Int, String, Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .pullRefresh(pullRefreshState)
    ) {
        LazyColumn(
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
        }

        PullRefreshIndicator(
            refreshing = isRefreshing,
            state = pullRefreshState,
            modifier = Modifier.align(Alignment.TopCenter)
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun TrendingFilmsCarousel(
    trendingMoviesUiState: FilmsSectionUiState,
    onRefresh: () -> Unit,
    onFilmClick: (Int, Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    val carouselHeight = 180.dp

    when (trendingMoviesUiState) {
        FilmsSectionUiState.Loading -> {
            TrendingFilmsCarouselShimmerLoading(height = carouselHeight)
        }

        is FilmsSectionUiState.Success -> {
            val films = trendingMoviesUiState.films
            val pagerState = rememberPagerState { films.size }
            Box(modifier = modifier) {
                HorizontalPager(
                    state = pagerState,
                    modifier = Modifier.fillMaxWidth()
                ) { index ->
                    Box(
                        modifier = Modifier.noRippleClickable {
                            onFilmClick(
                                films[index].id,
                                FilmType.MOVIES.ordinal
                            )
                        }
                    ) {
                        BaseAsyncImage(
                            model = films[index].backdropImage,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(carouselHeight)
                        )

                        Text(
                            text = films[index].title,
                            style = MaterialTheme.typography.titleSmall.copy(
                                fontSize = 16.sp,
                                fontWeight = FontWeight.SemiBold,
                            ),
                            color = MaterialTheme.colorScheme.onBackground,
                            textAlign = TextAlign.Center,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier
                                .padding(16.dp)
                                .align(Alignment.BottomCenter)
                        )
                    }
                }

                Icon(
                    imageVector = UrflixIcons.Fire,
                    contentDescription = stringResource(id = R.string.trending_films),
                    tint = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier
                        .clip(RoundedCornerShape(bottomEnd = 10.dp))
                        .background(BlackTrans60)
                        .padding(8.dp)
                        .align(Alignment.TopStart)
                )

                CarouselDotsIndicator(
                    totalDots = films.size,
                    selectedIndex = pagerState.currentPage,
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(
                            vertical = 8.dp,
                            horizontal = 16.dp
                        )
                )
            }
        }

        is FilmsSectionUiState.Error -> {
            ErrorHomeSection(
                text = trendingMoviesUiState.message,
                onRefresh = onRefresh,
                modifier = modifier
                    .fillMaxWidth()
                    .height(carouselHeight)
            )
        }
    }
}

@Composable
private fun CarouselDotsIndicator(
    totalDots: Int,
    selectedIndex: Int,
    modifier: Modifier = Modifier,
) {
    LazyRow(
        modifier = modifier.wrapContentSize()
    ) {
        items(count = totalDots) { index ->
            val color by animateColorAsState(
                targetValue = if (selectedIndex == index) {
                    MaterialTheme.colorScheme.onBackground
                } else {
                    MaterialTheme.colorScheme.background
                },
                label = "Carousel dots indicator"
            )

            Box(
                modifier = Modifier
                    .size(4.dp)
                    .clip(CircleShape)
                    .background(color)
            )

            if (index != totalDots - 1) {
                Spacer(modifier = Modifier.width(1.dp))
            }
        }
    }
}

@Composable
private fun TrendingFilmsCarouselShimmerLoading(height: Dp) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(height)
            .background(shimmerBrush())
    )
}

@Composable
private fun FilmsSectionHorizontalList(
    uiState: FilmsSectionUiState,
    onFilmClick: (Int, Int) -> Unit,
    onRefresh: () -> Unit,
    modifier: Modifier = Modifier,
) {
    when (uiState) {
        FilmsSectionUiState.Loading -> {
            FilmsSectionShimmerLoading()
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

        is FilmsSectionUiState.Error -> {
            ErrorHomeSection(
                text = uiState.message,
                onRefresh = onRefresh,
                modifier = modifier
                    .fillMaxWidth()
                    .height(filmItemHeight)
            )
        }
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
private fun FilmsSectionShimmerLoading() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = 16.dp,
                end = 16.dp,
                bottom = 40.dp
            )
    ) {
        repeat(3) {
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(10.dp))
                    .width(filmItemWidth)
                    .height(160.dp)
                    .background(shimmerBrush())
            )

            Spacer(modifier = Modifier.width(8.dp))
        }
    }
}

@Composable
private fun GenresSectionContent(
    uiState: GenresSectionUiState,
    onSeeAllGenresClick: (Int) -> Unit,
    onGenreItemClick: (Int, String, Int) -> Unit,
    onRefresh: () -> Unit,
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
                GenresSectionShimmerLoading()
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

            is GenresSectionUiState.Error -> {
                ErrorHomeSection(
                    text = uiState.message,
                    onRefresh = onRefresh,
                    modifier = modifier
                        .fillMaxWidth()
                        .height(filmItemHeight)
                )
            }
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

@Composable
private fun GenresSectionShimmerLoading() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = 16.dp,
                end = 16.dp,
                bottom = 32.dp
            )
    ) {
        repeat(6) {
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(10.dp))
                    .width(64.dp)
                    .height(32.dp)
                    .background(shimmerBrush())
            )

            Spacer(modifier = Modifier.width(4.dp))
        }
    }
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