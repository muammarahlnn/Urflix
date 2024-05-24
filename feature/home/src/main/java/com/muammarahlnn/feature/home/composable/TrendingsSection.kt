package com.muammarahlnn.feature.home.composable

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
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
import com.muammarahlnn.feature.home.FilmsSectionUiState
import com.muammarahlnn.urflix.core.designsystem.component.BaseAsyncImage
import com.muammarahlnn.urflix.core.designsystem.component.ErrorHomeSection
import com.muammarahlnn.urflix.core.designsystem.component.shimmerBrush
import com.muammarahlnn.urflix.core.designsystem.icon.UrflixIcons
import com.muammarahlnn.urflix.core.designsystem.theme.BlackTrans60
import com.muammarahlnn.urflix.core.designsystem.util.noRippleClickable
import com.muammarahlnn.urflix.core.model.ui.FilmType
import com.muammarahlnn.urflix.feature.home.R

/**
 * @Author Muammar Ahlan Abimanyu
 * @File TrendingsSection, 24/05/2024 17.41
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TrendingFilmsCarousel(
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