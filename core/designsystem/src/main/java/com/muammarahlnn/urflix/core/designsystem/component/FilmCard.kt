package com.muammarahlnn.urflix.core.designsystem.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.muammarahlnn.urflix.core.designsystem.theme.BlackTrans60
import com.muammarahlnn.urflix.core.designsystem.util.noRippleClickable
import com.muammarahlnn.urflix.core.model.data.FilmModel


/**
 * @author Muammar Ahlan Abimanyu (muammarahlnn)
 * @file FilmCard, 04/11/2023 01.34 by Muammar Ahlan Abimanyu
 */
@Composable
fun FilmItemCard(
    film: FilmModel,
    onFilmClick: (Int, Int) -> Unit,
    width: Dp = 125.dp,
    height: Dp = 200.dp,
) {
    Column(
        modifier = Modifier
            .width(width)
            .height(height)
            .noRippleClickable {
                onFilmClick(
                    film.id,
                    film.filmType.ordinal
                )
            }
    ) {
        Card(
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier.weight(1.6f)
        ) {
            Box {
                BaseAsyncImage(
                    model = film.posterImage,
                    modifier = Modifier.fillMaxSize(),
                )
                VoteAverageText(
                    voteAverage = film.voteAverage,
                    modifier = Modifier.align(Alignment.TopEnd)
                )
            }
        }

        Spacer(modifier = Modifier.height(2.dp))

        Text(
            text = film.title,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onBackground,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.weight(0.4f)
        )
    }
}

@Composable
fun VoteAverageText(
    voteAverage: Float,
    modifier: Modifier = Modifier,
) {
    Card(
        shape = RoundedCornerShape(bottomStart = 10.dp),
        colors = CardDefaults.cardColors(
            containerColor = BlackTrans60
        ),
        modifier = modifier,
    ) {
        val formattedVoteAverage = String.format("%.1f", voteAverage)
        Text(
            text = formattedVoteAverage,
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.padding(
                horizontal = 9.dp,
                vertical = 3.dp
            )
        )
    }
}