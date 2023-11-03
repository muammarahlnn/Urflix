package com.muammarahlnn.urflix.feature.filmdetails

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.muammarahlnn.urflix.core.designsystem.icon.UrflixIcons
import com.muammarahlnn.urflix.core.designsystem.theme.BlackTrans60
import com.muammarahlnn.urflix.core.designsystem.util.noRippleClickable


/**
 * @author Muammar Ahlan Abimanyu (muammarahlnn)
 * @file FilmDetailsScreen, 03/11/2023 13.55 by Muammar Ahlan Abimanyu
 */
@Composable
internal fun FilmDetailsRoute(
    modifier: Modifier = Modifier,
) {
    FilmDetailsScreen(
        modifier = modifier,
    )
}

@Composable
fun FilmDetailsScreen(
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier.fillMaxSize()
    ) {
        item {
            FilmDetailsHeaderInfo(
                modifier = Modifier.padding(bottom = 16.dp)
            )
        }
        item {
            FilmDetailsSynopsis()
        }
    }
}

@Composable
private fun FilmDetailsHeaderInfo(
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        FilmDetailsCarousel()
        Image(
            painter = painterResource(id = R.drawable.default_avatar),
            contentDescription = null,
            modifier = Modifier
                .padding(
                    top = backdropImageCarouselHeight - (backdropImageCarouselHeight / 5),
                    start = 16.dp,
                )
                .clip(RoundedCornerShape(10.dp))
                .width(posterImageWidth)
                .height(posterImageHeight)
                .align(Alignment.TopStart)
                .background(MaterialTheme.colorScheme.primary)
        )
        FilmDetailsDataContent(
            modifier = Modifier
                .padding(
                    top = backdropImageCarouselHeight + 16.dp,
                    start = posterImageWidth + 16.dp,
                )
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun FilmDetailsCarousel(
    modifier: Modifier = Modifier,
) {
    val images = listOf(1, 2, 3)
    val pagerState = rememberPagerState { images.size }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(backdropImageCarouselHeight)
    ) {
        HorizontalPager(
            state = pagerState,
            modifier = modifier.fillMaxSize()
        ) { index ->
            Image(
                painter = painterResource(id = R.drawable.default_avatar),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        }

        CarouselIndexIndicator(
            totalIndex = images.size,
            selectedIndex = pagerState.currentPage,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp)
        )

        val headerIconModifier = Modifier
            .padding(16.dp)
            .clip(CircleShape)
            .background(BlackTrans60)
            .padding(4.dp)
            .size(24.dp)

        IconButton(
            onClick = {},
            modifier = headerIconModifier.then(
                Modifier.align(Alignment.TopStart)
            )
        ) {
            Icon(
                imageVector = UrflixIcons.ArrowBack,
                contentDescription = stringResource(id = R.string.back_icon_desc),
                tint = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.size(20.dp)
            )
        }

        IconButton(
            onClick = {},
            modifier = headerIconModifier.then(
                Modifier.align(Alignment.TopEnd)
            )
        ) {
            Icon(
                imageVector = UrflixIcons.Bookmark,
                contentDescription = stringResource(id = R.string.bookmark_icon_desc),
                tint = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.size(20.dp)
            )
        }
    }
}

@Composable
private fun CarouselIndexIndicator(
    totalIndex: Int,
    selectedIndex: Int,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .clip(RoundedCornerShape(5.dp))
            .background(BlackTrans60)
            .padding(
                horizontal = 12.dp,
                vertical = 4.dp,
            )
    ) {
        Text(
            text = (selectedIndex + 1).toString(),
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onBackground
        )
        Spacer(modifier = Modifier.width(2.dp))
        Text(
            text = "/",
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onBackground
        )
        Spacer(modifier = Modifier.width(2.dp))
        Text(
            text = totalIndex.toString(),
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onBackground
        )
    }
}

@Composable
private fun FilmDetailsDataContent(
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        Text(
            text = "Haunting In Venice",
            style = MaterialTheme.typography.titleSmall.copy(
                fontSize = 16.sp
            ),
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = 16.dp),
        ) {
            Text(
                text = "Sep 2023",
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onSurface
            )

            Spacer(modifier = Modifier.width(4.dp))

            Text(
                text = stringResource(id = R.string.bullet),
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onSurface
            )

            Spacer(modifier = Modifier.width(4.dp))

            Text(
                text = "104 mins",
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onSurface
            )
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = 16.dp),
        ) {
            Icon(
                imageVector = UrflixIcons.Star,
                contentDescription = stringResource(id = R.string.rating),
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(18.dp)
            )

            Spacer(modifier = Modifier.width(4.dp))

            Text(
                text = "8.8",
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onSurface
            )
        }

        val genres = listOf("Mystery", "Thriller", "Crime")
        LazyRow(
            contentPadding = PaddingValues(16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(
                items = genres,
            ) {
                GenreItem(it)
            }
        }
    }
}

@Composable
private fun GenreItem(
    genre: String,
) {
    Text(
        text = genre,
        style = MaterialTheme.typography.labelMedium,
        color = MaterialTheme.colorScheme.onSurface,
        modifier = Modifier
            .clip(RoundedCornerShape(5.dp))
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.onSurface,
                shape = RoundedCornerShape(5.dp)
            )
            .padding(
                vertical = 4.dp,
                horizontal = 8.dp,
            )
    )
}

@Composable
private fun FilmDetailsSynopsis(
    modifier: Modifier = Modifier,
) {
    var isExpanded by remember { mutableStateOf(false) }
    Column(
        modifier = modifier.padding(horizontal = 16.dp)
    ) {
        Text(
            text = stringResource(id = R.string.synopsis),
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.onBackground
        )

        Spacer(modifier = Modifier.height(2.dp))

        Text(
            text = "Lorem ipsum dolor sit amet lorem ipsum dolor sit amet Lorem ipsum dolor sit amet lorem ipsum dolor sit amet Lorem ipsum dolor sit amet lorem ipsum dolor sit amet Lorem ipsum dolor sit amet lorem ipsum dolor sit amet",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onBackground,
            overflow = TextOverflow.Ellipsis,
            maxLines = if (isExpanded) Int.MAX_VALUE else 3,
        )
        Text(
            text = stringResource(
                id = if (!isExpanded) R.string.read_more else R.string.read_less
            ),
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.noRippleClickable {
                isExpanded = !isExpanded
            }
        )
    }
}

private val backdropImageCarouselHeight = 200.dp
private val posterImageHeight = 175.dp
private val posterImageWidth = 125.dp