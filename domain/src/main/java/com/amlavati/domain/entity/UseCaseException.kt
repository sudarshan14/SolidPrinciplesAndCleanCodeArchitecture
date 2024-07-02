package com.amlavati.domain.entity

/**
 * Use case exception
 *
 * @constructor
 *
 * @param cause
 */
sealed class UseCaseException(cause: Throwable) : Exception(cause) {

    /**
     * Post exception
     *
     * @constructor
     *
     * @param cause
     */
    class PostException(cause: Throwable) : UseCaseException(cause)

    /**
     * User exception
     *
     * @constructor
     *
     * @param cause
     */
    class UserException(cause: Throwable) : UseCaseException(cause)

    /**
     * Unknown exception
     *
     * @constructor
     *
     * @param cause
     */
    class UnknownException(cause: Throwable) : UseCaseException(cause)

    companion object {
        /**
         * Here, we have exceptions defined for when there will be an issue with loading the
         * post and user information, and UnknownException, which will be emitted when
         * something else goes wrong
         * */
        fun createFromThrowable(cause: Throwable): UseCaseException {
            UseCaseException
            return if (cause is UseCaseException) {
                cause
            } else {
                UnknownException(cause)
            }
        }
    }
}