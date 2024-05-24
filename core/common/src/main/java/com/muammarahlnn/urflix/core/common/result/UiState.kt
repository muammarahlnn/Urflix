package com.muammarahlnn.urflix.core.common.result

/**
 * @Author Muammar Ahlan Abimanyu
 * @File UiState, 24/05/2024 18.08
 */
sealed interface UiState<out T> {

    data object Loading : UiState<Nothing>

    data class Success<T>(val data: T) : UiState<T>

    data class Error(val message: String) : UiState<Nothing>
}