package com.muammarahlnn.urflix.feature.genres

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.muammarahlnn.urflix.core.designsystem.component.CircularLoading
import com.muammarahlnn.urflix.core.designsystem.component.ErrorScreen
import com.muammarahlnn.urflix.core.designsystem.component.UrflixTopAppBarDefaults
import com.muammarahlnn.urflix.core.designsystem.icon.UrflixIcons
import com.muammarahlnn.urflix.core.designsystem.R as designSystemR
import com.muammarahlnn.urflix.core.model.data.GenreModel
import com.muammarahlnn.urflix.core.model.ui.FilmType


/**
 * @author Muammar Ahlan Abimanyu (muammarahlnn)
 * @file GenresScreen, 04/11/2023 14.50 by Muammar Ahlan Abimanyu
 */
@Composable
internal fun GenresRoute(
    onBackClick: () -> Unit,
    onGenreItemClick: (Int, String, Int) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: GenresViewModel = hiltViewModel()
) {
    val uiState by viewModel.genresUiState.collectAsStateWithLifecycle()
    GenresScreen(
        uiState = uiState,
        filmType = viewModel.filmType,
        onBackClick = onBackClick,
        onRefresh = viewModel::fetchGenres,
        onGenreItemClick = onGenreItemClick,
        modifier = modifier,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun GenresScreen(
    uiState: GenresUiState,
    filmType: FilmType,
    onBackClick: () -> Unit,
    onRefresh: () -> Unit,
    onGenreItemClick: (Int, String, Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold(
        topBar = {
            GenresTopAppBar(
                filmType = filmType,
                onBackClick = onBackClick,
                scrollBehavior = scrollBehavior,
            )
        },
        modifier = modifier.fillMaxSize()
    ) { padding ->
        when (uiState) {
            GenresUiState.Loading -> {
                CircularLoading(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding)
                )
            }

            is GenresUiState.Success -> {
                LazyColumn(
                    contentPadding = PaddingValues(
                        vertical = 16.dp,
                        horizontal = 48.dp
                    ),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier
                        .fillMaxSize()
                        .nestedScroll(scrollBehavior.nestedScrollConnection)
                        .padding(padding),
                ) {
                    items(
                        items = uiState.genres,
                        key = { it.id },
                    ) { genre ->
                        GenreItem(
                            genre = genre,
                            onGenreItemClick = {
                                onGenreItemClick(
                                    genre.id,
                                    genre.name,
                                    filmType.ordinal,
                                )
                            }
                        )
                    }
                }
            }

            is GenresUiState.Error -> {
                ErrorScreen(
                    text = uiState.message,
                    onRefresh = onRefresh,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding)
                )
            }
        }
    }
}

@Composable
private fun GenreItem(
    genre: GenreModel,
    onGenreItemClick: () -> Unit,
) {
    Text(
        text = genre.name,
        style = MaterialTheme.typography.labelLarge,
        color = MaterialTheme.colorScheme.onBackground,
        modifier = Modifier
            .clip(RoundedCornerShape(10.dp))
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.secondary)
            .clickable {
                onGenreItemClick()
            }
            .padding(
                horizontal = 24.dp,
                vertical = 16.dp
            )
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun GenresTopAppBar(
    filmType: FilmType,
    onBackClick: () -> Unit,
    scrollBehavior: TopAppBarScrollBehavior? = null,
) {
    TopAppBar(
        title = {
            Column {
                Text(
                    text = stringResource(id = R.string.genres),
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onBackground
                )
                Text(
                    text = filmType.displayedText,
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
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
        scrollBehavior = scrollBehavior
    )
}