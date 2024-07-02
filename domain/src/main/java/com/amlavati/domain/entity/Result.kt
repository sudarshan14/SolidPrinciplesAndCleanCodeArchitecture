package com.amlavati.domain.entity

/**
 * Result class will hold the success and error
 * information:
 *
 * @param T
 */
sealed class Result<out T : Any?> {
    /**
     * Success
     *
     * @param T
     * @property data
     * @constructor Create empty Success
     */
    data class Success<out T : Any?>(val data: T) : Result<T>()

    /**
     * Error
     *
     * @property exception
     */
    class Error(val exception: UseCaseException) : Result<Nothing>()
}