package com.muammarahlnn.feature.bookmarks

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.muammarahlnn.urflix.core.common.result.asResult
import com.muammarahlnn.urflix.core.common.result.onError
import com.muammarahlnn.urflix.core.common.result.onLoading
import com.muammarahlnn.urflix.core.common.result.onSuccess
import com.muammarahlnn.urflix.core.data.repository.BookmarksRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


/**
 * @author Muammar Ahlan Abimanyu (muammarahlnn)
 * @file BookmarksViewModel, 03/11/2023 22.12 by Muammar Ahlan Abimanyu
 */
@HiltViewModel
class BookmarksViewModel @Inject constructor(
    private val bookmarksRepository: BookmarksRepository
) : ViewModel() {

    private val _bookmarksUiState = MutableStateFlow<BookmarksUiState>(BookmarksUiState.Loading)

    val bookmarksUiState = _bookmarksUiState.asStateFlow()

    init {
        loadBookmarkedFilms()
    }

    fun loadBookmarkedFilms() {
        viewModelScope.launch {
            bookmarksRepository.getBookmarkedFilms()
                .asResult()
                .collect { result ->
                    result.onLoading {
                        _bookmarksUiState.update {
                            BookmarksUiState.Loading
                        }
                    }.onSuccess { films ->
                        _bookmarksUiState.update {
                            if (films.isNotEmpty()) {
                                BookmarksUiState.Success(films)
                            } else {
                                BookmarksUiState.SuccessEmpty()
                            }
                        }
                    }.onError { exception, message ->
                        Log.e("BookmakrsViewModel", exception?.message.toString())
                        _bookmarksUiState.update {
                            BookmarksUiState.Error(message)
                        }
                    }
                }
        }
    }
}