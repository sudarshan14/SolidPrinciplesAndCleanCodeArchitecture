package com.amlavati.common.state

import com.amlavati.domain.entity.Result

abstract class CommonResultConverter<T : Any, R : Any> {


    fun convert(result: Result<T>): UiState<R> {

        return when (result) {
            is Result.Success -> {
                UiState.Success(convertSuccess(result.data))
            }

            is Result.Error -> {
                UiState.Error(result.exception.localizedMessage.orEmpty())
            }
        }
    }

    abstract fun convertSuccess(data: T): R
}