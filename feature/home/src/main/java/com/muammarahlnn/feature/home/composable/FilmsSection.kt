package com.muammarahlnn.feature.home.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.muammarahlnn.feature.home.FilmsSectionUiState
import com.muammarahlnn.urflix.core.designsystem.component.ErrorHomeSection
import com.muammarahlnn.urflix.core.designsystem.component.FilmItemCard
import com.muammarahlnn.urflix.core.designsystem.component.shimmerBrush
import com.muammarahlnn.urflix.core.designsystem.icon.UrflixIcons
import com.muammarahlnn.urflix.core.model.ui.FilmType
import com.muammarahlnn.urflix.feature.home.R

/**
 * @Author Muammar Ahlan Abimanyu
 * @File FilmsSection, 24/05/2024 17.39
 */

internal val filmItemHeight = 200.dp
internal val filmItemWidth = 120.dp

@Composable
fun FilmsSectionHorizontalList(
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
fun FilmsSectionHeader(
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