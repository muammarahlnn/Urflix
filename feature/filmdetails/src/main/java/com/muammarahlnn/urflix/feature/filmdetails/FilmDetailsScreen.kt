package com.muammarahlnn.urflix.feature.filmdetails

import android.widget.Toast
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.animateContentSize
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.muammarahlnn.urflix.core.designsystem.R as designSystemR
import com.muammarahlnn.urflix.core.designsystem.component.BaseAsyncImage
import com.muammarahlnn.urflix.core.designsystem.component.CircularLoading
import com.muammarahlnn.urflix.core.designsystem.component.ErrorScreen
import com.muammarahlnn.urflix.core.designsystem.icon.UrflixIcons
import com.muammarahlnn.urflix.core.designsystem.theme.BlackTrans60
import com.muammarahlnn.urflix.core.designsystem.util.noRippleClickable
import com.muammarahlnn.urflix.core.model.data.FilmDetailsModel
import com.muammarahlnn.urflix.core.model.data.GenreModel
import com.muammarahlnn.urflix.core.model.data.ImageModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


/**
 * @author Muammar Ahlan Abimanyu (muammarahlnn)
 * @file FilmDetailsScreen, 03/11/2023 13.55 by Muammar Ahlan Abimanyu
 */
@Composable
internal fun FilmDetailsRoute(
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: FilmDetailsViewModel = hiltViewModel()
) {
    val context = LocalContext.current

    val uiState by viewModel.filmDetailsUiState.collectAsStateWithLifecycle()
    val isFilmBookmarked by viewModel.isFilmBookmarked.collectAsStateWithLifecycle()
    FilmDetailsScreen(
        uiState = uiState,
        isFilmBookmarked = isFilmBookmarked,
        onBackClick = onBackClick,
        onRefresh = viewModel::refreshFilmDetailsScreen,
        onBookmarkClick = { filmDetails ->
            val message: String
            if (isFilmBookmarked) {
                viewModel.deleteBookmarkedFilm()
                message = "${filmDetails.title} is successfully removed from Bookmarks"
            } else {
                viewModel.insertBookmarkedFilm(filmDetails)
                message = "${filmDetails.title} is successfully saved to Bookmarks"
            }
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        },
        modifier = modifier,
    )
}

@Composable
fun FilmDetailsScreen(
    uiState: FilmDetailsUiState,
    isFilmBookmarked: Boolean,
    onBackClick: () -> Unit,
    onRefresh: () -> Unit,
    onBookmarkClick: (FilmDetailsModel) -> Unit,
    modifier: Modifier = Modifier,
) {
    when (uiState) {
        FilmDetailsUiState.Loading -> {
            CircularLoading(
                modifier = modifier.fillMaxSize()
            )
        }

        is FilmDetailsUiState.Success -> {
            LazyColumn(
                modifier = modifier.fillMaxSize()
            ) {
                item {
                    FilmDetailsHeaderInfo(
                        filmDetails = uiState.filmDetails,
                        isFilmBookmarked = isFilmBookmarked,
                        onBackClick = onBackClick,
                        onBookmarkClick = onBookmarkClick,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )
                }
                item {
                    FilmDetailsSynopsis(
                        uiState.filmDetails.overview
                    )
                }
            }
        }

        is FilmDetailsUiState.Error -> {
            ErrorScreen(
                text = uiState.message,
                onRefresh = onRefresh,
                modifier = modifier.fillMaxSize()
            )
        }
    }
}

