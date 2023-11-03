package com.muammarahlnn.feature.search

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.muammarahlnn.urflix.core.designsystem.component.CircularLoading
import com.muammarahlnn.urflix.core.designsystem.component.ErrorScreen
import com.muammarahlnn.urflix.core.designsystem.component.FilmItemCard
import com.muammarahlnn.urflix.core.designsystem.component.NoDataScreen
import com.muammarahlnn.urflix.core.designsystem.component.WrappedTabRow
import com.muammarahlnn.urflix.core.designsystem.icon.UrflixIcons
import com.muammarahlnn.urflix.core.model.data.FilmModel
import com.muammarahlnn.urflix.core.model.ui.FilmType
import com.muammarahlnn.urflix.feature.search.R
import kotlinx.coroutines.launch


/**
 * @author Muammar Ahlan Abimanyu (muammarahlnn)
 * @file SearchScreen, 02/11/2023 09.33 by Muammar Ahlan Abimanyu
 */
@Composable
internal fun SearchRoute(
    onFilmClick: (Int, Int) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: SearchViewModel = hiltViewModel(),
) {
    val uiState by viewModel.searchUiState.collectAsStateWithLifecycle()
    val searchQuery by viewModel.searchQuery.collectAsStateWithLifecycle()
    SearchScreen(
        uiState = uiState,
        searchQuery = searchQuery,
        onRefresh = viewModel::searchFilms,
        onSearchQueryChanged = viewModel::onSearchQueryChanged,
        onFilmClick = onFilmClick,
        modifier = modifier,
    )
}

