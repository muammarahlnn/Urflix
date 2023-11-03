package com.muammarahlnn.feature.search

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.muammarahlnn.urflix.core.common.result.asResult
import com.muammarahlnn.urflix.core.common.result.onError
import com.muammarahlnn.urflix.core.common.result.onLoading
import com.muammarahlnn.urflix.core.common.result.onSuccess
import com.muammarahlnn.urflix.core.data.repository.SearchRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


/**
 * @author Muammar Ahlan Abimanyu (muammarahlnn)
 * @file SearchViewModel, 03/11/2023 23.56 by Muammar Ahlan Abimanyu
 */
@HiltViewModel
class SearchViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val searchRepository: SearchRepository,
) : ViewModel() {

    val searchQuery = savedStateHandle.getStateFlow(key = SEARCH_QUERY, initialValue = "")

    private val _searchUiState = MutableStateFlow<SearchUiState>(SearchUiState.None)

    val searchUiState = _searchUiState.asStateFlow()

    fun onSearchQueryChanged(query: String) {
        savedStateHandle[SEARCH_QUERY] = query
        searchFilms()
    }

    fun searchFilms() {
        viewModelScope.launch {
            searchQuery
                .debounce(300)
                .distinctUntilChanged()
                .flatMapLatest { query ->
                    combine(
                        searchRepository.getSearchMovies(query),
                        searchRepository.getSearchTvShows(query),
                        ::Pair
                    )
                }
                .asResult()
                .collect { result ->
                    result.onLoading {
                        _searchUiState.update {
                            SearchUiState.Loading
                        }
                    }.onSuccess {
                        val (searchedMovies, searchedTvShows) = it
                        _searchUiState.update {
                            if (searchQuery.value.isNotEmpty()) {
                                SearchUiState.Success(
                                    searchedMovies = searchedMovies,
                                    searchedTvShows = searchedTvShows,
                                )
                            } else {
                                SearchUiState.None
                            }
                        }
                    }.onError { exception, message ->
                        Log.e("SearchViewModel", exception?.message.toString())
                        _searchUiState.update {
                            SearchUiState.Error(message)
                        }
                    }
                }
        }
    }
}

private const val SEARCH_QUERY = "search_query"