package com.muammarahlnn.urflix.core.common.result

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart


/**
 * @author Muammar Ahlan Abimanyu (muammarahlnn)
 * @file Result, 02/11/2023 15.30 by Muammar Ahlan Abimanyu
 */
sealed interface Result<out T> {

    data object Loading : Result<Nothing>

    data class Success<T>(val data: T) : Result<T>

    data class Error(
        val exception: Throwable? = null,
        val message: String = "Something went wrong, please try again."
    ) : Result<Nothing>
}

fun <T>Flow<T>.asResult(): Flow<Result<T>> =
    this.map<T, Result<T>> {
        Result.Success(it)
    }.onStart {
        emit(Result.Loading)
    }.catch {
        emit(Result.Error(it))
    }

inline fun <reified T> Result<T>.onLoading(
    callback: () -> Unit
) = apply {
    if (this is Result.Loading) {
        callback()
    }
}

inline fun <reified T> Result<T>.onSuccess(
    callback: (data: T) -> Unit,
) = apply {
    if (this is Result.Success) {
        callback(data)
    }
}

inline fun <reified T> Result<T>.onError(
    callback: (
        exception: Throwable?,
        message: String,
    ) -> Unit,
) = apply {
    if (this is Result.Error) {
        callback(exception, message)
    }
}