@Composable
private fun SearchScreen(
    uiState: SearchUiState,
    searchQuery: String,
    onRefresh: () -> Unit,
    onSearchQueryChanged: (String) -> Unit,
    onFilmClick: (Int, Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        SearchTextField(
            searchQuery = searchQuery,
            onSearchQueryChanged = onSearchQueryChanged,
            modifier = Modifier.padding(16.dp)
        )

        SearchPager(
            uiState = uiState,
            searchQuery = searchQuery,
            onRefresh = onRefresh,
            onFilmClick = onFilmClick,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
        )
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchTextField(
    searchQuery: String,
    onSearchQueryChanged: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    val focusRequester = remember { FocusRequester() }
    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }

    TextField(
        value = searchQuery,
        onValueChange = {
            if (!it.contains("\n")) {
                onSearchQueryChanged(it)
            }
        },
        placeholder = {
            Text(
                text = stringResource(id = R.string.search_text_field_placeholder),
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurface,
            )
        },
        leadingIcon = {
            Icon(
                imageVector = UrflixIcons.Search,
                contentDescription = stringResource(id = R.string.search),
            )
        },
        trailingIcon = {
            if (searchQuery.isNotEmpty()) {
                IconButton(
                    onClick = {
                        onSearchQueryChanged("")
                    },
                ) {
                    Icon(
                        imageVector = UrflixIcons.Clear,
                        contentDescription = stringResource(id = R.string.clear_query),
                    )
                }
            }
        },
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Search,
        ),
        keyboardActions = KeyboardActions(
            onSearch = {
                keyboardController?.hide()
            },
        ),
        maxLines = 1,
        singleLine = true,
        textStyle = MaterialTheme.typography.bodySmall,
        shape = RoundedCornerShape(10.dp),
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            unfocusedContainerColor = MaterialTheme.colorScheme.surfaceVariant,
            focusedContainerColor = MaterialTheme.colorScheme.surfaceVariant,
            disabledContainerColor = MaterialTheme.colorScheme.surfaceVariant,
            unfocusedLeadingIconColor = MaterialTheme.colorScheme.onSurface,
            focusedLeadingIconColor = MaterialTheme.colorScheme.onBackground,
            focusedTrailingIconColor = MaterialTheme.colorScheme.onBackground,
            unfocusedTextColor = MaterialTheme.colorScheme.onBackground,
            focusedTextColor = MaterialTheme.colorScheme.onBackground,
            cursorColor = MaterialTheme.colorScheme.onBackground,
        ),
        modifier = modifier
            .fillMaxWidth()
            .focusRequester(focusRequester)
            .onKeyEvent {
                if (it.key == Key.Enter) {
                    keyboardController?.hide()
                    true
                } else {
                    false
                }
            },
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun SearchPager(
    uiState: SearchUiState,
    searchQuery: String,
    onFilmClick: (Int, Int) -> Unit,
    onRefresh: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val coroutineScope = rememberCoroutineScope()
    val tabs = listOf(
        stringResource(id = R.string.movies),
        stringResource(id = R.string.tv_shows),
    )
    val pagerState = rememberPagerState { tabs.size }
    Column(modifier = modifier.fillMaxSize()) {
        SearchTabRow(
            tabs = tabs,
            selectedTabIndex = pagerState.currentPage,
            onTabSelected = { selectedIndex ->
                coroutineScope.launch {
                    pagerState.animateScrollToPage(selectedIndex)
                }
            },
        )
        SearchHorizontalPager(
            uiState = uiState,
            pagerState = pagerState,
            searchQuery = searchQuery,
            onFilmClick = onFilmClick,
            onRefresh = onRefresh,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        )
    }
}

@Composable
private fun SearchTabRow(
    tabs: List<String>,
    selectedTabIndex: Int,
    onTabSelected: (Int) -> Unit,
) {
    WrappedTabRow(
        tabs = tabs,
        selectedTabIndex = selectedTabIndex,
        onTabClick = onTabSelected,
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun SearchHorizontalPager(
    uiState: SearchUiState,
    pagerState: PagerState,
    searchQuery: String,
    onFilmClick: (Int, Int) -> Unit,
    onRefresh: () -> Unit,
    modifier: Modifier = Modifier,
) {
    HorizontalPager(
        state = pagerState,
        modifier = modifier.fillMaxSize(),
    ) { pageIndex ->
        when (pageIndex) {
            0 -> SearchResultContent(
                uiState = uiState,
                filmType = FilmType.MOVIES,
                searchQuery = searchQuery,
                onFilmClick = onFilmClick,
                onRefresh = onRefresh,
                modifier = Modifier.fillMaxSize()
            )
            1 -> SearchResultContent(
                uiState = uiState,
                filmType = FilmType.TV_SHOWS,
                searchQuery = searchQuery,
                onFilmClick = onFilmClick,
                onRefresh = onRefresh,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}

@Composable
private fun SearchResultContent(
    uiState: SearchUiState,
    searchQuery: String,
    filmType: FilmType,
    onFilmClick: (Int, Int) -> Unit,
    onRefresh: () -> Unit,
    modifier: Modifier = Modifier,
) {
    when (uiState) {
        SearchUiState.None -> {
            NotSearchYetContent(
                text = stringResource(
                    id = when (filmType) {
                        FilmType.MOVIES -> R.string.not_search_yet_text_movie
                        FilmType.TV_SHOWS -> R.string.not_search_yet_text_tv_show
                    }
                ),
                modifier = modifier
            )
        }

        SearchUiState.Loading -> {
            CircularLoading(
                modifier = modifier
            )
        }

        is SearchUiState.Success -> {
            SearchResultVerticalGrid(
                searchQuery = searchQuery,
                films = when (filmType) {
                    FilmType.MOVIES -> uiState.searchedMovies
                    FilmType.TV_SHOWS -> uiState.searchedTvShows
                },
                onFilmClick = onFilmClick,
                modifier = modifier
            )
        }

        is SearchUiState.Error -> {
            ErrorScreen(
                text = uiState.message,
                onRefresh = onRefresh,
                modifier = modifier
            )
        }
    }
}

@Composable
private fun NotSearchYetContent(
    text: String,
    modifier: Modifier = Modifier,
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Image(
            painter = painterResource(id = R.drawable.searching_illustration),
            contentDescription = null,
            modifier = Modifier.size(162.dp),
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = text,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onBackground,
        )
    }
}

@Composable
private fun SearchResultVerticalGrid(
    searchQuery: String,
    films: List<FilmModel>,
    onFilmClick: (Int, Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    if (films.isNotEmpty()) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = modifier,
        ) {
            items(
                items = films,
                key = { it.id }
            ) { film ->
                FilmItemCard(
                    height = filmItemCardHeight,
                    film = film,
                    onFilmClick = onFilmClick,
                )
            }
        }
    } else {
        NoDataScreen(
            text = stringResource(
                id = R.string.empty_search_result_text,
                searchQuery
            ),
            modifier = modifier,
        )
    }
}

private val filmItemCardHeight = 180.dp