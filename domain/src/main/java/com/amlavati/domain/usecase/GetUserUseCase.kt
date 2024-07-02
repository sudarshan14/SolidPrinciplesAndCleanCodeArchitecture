package com.amlavati.domain.usecase

import com.amlavati.domain.entity.User
import com.amlavati.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * Get user use case
 *
 * @property userRepository
 * @constructor
 *
 * @param configuration
 */
class GetUserUseCase @Inject constructor(
    configuration: Configuration,
    private val userRepository: UserRepository
) : UseCase<GetUserUseCase.Request, GetUserUseCase.Response>(configuration) {


    override fun process(request: Request): Flow<Response> =
        userRepository.getUser(request.userId).map {
            Response(it)
        }

    /**
     * Response
     *
     * @property user
     * @constructor Create empty Response
     */
    data class Response(val user: User) : UseCase.Response

    /**
     * Request
     *
     * @property userId
     * @constructor Create empty Request
     */
    data class Request(val userId: Long) : UseCase.Request


}