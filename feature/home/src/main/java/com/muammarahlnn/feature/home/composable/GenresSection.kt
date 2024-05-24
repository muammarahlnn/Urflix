package com.muammarahlnn.feature.home.composable

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import com.muammarahlnn.feature.home.GenresSectionUiState
import com.muammarahlnn.urflix.core.designsystem.component.ErrorHomeSection
import com.muammarahlnn.urflix.core.designsystem.component.WrappedTabRow
import com.muammarahlnn.urflix.core.designsystem.component.shimmerBrush
import com.muammarahlnn.urflix.core.designsystem.icon.UrflixIcons
import com.muammarahlnn.urflix.core.model.data.GenreModel
import com.muammarahlnn.urflix.core.model.ui.FilmType
import com.muammarahlnn.urflix.feature.home.R

/**
 * @Author Muammar Ahlan Abimanyu
 * @File GenresSection, 24/05/2024 17.38
 */
@Composable
fun GenresSectionContent(
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