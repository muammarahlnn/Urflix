package com.muammarahlnn.feature.home.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.muammarahlnn.feature.home.PeopleSectionUiState
import com.muammarahlnn.urflix.core.common.result.UiState
import com.muammarahlnn.urflix.core.designsystem.component.BaseAsyncImage
import com.muammarahlnn.urflix.core.designsystem.component.ErrorHomeSection
import com.muammarahlnn.urflix.core.designsystem.component.shimmerBrush
import com.muammarahlnn.urflix.core.designsystem.icon.UrflixIcons
import com.muammarahlnn.urflix.core.model.data.PersonModel
import com.muammarahlnn.urflix.feature.home.R

/**
 * @Author Muammar Ahlan Abimanyu
 * @File PeopleSection, 24/05/2024 17.53
 */
@Composable
fun PeopleSectionContent(
    uiState: PeopleSectionUiState,
    onSeeAllPeopleClick: () -> Unit,
    onPersonClick: (PersonModel) -> Unit,
    onRefresh: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = 16.dp),
        ) {
            Text(
                text = stringResource(id = R.string.popular_people),
                color = MaterialTheme.colorScheme.onBackground,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.align(Alignment.Bottom)
            )

            Spacer(modifier = Modifier.weight(1f))

            Icon(
                imageVector = UrflixIcons.ArrowForward,
                contentDescription = stringResource(id = R.string.see_more),
                tint = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.clickable { onSeeAllPeopleClick() },
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        when (uiState) {
            UiState.Loading -> PeopleSectionShimmerLoading()

            is UiState.Error -> ErrorHomeSection(
                text = uiState.message,
                onRefresh = onRefresh,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(filmItemHeight)
            )

            is UiState.Success -> LazyRow(
                contentPadding = PaddingValues(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                items(
                    items = uiState.data,
                    key = { it.id },
                ) { person ->
                    PersonItem(
                        person = person,
                        onPersonClick = onPersonClick,
                    )
                }
            }
        }
    }
}

val imageSize = 100.dp

@Composable
private fun PersonItem(
    person: PersonModel,
    onPersonClick: (PersonModel) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.clickable { onPersonClick(person) },
    ) {
        BaseAsyncImage(
            model = person.profileImage,
            modifier = Modifier
                .size(imageSize)
                .clip(CircleShape)
        )

        Spacer(modifier = Modifier.height(2.dp))

        Text(
            text = person.name,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onBackground,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.Center,
            modifier = Modifier.width(imageSize)
        )
    }
}

@Composable
private fun PeopleSectionShimmerLoading() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = 16.dp,
                end = 16.dp,
                bottom = 32.dp
            )
    ) {
        repeat(3) {
            Box(
                modifier = Modifier
                    .clip(CircleShape)
                    .size(imageSize)
                    .background(shimmerBrush())
            )
            
            Spacer(modifier = Modifier.width(16.dp))
        }
    }
}