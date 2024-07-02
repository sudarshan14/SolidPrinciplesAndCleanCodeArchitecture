package com.amlavati.common.state

sealed class UiState<out T : Any> {
    data object Loading : UiState<Nothing>()
    data class Error<T : Any>(val message: String) : UiState<T>()
    data class Success<T : Any>(val data: T) : UiState<T>()
}