@Composable
private fun FilmDetailsHeaderInfo(
    filmDetails: FilmDetailsModel,
    isFilmBookmarked: Boolean,
    onBackClick: () -> Unit,
    onBookmarkClick: (FilmDetailsModel) -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        FilmDetailsCarousel(
            isFilmBookmarked = isFilmBookmarked,
            backdrops = filmDetails.backdrops,
            onBackClick = onBackClick,
            onBookmarkClick = {
                onBookmarkClick(filmDetails)
            },
        )
        BaseAsyncImage(
            model = filmDetails.posterImage,
            modifier = Modifier
                .padding(
                    top = backdropImageCarouselHeight - (backdropImageCarouselHeight / 5),
                    start = 16.dp,
                )
                .clip(RoundedCornerShape(10.dp))
                .width(posterImageWidth)
                .height(posterImageHeight)
                .align(Alignment.TopStart)
        )
        FilmDetailsDataContent(
            title = filmDetails.title,
            releaseDate = filmDetails.releaseDate,
            duration = filmDetails.duration,
            voteAverage = filmDetails.voteAverage,
            genres = filmDetails.genres,
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
    isFilmBookmarked: Boolean,
    backdrops: List<ImageModel>,
    onBackClick: () -> Unit,
    onBookmarkClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val pagerState = rememberPagerState { backdrops.size }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(backdropImageCarouselHeight)
    ) {
        if (backdrops.isNotEmpty()) {
            HorizontalPager(
                state = pagerState,
                modifier = modifier.fillMaxSize()
            ) { index ->
                BaseAsyncImage(
                    model = backdrops[index].fileImage,
                    modifier = Modifier.fillMaxSize()
                )
            }

            CarouselIndexIndicator(
                totalIndex = backdrops.size,
                selectedIndex = pagerState.currentPage,
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(16.dp)
            )
        } else {
            Image(
                painter = painterResource(id = designSystemR.drawable.error_image_placeholder),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        }

        val headerIconModifier = Modifier
            .padding(16.dp)
            .clip(CircleShape)
            .background(BlackTrans60)
            .padding(4.dp)
            .size(24.dp)

        IconButton(
            onClick = onBackClick,
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
            onClick = onBookmarkClick,
            modifier = headerIconModifier.then(
                Modifier.align(Alignment.TopEnd)
            )
        ) {
            Icon(
                imageVector =
                    if (isFilmBookmarked) UrflixIcons.Bookmark
                    else UrflixIcons.BookmarkBorder,
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
    title: String,
    releaseDate: String,
    duration: Int,
    voteAverage: Float,
    genres: List<GenreModel>,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleSmall.copy(
                fontSize = 16.sp
            ),
            color = MaterialTheme.colorScheme.onBackground,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = 16.dp),
        ) {
            Text(
                text = formatDate(releaseDate),
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
                text = "$duration mins",
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

            val formattedVoteAverage = String.format("%.1f", voteAverage)
            Text(
                text = formattedVoteAverage,
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onSurface
            )
        }

        LazyRow(
            contentPadding = PaddingValues(16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(
                items = genres,
                key = { it.id }
            ) {
                GenreItem(it)
            }
        }
    }
}

@Composable
private fun GenreItem(genre: GenreModel) {
    Text(
        text = genre.name,
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
    synopsis: String,
    modifier: Modifier = Modifier,
) {
    var isExpanded by remember { mutableStateOf(false) }
    Column(
        modifier = modifier
            .animateContentSize()
            .noRippleClickable {
                isExpanded = !isExpanded
            }
            .padding(horizontal = 16.dp)
    ) {
        Text(
            text = stringResource(id = R.string.synopsis),
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.onBackground,
        )

        Spacer(modifier = Modifier.height(2.dp))

        AnimatedContent(
            targetState = isExpanded,
            label = "Synopsis AnimatedContent",
        ) { targetState ->
            Text(
                text = synopsis,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onBackground,
                overflow = TextOverflow.Ellipsis,
                maxLines = if (targetState) Int.MAX_VALUE else 3,
            )
        }
        Text(
            text = stringResource(
                id = if (!isExpanded) R.string.read_more else R.string.read_less
            ),
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.primary,
        )
    }
}

private val backdropImageCarouselHeight = 200.dp
private val posterImageHeight = 175.dp
private val posterImageWidth = 125.dp

private fun formatDate(inputDate: String): String {
    if (inputDate.isEmpty()) return "-"

    val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.US)
    val outputFormat = SimpleDateFormat("MMM yyyy", Locale.US)
    val date: Date = inputFormat.parse(inputDate) as Date
    return outputFormat.format(date)
}