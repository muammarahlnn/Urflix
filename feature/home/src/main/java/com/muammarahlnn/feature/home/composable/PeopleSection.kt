package com.muammarahlnn.feature.home.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.muammarahlnn.urflix.core.common.result.UiState
import com.muammarahlnn.urflix.core.designsystem.icon.UrflixIcons
import com.muammarahlnn.urflix.feature.home.R

/**
 * @Author Muammar Ahlan Abimanyu
 * @File PeopleSection, 24/05/2024 17.53
 */
@Composable
fun PeopleSectionContent(
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
                modifier = Modifier.clickable {
                    // todo
                }
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        LazyRow(
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            repeat(10) {
                item {
                    PersonItem()
                }
            }
        }
    }
}

@Composable
private fun PersonItem(
    modifier: Modifier = Modifier,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier,
    ) {
        val imageSize = 100.dp
        Image(
            painter = painterResource(id = R.drawable.default_avatar),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(imageSize)
                .clip(CircleShape)
        )

        Spacer(modifier = Modifier.height(2.dp))

        Text(
            text = "Lorem Ipsum Dolor Sit Amet",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onBackground,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.Center,
            modifier = Modifier.width(imageSize)
        )
    }
}