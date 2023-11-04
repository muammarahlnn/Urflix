package com.muammarahlnn.urflix.feature.filmssection

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.muammarahlnn.urflix.core.designsystem.component.BaseAsyncImage
import com.muammarahlnn.urflix.core.designsystem.component.CircularLoading
import com.muammarahlnn.urflix.core.designsystem.component.ErrorScreen
import com.muammarahlnn.urflix.core.designsystem.component.UrflixTopAppBarDefaults
import com.muammarahlnn.urflix.core.designsystem.component.VoteAverageText
import com.muammarahlnn.urflix.core.designsystem.icon.UrflixIcons
import com.muammarahlnn.urflix.core.designsystem.util.formatToYear
import com.muammarahlnn.urflix.core.model.data.FilmModel
import com.muammarahlnn.urflix.feature.filmssection.viewmodel.FilmsGenreViewModel
import com.muammarahlnn.urflix.feature.filmssection.viewmodel.FilmsSectionViewModel
import com.muammarahlnn.urflix.core.designsystem.R as designSystemR


/**
 * @author Muammar Ahlan Abimanyu (muammarahlnn)
 * @file FilmsScreen, 04/11/2023 06.17 by Muammar Ahlan Abimanyu
 */
@Composable
internal fun FilmsSectionRoute(
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: FilmsSectionViewModel = hiltViewModel(),
) {
    val uiState by viewModel.filmsUiState.collectAsState()
    val isLoadingNextPage by viewModel.loadingNextPageState.collectAsStateWithLifecycle()
    FilmsScreen(
        uiState = uiState,
        title = viewModel.title,
        isLoadingNextPage = isLoadingNextPage,
        onBackClick = onBackClick,
        onRefresh = viewModel::refresh,
        onScrolledToTheEnd = viewModel::fetchNextPage,
        modifier = modifier,
    )
}

@Composable
internal fun FilmsGenreRoute(
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: FilmsGenreViewModel = hiltViewModel(),
) {
    val uiState by viewModel.filmsUiState.collectAsState()
    val isLoadingNextPage by viewModel.loadingNextPageState.collectAsStateWithLifecycle()
    FilmsScreen(
        uiState = uiState,
        title = viewModel.title,
        isLoadingNextPage = isLoadingNextPage,
        onBackClick = onBackClick,
        onRefresh = viewModel::refresh,
        onScrolledToTheEnd = viewModel::fetchNextPage,
        modifier = modifier,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun FilmsScreen(
    uiState: FilmsUiState,
    title: String,
    isLoadingNextPage: Boolean,
    onBackClick: () -> Unit,
    onRefresh: () -> Unit,
    onScrolledToTheEnd: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold(
        topBar = {
            FilmsSectionTopAppBar(
                title = title,
                scrollBehavior = scrollBehavior,
                onBackClick = onBackClick,
            )
        },
        modifier = modifier.fillMaxSize()
    ) { padding ->
        when (uiState) {
            FilmsUiState.Loading -> {
                CircularLoading(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding)
                )
            }

            is FilmsUiState.Success -> {
                val listState = rememberLazyListState()
                LazyColumn(
                    state = listState,
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier
                        .fillMaxSize()
                        .nestedScroll(scrollBehavior.nestedScrollConnection)
                        .padding(padding)
                ) {
                    items(
                        items = uiState.films,
                        key = { it.id },
                    ) { film ->
                        FilmSectionItemCard(film)
                    }

                    if (isLoadingNextPage) {
                        item {
                            CircularLoading(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(filmsSectionItemCardHeight)
                            )
                        }
                    }
                }

                val isScrolledToTheEnd by remember(listState) {
                    derivedStateOf {
                        listState
                            .layoutInfo
                            .visibleItemsInfo
                            .lastOrNull()
                            ?.index == uiState.films.size - 1
                    }
                }
                if (isScrolledToTheEnd) onScrolledToTheEnd()
            }

            is FilmsUiState.Error -> {
                ErrorScreen(
                    text = uiState.message,
                    onRefresh = onRefresh,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding),
                )
            }
        }
    }
}

@Composable
private fun FilmSectionItemCard(
    film: FilmModel,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min)
    ) {
        Card(
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier
                .height(filmsSectionItemCardHeight)
                .width(120.dp)
        ) {
            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                BaseAsyncImage(
                    model = film.posterImage,
                    modifier = Modifier.fillMaxSize()
                )
                VoteAverageText(
                    voteAverage = film.voteAverage,
                    modifier = Modifier.align(Alignment.TopEnd)
                )
            }
        }

        Spacer(modifier = Modifier.width(8.dp))

        Column(
            modifier = Modifier
                .fillMaxHeight()
                .weight(1f)
        ) {
            Text(
                text = film.title,
                style = MaterialTheme.typography.titleSmall.copy(
                    fontSize = 16.sp
                ),
                color = MaterialTheme.colorScheme.onBackground,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
            )
            Text(
                text = film.genreNames.toGenresText(),
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onSurface,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = formatToYear(film.releaseDate),
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onSurface
            )
            Spacer(modifier = Modifier.weight(1f))
            Divider(
                color = MaterialTheme.colorScheme.surfaceVariant
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun FilmsSectionTopAppBar(
    title: String,
    scrollBehavior: TopAppBarScrollBehavior,
    onBackClick: () -> Unit,
) {
    TopAppBar(
        title = {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.SemiBold
                ),
                color = MaterialTheme.colorScheme.onBackground
            )
        },
        navigationIcon = {
            IconButton(onClick = onBackClick) {
                Icon(
                    imageVector = UrflixIcons.ArrowBack,
                    contentDescription = stringResource(id = designSystemR.string.back),
                    tint = MaterialTheme.colorScheme.onBackground
                )
            }
        },
        colors = UrflixTopAppBarDefaults.topAppBarColors(),
        scrollBehavior = scrollBehavior,
    )
}

private val filmsSectionItemCardHeight = 180.dp

private fun List<String>.toGenresText(): String {
    val genres: StringBuilder = StringBuilder()
    this.forEachIndexed { index, genreName ->
        genres.append(genreName)
        if (index < this.size - 1) {
            genres.append(", ")
        }
    }
    return genres.toString()
}