package com.amlavati.presentation_common_mvi.state

abstract class CommonResultConverterMvi<T : Any, R : Any> {
    abstract fun convertSuccess(data: T): R
}