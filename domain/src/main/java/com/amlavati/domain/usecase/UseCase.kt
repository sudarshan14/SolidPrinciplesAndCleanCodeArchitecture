package com.amlavati.domain.usecase

import com.amlavati.domain.entity.Result
import com.amlavati.domain.entity.UseCaseException
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

/**
 * Use case:
 * In this template, we have defined the data transfer objects' abstraction, as well
 * as a Configuration class that holds CoroutineDispatcher. The reason
 * for this Configuration class is to be able to add other parameters for the
 * use case without modifying the UseCase subclasses. We have one abstract
 * method, which will be implemented by the subclasses to retrieve the data from the
 * repositories, and the execute method, which will take the data and convert it to
 * Result, handle the error scenarios, and set the proper CoroutineDispatcher.
 *
 * @param I
 * @param O
 * @property configuration
 * @constructor Create empty Use case
 */
abstract class UseCase<I : UseCase.Request, O : UseCase.Response>(private val configuration: Configuration) {


    /**
     * Execute
     *
     * @param request
     */
    fun execute(request: I) = process(request).map {
        Result.Success(it) as Result<O>
    }
        .flowOn(configuration.dispatcher)
        .catch {
            emit(Result.Error(UseCaseException.createFromThrowable(it)))
        }


    /**
     * Process
     *
     * @param request
     * @return
     */
    internal abstract fun process(request: I): Flow<O>

    /**
     * Configuration
     *
     * @property dispatcher
     * @constructor Create empty Configuration
     */
    class Configuration(
        val dispatcher:
        CoroutineDispatcher
    )

    /**
     * Request
     *
     * @constructor Create empty Request
     */
    interface Request

    /**
     * Response
     *
     * @constructor Create empty Response
     */
    interface Response
}