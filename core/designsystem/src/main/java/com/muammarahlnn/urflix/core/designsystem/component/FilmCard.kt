package com.muammarahlnn.urflix.core.designsystem.component

import androidx.compose.foundation.clickable
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.muammarahlnn.urflix.core.designsystem.theme.BlackTrans60
import com.muammarahlnn.urflix.core.model.data.FilmModel


/**
 * @author Muammar Ahlan Abimanyu (muammarahlnn)
 * @file FilmCard, 04/11/2023 01.34 by Muammar Ahlan Abimanyu
 */
@Composable
fun FilmItemCard(
    width: Dp,
    height: Dp,
    film: FilmModel,
    onFilmClick: (Int, Int) -> Unit,
) {
    Column(
        modifier = Modifier
            .width(width)
            .height(height)
            .clickable {
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
                AsyncImage(
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop,
                    model = film.posterImage,
                    contentDescription = null
                )
                Card(
                    shape = RoundedCornerShape(bottomStart = 10.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = BlackTrans60
                    ),
                    modifier = Modifier.align(Alignment.TopEnd)
                ) {
                    val formattedVoteAverage = String.format("%.1f", film.voteAverage)
                    Text(
                        text = formattedVoteAverage,
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.onBackground,
                        modifier = Modifier
                            .padding(horizontal = 10.dp, vertical = 4.dp)
                    )
                }
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