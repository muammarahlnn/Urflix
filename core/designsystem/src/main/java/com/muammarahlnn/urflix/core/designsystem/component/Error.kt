package com.muammarahlnn.urflix.core.designsystem.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.muammarahlnn.urflix.core.designsystem.R
import com.muammarahlnn.urflix.core.designsystem.icon.UrflixIcons


/**
 * @author Muammar Ahlan Abimanyu (muammarahlnn)
 * @file Error, 03/11/2023 20.46 by Muammar Ahlan Abimanyu
 */

@Composable
fun ErrorScreen(
    text: String,
    onRefresh: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.padding(16.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.error_illustration),
            contentDescription = null,
            modifier = Modifier.size(128.dp),
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = text,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onBackground,
            textAlign = TextAlign.Center,
        )

        Spacer(modifier = Modifier.height(4.dp))

        IconButton(
            onClick = onRefresh,
        ) {
            Icon(
                imageVector = UrflixIcons.Refresh,
                contentDescription = stringResource(id = R.string.refresh),
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(32.dp)
            )
        }
    }
}

@Composable
fun ErrorHomeSection(
    text: String,
    onRefresh: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = modifier.fillMaxWidth()
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.error_illustration),
                contentDescription = null,
                modifier = Modifier.size(82.dp),
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = text,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onBackground,
                textAlign = TextAlign.Center,
            )
        }

        Spacer(modifier = Modifier.width(16.dp))

        IconButton(
            onClick = onRefresh,
        ) {
            Icon(
                imageVector = UrflixIcons.Refresh,
                contentDescription = stringResource(id = R.string.refresh),
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(32.dp)
            )
        }
    }
}