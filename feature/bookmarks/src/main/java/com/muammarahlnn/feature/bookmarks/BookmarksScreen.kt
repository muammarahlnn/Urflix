package com.muammarahlnn.feature.bookmarks

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.muammarahlnn.urflix.core.designsystem.component.CircularLoading
import com.muammarahlnn.urflix.core.designsystem.component.ErrorScreen
import com.muammarahlnn.urflix.core.designsystem.component.NoDataScreen
import com.muammarahlnn.urflix.core.designsystem.icon.UrflixIcons
import com.muammarahlnn.urflix.core.model.data.FilmModel
import com.muammarahlnn.urflix.core.model.ui.FilmType
import com.muammarahlnn.urflix.feature.bookmarks.R


/**
 * @author Muammar Ahlan Abimanyu (muammarahlnn)
 * @file BookmarksScreen, 02/11/2023 09.33 by Muammar Ahlan Abimanyu
 */
@Composable
internal fun BookmarksRoute(
    modifier: Modifier = Modifier,
    viewModel: BookmarksViewModel = hiltViewModel(),
) {
    val uiState by viewModel.bookmarksUiState.collectAsStateWithLifecycle()
    BookmarksScreen(
        uiState = uiState,
        onRefresh = viewModel::loadBookmarkedFilms,
        modifier = modifier,
    )
}

@Composable
private fun BookmarksScreen(
    uiState: BookmarksUiState,
    onRefresh: () -> Unit,
    modifier: Modifier = Modifier,
) {
    when (uiState) {
        BookmarksUiState.Loading -> {
            CircularLoading(
                modifier = modifier.fillMaxSize()
            )
        }

        is BookmarksUiState.Success -> {
            LazyColumn(
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = modifier.fillMaxSize()
            ) {
                items(
                    items = uiState.films,
                ) { film ->
                    BookmarkedFilmCard(film)
                }
            }
        }

        is BookmarksUiState.SuccessEmpty -> {
            NoDataScreen(
                text = uiState.message,
                modifier = modifier.fillMaxSize()
            )
        }

        is BookmarksUiState.Error -> {
            ErrorScreen(
                text = uiState.message,
                onRefresh = onRefresh,
                modifier = modifier.fillMaxSize()
            )
        }
    }
}

@Composable
private fun BookmarkedFilmCard(
    film: FilmModel,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .clip(RoundedCornerShape(10.dp))
            .fillMaxWidth()
            .height(80.dp)
            .background(MaterialTheme.colorScheme.surfaceVariant)
    ) {
        AsyncImage(
            model = film.posterImage,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxHeight()
                .width(80.dp),
        )

        Column(
            modifier = Modifier
                .weight(1f)
                .padding(8.dp)
        ) {
            Text(
                text = film.title,
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.onBackground,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
            )
            Text(
                text = formatToYear(film.releaseDate),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface
            )
        }

        Box(
            modifier = Modifier
                .clip(
                    RoundedCornerShape(
                        topStart = 10.dp
                    )
                )
                .background(MaterialTheme.colorScheme.secondary)
                .align(Alignment.Bottom)
                .padding(4.dp)
        ) {
            Icon(
                imageVector = when (film.filmType) {
                    FilmType.MOVIES -> UrflixIcons.Movie
                    FilmType.TV_SHOWS -> UrflixIcons.Tv
                },
                contentDescription = stringResource(id = R.string.movie),
                tint = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}

private fun formatToYear(date: String): String =
    if (date.isNotEmpty()) date.substring(0, 4) else "-"